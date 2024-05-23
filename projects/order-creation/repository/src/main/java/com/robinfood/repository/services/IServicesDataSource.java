package com.robinfood.repository.services;

import com.robinfood.core.entities.servicesentities.ServiceListRequestEntity;
import java.util.concurrent.CompletableFuture;

public interface IServicesDataSource {

    CompletableFuture<Boolean> validateService( String token,
                                                ServiceListRequestEntity requestEntity);
}
