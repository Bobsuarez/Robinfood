package com.robinfood.repository.userorderdetail;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.enums.Result;

/**
 * Repository for order detail related data
 */
public interface IUserOrderDetailRepository {

    /**
     * Retrieves the order based on the following query params
     *
     * @param orderUId order uid
     * @param token    token
     * @param userId   user id
     * @return the order detail containing the orders detailed info
     */
    Result<ResponseOrderDetailDTO> getOrderDetail(
        String orderUId,
        String token,
        Long userId
    );

}
