package com.robinfood.app.usecases.getresponsepaymentmethodbyorder;

import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentMethodDTO;

import java.util.Optional;

public interface IGetResponsePaymentMethodByOrderUseCase {

    /**
     * Get payment method dto
     *
     * @param orderPayment order payment
     * @return ResponseServiceDTO information of service
     */
    Optional<ResponsePaymentMethodDTO> invoke(OrderPaymentDTO orderPayment);

}
