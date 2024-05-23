package com.robinfood.app.usecases.getuserorderdetail;

import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.exceptions.ResourceNotFoundException;

/**
 * Use case that returns the order detail by uid
 */
public interface IGetUserOrderDetailByUIdUseCase {

    /**
     * Gets the order detail by uid
     * @param orderUId order uid
     * @return the order detail by uid
     */
    ResponseOrderDetailDTO invoke(
        String orderUId,
        Long userId
    ) throws ResourceNotFoundException;

}
