package com.robinfood.repository.changestateorders;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBCApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class ChangeStateOrderRemoteDataSource implements IChangeStateOrderRemoteDataSource {

    private final OrderBCApi orderBCApi;

    public ChangeStateOrderRemoteDataSource(OrderBCApi orderBCApi) {
        this.orderBCApi = orderBCApi;
    }

    @Override
    public CompletableFuture<ChangeStateOrderRespondEntity> invoke(
            ChangeStateOrderRequestEntity changeStateOrderRequestEntity,
            String token) {

        final Result<ApiResponseEntity<ChangeStateOrderRespondEntity>> responseEntityResult =
                NetworkExtensionsKt.safeAPICall(orderBCApi.changeState(changeStateOrderRequestEntity, token));

        if (responseEntityResult instanceof Result.Error) {
            final Result.Error serviceError = (Result.Error) responseEntityResult;
            log.error("Failed to change state order: " + serviceError.getException().getLocalizedMessage());
            throw new ResponseStatusException(
                    serviceError.getHttpStatus(),
                    serviceError.getException().getLocalizedMessage()
            );
        }

        log.info("The state of the orders changed successfully");
        return CompletableFuture.completedFuture( ((
                Result.Success<ApiResponseEntity<ChangeStateOrderRespondEntity>>) responseEntityResult)
                .getData()
                .getData());
    }
}
