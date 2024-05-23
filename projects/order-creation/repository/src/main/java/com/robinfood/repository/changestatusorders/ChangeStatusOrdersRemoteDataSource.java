package com.robinfood.repository.changestatusorders;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.changestatusordersrequestentities.ChangeStatusOrdersRequestEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBCApi;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of IChangeStatusOrdersRemoteDataSource
 */
@Slf4j
public class ChangeStatusOrdersRemoteDataSource implements IChangeStatusOrdersRemoteDataSource {

    private final OrderBCApi orderBCApi;

    public ChangeStatusOrdersRemoteDataSource(OrderBCApi orderBCApi) {
        this.orderBCApi = orderBCApi;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            ChangeStatusOrdersRequestEntity changeStatusOrdersRequestEntity,
            String token
    ) {
        final Result<ApiResponseEntity> responseEntityResult =
                NetworkExtensionsKt.safeAPICall(orderBCApi.changeStatus(changeStatusOrdersRequestEntity, token));

        if (responseEntityResult instanceof Result.Error) {
            final Result.Error serviceError = (Result.Error) responseEntityResult;
            log.error("Failed to change state order: " + serviceError.getException().getLocalizedMessage());
            throw new ResponseStatusException(
                    serviceError.getHttpStatus(),
                    serviceError.getException().getLocalizedMessage()
            );
        }

        log.info("The status of the orders changed successfully");
        return CompletableFuture.completedFuture(true);
    }
}
