package com.robinfood.app.usecases.getuseractiveorder;

import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;

import java.util.List;

public interface IGetUserActiveOrderUseCase {

    /**
     * Gets the active orders by user id
     * @param userId user id
     * @return the active orders by user
     */
    List<ResponseOrderDTO> invoke(
        Long userId
    );

}
