package com.robinfood.app.usecases.createorderdetail;

import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order detail
 */
public interface ICreateOrderDetailUseCase {

    /**
     * Retrieves if order detail has been created
     * @param orderDTOS list of orders
     * @param createdOrderDTOList result list of the ordercreate
     * @return if was successfully
     */
    CompletableFuture<Boolean> invoke(
            List<OrderDTO> orderDTOS,
            List<ResponseCreatedOrderDTO> createdOrderDTOList
    );
}
