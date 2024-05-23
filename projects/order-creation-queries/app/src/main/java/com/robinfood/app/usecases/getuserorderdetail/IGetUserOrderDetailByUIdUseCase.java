package com.robinfood.app.usecases.getuserorderdetail;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.enums.Result;

/**
 * Use case that retrieved the order detail
 */
public interface IGetUserOrderDetailByUIdUseCase {

    /**
     * Retrieves the order detail based on the following user id and order uid
     *
     * @param orderUId orderUId
     * @param userId   userId
     * @return the order detailed info
     */
    Result<ResponseOrderDetailDTO> invoke(
        String orderUId,
        Long userId
    );
}
