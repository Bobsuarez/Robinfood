package com.robinfood.repository.userorderhistory;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.enums.Result;

/**
 * Repository for order detail related data
 */
public interface IUserOrderHistoryRepository {

    /**
     * Retrieves the order id's based on the following query params
     *
     * @param currentPage current page
     * @param perPage     the number of results per page
     * @param token       token
     * @param userId      user id
     * @return the order detail containing the orders detailed info
     */
    Result<EntityDTO<ResponseOrderDTO>> getOrderHistory(
        Integer currentPage,
        Integer perPage,
        String token,
        Long userId
    );

}
