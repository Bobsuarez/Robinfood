package com.robinfood.app.usecases.getorderpaymentbyorderids;

import com.robinfood.core.dtos.OrderPaymentDTO;
import java.util.List;

public interface IGetOrderPaymentByOrderIdUseCase {

    /**
     * Retrieves the list of order payments DTO
     *
     * @param orderId   to consult
     * @return {@link List<OrderPaymentDTO>} the list of order payments DTO
     */
    List<OrderPaymentDTO> invoke(Long orderId);

}
