package com.robinfood.repository.transaction;

import com.robinfood.core.entities.transactionrequestentities.TransactionRequestEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionCreationResponseEntity;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelResponseRest;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelRest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

/**
 * Remote data source for transactions
 */
public interface ITransactionRemoteDataSource {

    /**
     * Creates transactions
     *
     * @param token              the authorization token
     * @param transactionRequest the transaction with orders to create
     * @return the transaction creation result
     */
    @Async
    CompletableFuture<TransactionCreationResponseEntity> createTransaction(String token,
        TransactionRequestEntity transactionRequest);

    List<PickupTimeModelResponseRest> savePickupTime(String token, PickupTimeModelRest request);

}
