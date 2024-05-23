package com.robinfood.repository.useractiveorder;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Repository for active orders related data
 */
public interface IUserActiveOrderRepository {

    /**
     * Retrieves the active orders based on the following query params
     *
     * @param userId user id
     * @return the active orders containing the orders detailed info
     */
    Result<List<ResponseOrderDTO>> getActiveOrders(
        String token,
        Long userId
    );

}
