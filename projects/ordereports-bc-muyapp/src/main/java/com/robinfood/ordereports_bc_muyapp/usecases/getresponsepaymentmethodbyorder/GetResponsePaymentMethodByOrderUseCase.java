package com.robinfood.ordereports_bc_muyapp.usecases.getresponsepaymentmethodbyorder;

import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponsePaymentMethodDTO;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.paymentmethod.IPaymentMethodRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of IGetPaymentMethodByIdUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetResponsePaymentMethodByOrderUseCase implements IGetResponsePaymentMethodByOrderUseCase {

    private final IPaymentMethodRepository paymentMethodRepository;

    private final OrderDetailOrderMapper orderDetailOrderMapper;

    @Override
    public Optional<ResponsePaymentMethodDTO> invoke(OrderPaymentDTO orderPayment) {

        return paymentMethodRepository.findById(orderPayment.getPaymentMethodId())
                .map(paymentMethod ->
                             orderDetailOrderMapper.mapOrderPaymentMethodToResponseDTO(orderPayment, paymentMethod)
                );
    }
}
