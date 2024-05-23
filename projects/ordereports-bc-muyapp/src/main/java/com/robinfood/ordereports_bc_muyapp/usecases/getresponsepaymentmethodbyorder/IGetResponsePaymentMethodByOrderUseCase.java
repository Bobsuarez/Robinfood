package com.robinfood.ordereports_bc_muyapp.usecases.getresponsepaymentmethodbyorder;

import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponsePaymentMethodDTO;

import java.util.Optional;

public interface IGetResponsePaymentMethodByOrderUseCase {

    /**
     * Get payment method dto
     *
     * @param orderPayment order payment
     *
     * @return ResponseServiceDTO information of service
     */
    Optional<ResponsePaymentMethodDTO> invoke(OrderPaymentDTO orderPayment);

}
