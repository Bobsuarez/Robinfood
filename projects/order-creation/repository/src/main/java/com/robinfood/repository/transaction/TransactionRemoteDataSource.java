package com.robinfood.repository.transaction;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.transactionrequestentities.TransactionRequestEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionCreationResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelResponseRest;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelRest;
import com.robinfood.network.api.OrderBCApi;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of ITransactionRemoteDataSource
 */
@Slf4j
public class TransactionRemoteDataSource implements ITransactionRemoteDataSource {

    private final OrderBCApi orderBCApi;

    public TransactionRemoteDataSource(OrderBCApi orderBCApi) {
        this.orderBCApi = orderBCApi;
    }

    @Override
    public CompletableFuture<TransactionCreationResponseEntity> createTransaction(
            String token,
            TransactionRequestEntity transactionRequest
    ) {
        final Result<ApiResponseEntity<TransactionCreationResponseEntity>> transactionCreationResult =
                NetworkExtensionsKt.safeAPICall(
                        orderBCApi.createTransaction(token, transactionRequest));

        if (transactionCreationResult instanceof Result.Error) {
            final Result.Error actualError = (Result.Error) transactionCreationResult;
            throw new TransactionCreationException(
                    actualError.getHttpStatus(),
                    actualError.getException().getLocalizedMessage(),
                    TransactionCreationErrors.ORDER_BC_ERROR
            );
        }

        return CompletableFuture.completedFuture(
                ((Result.Success<ApiResponseEntity<TransactionCreationResponseEntity>>) transactionCreationResult)
                        .getData()
                        .getData()
        );
    }

    @Override
    public List<PickupTimeModelResponseRest> savePickupTime(String token, PickupTimeModelRest request) {
        log.info("Exiting to save the pickup-time in order-bc");

        final Result<ApiResponseEntity<List<PickupTimeModelResponseRest>>> response = NetworkExtensionsKt.safeAPICall(
                orderBCApi.savePickupTime(token, request));

        if (response instanceof Result.Error) {
            final Result.Error error = (Result.Error) response;

            throw new TransactionCreationException(
                    error.getHttpStatus(),
                    error.getException().getLocalizedMessage(),
                    TransactionCreationErrors.ORDER_BC_ERROR
            );
        }

        return ((Result.Success<ApiResponseEntity<List<PickupTimeModelResponseRest>>>) response).getData()
                .getData();
    }

}
