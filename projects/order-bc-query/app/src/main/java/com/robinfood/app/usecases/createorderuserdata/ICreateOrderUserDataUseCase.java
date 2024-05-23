package com.robinfood.app.usecases.createorderuserdata;

import com.robinfood.core.dtos.request.transaction.RequestUserDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order flag togo
 */
public interface ICreateOrderUserDataUseCase {

    /**
     * Create order user data
     * @param userDataDTOList for creating order user data
     * @return future success or false
     */
    CompletableFuture<Boolean> invoke(List<RequestUserDTO> userDataDTOList);
}
