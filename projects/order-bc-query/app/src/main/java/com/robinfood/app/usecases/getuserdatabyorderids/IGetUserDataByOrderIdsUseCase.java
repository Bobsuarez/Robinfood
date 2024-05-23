package com.robinfood.app.usecases.getuserdatabyorderids;

import com.robinfood.core.dtos.UserDataDTO;

import java.util.List;

/**
 * Use case that returns theh user data list from some order ids
 */
public interface IGetUserDataByOrderIdsUseCase {

    /**
     * Gets the list of user data information from some order ids
     * @param orderIds the list of order ids
     * @return the list of user data information from some order ids
     */
    List<UserDataDTO> invoke(List<Long> orderIds);
}
