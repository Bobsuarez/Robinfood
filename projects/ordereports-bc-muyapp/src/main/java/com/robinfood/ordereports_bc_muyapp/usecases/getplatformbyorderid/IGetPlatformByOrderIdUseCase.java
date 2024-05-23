package com.robinfood.ordereports_bc_muyapp.usecases.getplatformbyorderid;

import java.util.concurrent.CompletableFuture;

public interface IGetPlatformByOrderIdUseCase {

    CompletableFuture<Short> invoke(Integer orderId);

}
