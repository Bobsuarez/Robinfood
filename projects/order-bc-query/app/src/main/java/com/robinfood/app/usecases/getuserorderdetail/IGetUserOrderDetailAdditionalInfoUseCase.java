package com.robinfood.app.usecases.getuserorderdetail;

import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.exceptions.ResourceNotFoundException;

/**
 * Use case that returns the order detail by uid
 */
public interface IGetUserOrderDetailAdditionalInfoUseCase {

    /**
     * Gets the order detail by uid
     * @param responseOrderDetailDTO basic response order detail
     * @return the order detail by uid
     */
    ResponseOrderDetailDTO invoke(
        ResponseOrderDetailDTO responseOrderDetailDTO
    ) throws ResourceNotFoundException;

}
