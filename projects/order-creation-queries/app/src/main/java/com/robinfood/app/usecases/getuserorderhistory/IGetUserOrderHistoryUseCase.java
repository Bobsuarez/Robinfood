package com.robinfood.app.usecases.getuserorderhistory;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.enums.Result;

/**
 * Use case that retrieved the order history
 */
public interface IGetUserOrderHistoryUseCase {

    /**
     * Retrieves the order history based on the following query params and user id
     *
     * @param userId      userId
     * @param currentPage the page the client needs of the current pagination
     * @param perPage     the number of results per page
     * @return the order history containing the orders detailed info
     */
    Result<EntityDTO<ResponseOrderDTO>> invoke(
        Integer currentPage,
        Integer perPage,
        Long userId
    );
}
