package com.robinfood.app.usecases.getuseractiveorder;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that retrieved the active orders
 */
public interface IGetUserActiveOrderUseCase {

    /**
     * Retrieves the active order based on the following query params and user id
     *
     * @param userId userId
     * @return the active orders containing the orders detailed info
     */
    Result<List<ResponseOrderDTO>> invoke(
        Long userId
    );
}
