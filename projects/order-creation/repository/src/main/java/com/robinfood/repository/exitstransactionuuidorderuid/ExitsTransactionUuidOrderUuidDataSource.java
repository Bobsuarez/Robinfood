package com.robinfood.repository.exitstransactionuuidorderuid;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.ExistsTransactionUuidOrderUuidEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.logs.OrderErrorLogEnum;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBCApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of IChangeStatusOrdersRemoteDataSource
 */

@Slf4j
@Service
public class ExitsTransactionUuidOrderUuidDataSource implements IExitsTransactionUuidOrderUuidDataSource {

    private final OrderBCApi orderBCApi;

    public ExitsTransactionUuidOrderUuidDataSource(OrderBCApi orderBCApi) {
        this.orderBCApi = orderBCApi;
    }

    @Override
    public ExistsTransactionUuidOrderUuidEntity invoke(String token, String transactionUuid, List<String> orderUuids) {

        log.info("Enter to Exits Transaction Uuid Order Uid DataSource with transaction uuid {} and orders uuids {}",
                transactionUuid, objectToJson(orderUuids)
        );

        final Result<ApiResponseEntity<ExistsTransactionUuidOrderUuidEntity>> exitsMethodResult =
                NetworkExtensionsKt
                        .safeAPICall(orderBCApi.getExitsTransactionUuidOrderUuids(
                                token, transactionUuid, orderUuids)
                        );

        if (exitsMethodResult instanceof Result.Error) {

            final Result.Error serviceError = (Result.Error) exitsMethodResult;

            log.error(OrderErrorLogEnum.ERROR_FAILED_VALIDATE_ORDER_TRANSACTION_EXIST_UUID.getMessage(),
                    serviceError.getException().getLocalizedMessage());

            throw new ResponseStatusException(
                    serviceError.getHttpStatus(),
                    serviceError.getException().getLocalizedMessage()
            );
        }

        return ((Result.Success<ApiResponseEntity<ExistsTransactionUuidOrderUuidEntity>>) exitsMethodResult).getData()
                .getData();
    }
}
