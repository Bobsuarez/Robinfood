package com.robinfood.app.usecases.getstatusbyid;

import com.robinfood.core.dtos.OrderStatusDTO;

/**
 *  Use case retrieves the get status
 */
public interface IGetStatusByIdUseCase {

    /**
     * Retrieves the status
     * @param statusId status id
     * @return status dto
     */
    OrderStatusDTO invoke(Long statusId);
}
