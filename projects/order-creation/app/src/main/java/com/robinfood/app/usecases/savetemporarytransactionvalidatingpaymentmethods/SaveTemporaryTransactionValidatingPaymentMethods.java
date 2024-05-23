package com.robinfood.app.usecases.savetemporarytransactionvalidatingpaymentmethods;

import com.robinfood.app.usecases.savetemporarytransaction.ISaveTemporaryTransactionUseCase;
import com.robinfood.app.util.PaymentMethodsUtil;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class SaveTemporaryTransactionValidatingPaymentMethods
        implements ISaveTemporaryTransactionValidatingPaymentMethods {

    private final ISaveTemporaryTransactionUseCase saveTransactionCreatedUseCase;
    private final PaymentMethodsUtil paymentMethodsUtil;

    public SaveTemporaryTransactionValidatingPaymentMethods(
            ISaveTemporaryTransactionUseCase saveTransactionCreatedUseCase,
            PaymentMethodsUtil paymentMethodsUtil
    ) {
        this.saveTransactionCreatedUseCase = saveTransactionCreatedUseCase;
        this.paymentMethodsUtil = paymentMethodsUtil;
    }

    @Override
    public void invoke(
            @NotNull TransactionRequestDTO transactionRequestDTO
    ) {

        final Map<Long, Long> searchPaymentMethodsByCountryID = paymentMethodsUtil.getByCountry(
                transactionRequestDTO.getCompany().getId()
        );

        log.info("Save temporary search payment methods IDs: {}", searchPaymentMethodsByCountryID);

        if (searchPaymentMethodsByCountryID.isEmpty()) {
            return;
        }

        saveTransactionCreatedUseCase.invoke(transactionRequestDTO);
    }
}
