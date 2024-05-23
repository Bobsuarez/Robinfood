package com.robinfood.repository.getordersbytransaction;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.getordersbytransaction.OrdersByTransactionEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBCApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
public class GetOrdersByTransactionRemoteDataSource implements IGetOrdersByTransactionRemoteDataSource{

    private final OrderBCApi orderBCApi;

    public GetOrdersByTransactionRemoteDataSource(OrderBCApi orderBCApi) {
        this.orderBCApi = orderBCApi;
    }

    /**
     * Returns the orders associated with a transaction
     *
     * @param transactionId transaction id associated with orders
     * @param token         the authorization token
     * @return a future that contains the result of orders associated with a transaction
     */
    @Override
    public List<OrdersByTransactionEntity> invoke(Long transactionId, String token) {
        final Result<ApiResponseEntity<List<OrdersByTransactionEntity>>> ordersByTransaction =
            NetworkExtensionsKt.safeAPICall(orderBCApi.getOrdersByTransactionId(token, transactionId));

        if (ordersByTransaction instanceof Result.Error) {
            final Result.Error serviceError = (Result.Error) ordersByTransaction;
            log.error("Failed to find orders by transaction: " + serviceError.getException().getLocalizedMessage());
            throw new ResponseStatusException(
                serviceError.getHttpStatus(),
                serviceError.getException().getLocalizedMessage()
            );
        }

        log.info("find orders by transaction successfully");

        return ((Result.Success<ApiResponseEntity<List<OrdersByTransactionEntity>>>) ordersByTransaction).getData()
            .getData();

    }
}
