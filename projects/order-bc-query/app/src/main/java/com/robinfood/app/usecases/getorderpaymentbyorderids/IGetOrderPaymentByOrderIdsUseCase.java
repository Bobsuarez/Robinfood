package com.robinfood.app.usecases.getorderpaymentbyorderids;

import com.robinfood.core.dtos.OrderPaymentDTO;

import java.util.List;

/**
 * Use case retrieves the list of order payments DTO
 */
public interface IGetOrderPaymentByOrderIdsUseCase {

    /**
     * Retrieves the list of order payments DTO
     *
     * @param orderIds the list order products id's DTO
     * @return List <OrderPaymentDTO> the list of order payments DTO
     */
    List<OrderPaymentDTO> invoke(List<Long> orderIds);
}
