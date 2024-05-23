package com.robinfood.app.usecases.getstatusbylistid;

import com.robinfood.core.dtos.OrderStatusDTO;

import java.util.List;

/**
 *  Use case retrieves the get a List of status
 */
public interface IGetStatusByListIdUseCase {

    /**
     * Retrieves the status
     *
     * @param statusIds status identifiers
     * @return status dto
     */
    List<OrderStatusDTO> invoke(List<Long> statusIds);
}
