package com.robinfood.repository.salesstore;

import com.robinfood.core.dtos.salesstore.SalesStoresDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Slf4j
public class SalesStoreRepository  implements ISalesStoreRepository{

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public SalesStoreRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<SalesStoresDTO> getSalesByStoreGroupByPaymentMethods (
            LocalDateTime dateTimeCurrent,
            Long storeId,
            String timezone,
            String token
    ) {

        log.info("Invoke get rest order-bc {}, {}", dateTimeCurrent, storeId);

        final Result<APIResponseEntity<SalesStoresDTO>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getSalesByStoreGroupByPaymentMethods(
                        storeId,
                        dateTimeCurrent,
                        timezone,
                        token
                )
        );

        if (result instanceof Result.Error) {

            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<SalesStoresDTO>> data =
                ((Result.Success<APIResponseEntity<SalesStoresDTO>>) result);

        return new Result.Success(data.getData().getData());
    }
}
