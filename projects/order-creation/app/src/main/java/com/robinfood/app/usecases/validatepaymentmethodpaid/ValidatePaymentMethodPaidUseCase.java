package com.robinfood.app.usecases.validatepaymentmethodpaid;

import com.robinfood.app.util.PaymentMethodsUtil;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidRequestDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.mappers.PaymentMethodPaidMappers;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.repository.paymentmethods.IPaymentMethodPaidRepository;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ValidatePaymentMethodPaidUseCase implements IValidatePaymentMethodPaidUseCase {

    private static final String STATUS_TRACE_NAME = "Rejected";
    private final IPaymentMethodPaidRepository paymentMethodRepository;
    private final PaymentMethodsUtil paymentMethodsUtil;

    public ValidatePaymentMethodPaidUseCase(
            IPaymentMethodPaidRepository paymentMethodRepository,
            PaymentMethodsUtil paymentMethodsUtil
    ) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodsUtil = paymentMethodsUtil;
    }

    private List<PaymentMethodDTO> filterPaymentMethodsValidate(
            List<Long> paymentMethodIds,
            List<PaymentMethodDTO> paymentMethodDTOS
    ) {
        return paymentMethodDTOS.stream().filter(paymentMethodDTO ->
                paymentMethodIds.contains(paymentMethodDTO.getId())
        ).collect(Collectors.toList());
    }

    @Override
    public void invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequestDTO
    ) {

        final List<Long> paymentMethodsIds = paymentMethodsUtil.getIDsByCountryID(
                transactionRequestDTO.getCompany().getId()
        );

        final List<PaymentMethodDTO> filterPaymentMethodsValidate = filterPaymentMethodsValidate(
                paymentMethodsIds,
                transactionRequestDTO.getPaymentMethods()
        );

        if (
                Boolean.FALSE.equals(transactionRequestDTO.getPaid())
                        && !CollectionUtils.isEmpty(filterPaymentMethodsValidate)
        ) {

            final Map<Long, Long> searchPaymentMethodsByCountryID = paymentMethodsUtil.getByCountry(
                    transactionRequestDTO.getCompany().getId());

            for (PaymentMethodDTO paymentMethodDTO : filterPaymentMethodsValidate) {

                final Long paymentMethodID = searchPaymentMethodsByCountryID.get(paymentMethodDTO.getId());

                final TransactionCreationResponseDTO transactionCreationResponseDTO = (TransactionCreationResponseDTO)
                        SaveDataInMemoryUtil.getValue(transactionRequestDTO.getUuid().toString());

                final PaymentMethodPaidRequestDTO paymentMethodPaidRequestDTO = PaymentMethodPaidMappers
                        .buildPaymentMethodPaidRequestDTO(
                                transactionRequestDTO.getCompany().getId(),
                                transactionRequestDTO.getDevice().getPlatform(),
                                paymentMethodID,
                                paymentMethodDTO,
                                transactionRequestDTO,
                                transactionCreationResponseDTO
                        );

                sendPaymentMethodToValidate(token, paymentMethodPaidRequestDTO);
            }
        }
    }

    @SneakyThrows
    private void sendPaymentMethodToValidate(
            String token,
            PaymentMethodPaidRequestDTO paymentMethodPaidRequestDTO
    ) {

        final PaymentMethodPaidResponseDTO paymentMethodPaidResponseDTO = paymentMethodRepository
                .send(token, paymentMethodPaidRequestDTO).join();

        if (STATUS_TRACE_NAME.equals(paymentMethodPaidResponseDTO.getStatusTraceName())) {
            throw new TransactionCreationException(
                    HttpStatus.PRECONDITION_FAILED,
                    paymentMethodPaidResponseDTO.getMessage()
            );
        }
    }
}
