package com.robinfood.app.usecases.getordersnotdirectpayments;

import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;

import java.util.List;

/**
 * Get Orders Not Direct Payments Use Case
 */
public interface IGetOrdersNotDirectPaymentsUseCase {

    /**
     * Get Orders Not Direct Payments
     *
     * @param orderDTOS List of orders with your information
     * @param orderPaymentDTOS List of Payment with your information
     * @return List of orders with your information
     */
    List<OrderDTO> invoke(List<OrderDTO> orderDTOS, List<OrderPaymentDTO> orderPaymentDTOS);

}
