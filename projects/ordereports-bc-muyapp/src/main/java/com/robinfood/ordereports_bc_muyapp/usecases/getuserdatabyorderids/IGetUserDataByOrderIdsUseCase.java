package com.robinfood.ordereports_bc_muyapp.usecases.getuserdatabyorderids;

import com.robinfood.ordereports_bc_muyapp.dto.UserDataDTO;

import java.util.concurrent.CompletableFuture;

/**
 * Use case that returns theh user data list from some order ids
 */
public interface IGetUserDataByOrderIdsUseCase {

    /**
     * Gets the list of user data information from some order ids
     *
     * @param orderId the list of order ids
     *
     * @return the list of user data information from some order ids
     */
    CompletableFuture<UserDataDTO> invoke(Integer orderId);
}
