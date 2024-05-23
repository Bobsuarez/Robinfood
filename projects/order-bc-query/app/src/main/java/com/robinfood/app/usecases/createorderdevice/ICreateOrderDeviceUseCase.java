package com.robinfood.app.usecases.createorderdevice;

import com.robinfood.core.dtos.request.transaction.RequestDeviceDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that gets the create order device
 */
public interface ICreateOrderDeviceUseCase {

    /**
     * Save order discount
     * @param requestDeviceDTO
     * @param orderIds
     * @return CompletableFuture void
     */
    CompletableFuture<Void> invoke(RequestDeviceDTO requestDeviceDTO, List<Long> orderIds);
}
