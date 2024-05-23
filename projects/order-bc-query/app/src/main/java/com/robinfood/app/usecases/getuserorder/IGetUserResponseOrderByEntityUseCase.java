package com.robinfood.app.usecases.getuserorder;

import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.entities.OrderEntity;

public interface IGetUserResponseOrderByEntityUseCase {

    /**
     * Gets the response order information by order entity
     * @param order order entity
     * @return the response order
     */
    ResponseOrderDTO invoke(
        OrderEntity order
    );

}
