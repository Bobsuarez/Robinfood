package com.robinfood.app.usecases.getuserdatalistbyuserid;

import com.robinfood.core.dtos.UserDataDTO;

public interface IGetUserDataByOrderIdUseCase {

    /**
     * Method that finds the user of an order by its orderId
     *
     * @param orderId   To consult
     * @return {@link UserDataDTO}
     */
    UserDataDTO invoke(Long orderId);

}
