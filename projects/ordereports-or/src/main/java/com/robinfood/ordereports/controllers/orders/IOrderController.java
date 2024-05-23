package com.robinfood.ordereports.controllers.orders;

import com.robinfood.app.library.dto.Answer;
import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;


public interface IOrderController {

    /**
     * Retrieves the order details based on the following query params
     * @param transactionUuid the transactionUuid for order
     * @return the order history containing the orders detailed info
     */
    Answer<OrderDetailDTO> getOrderDetails(String transactionUuid);
}
