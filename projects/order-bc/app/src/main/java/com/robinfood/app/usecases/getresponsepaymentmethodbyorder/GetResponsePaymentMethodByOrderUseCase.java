package com.robinfood.app.usecases.getresponsepaymentmethodbyorder;

import com.robinfood.app.mappers.OrderDetailOrderMapper;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentMethodDTO;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of IGetPaymentMethodByIdUseCase
 */
@Component
@Slf4j
public class GetResponsePaymentMethodByOrderUseCase implements IGetResponsePaymentMethodByOrderUseCase {

    private final IPaymentMethodRepository paymentMethodRepository;

    public GetResponsePaymentMethodByOrderUseCase(IPaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public Optional<ResponsePaymentMethodDTO> invoke(OrderPaymentDTO orderPayment) {
        log.info(
                "Start of the process that obtains an payment method with {}",
                orderPayment.getPaymentMethodId()
        );

        return paymentMethodRepository.findById(orderPayment.getPaymentMethodId())
                .map(paymentMethod ->
                        OrderDetailOrderMapper.mapOrderPaymentMethodToResponseDTO(orderPayment, paymentMethod)
                );
    }
}
