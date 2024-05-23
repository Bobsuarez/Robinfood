package com.robinfood.repository.ordersstore;

import com.robinfood.core.dtos.ordersstore.DataOrderStoreRequestDTO;
import com.robinfood.core.dtos.ordersstore.OrderStoreDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class OrdersStoreRepository implements IOrdersStoreRepository{

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrdersStoreRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<List<OrderStoreDTO>> getDataOrderStore(
            DataOrderStoreRequestDTO dataOrderStoreRequestDTO,
            String token
    ) {

        log.info("Invoke get order bc query  {}", dataOrderStoreRequestDTO);

        final Result<APIResponseEntity<List<OrderStoreDTO>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getOrdersStore(
                dataOrderStoreRequestDTO.getStoreId(),
                dataOrderStoreRequestDTO.getLocalDateEnd(),
                dataOrderStoreRequestDTO.getLocalDateStart(),
                dataOrderStoreRequestDTO.getTimeZone(),
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }
        final Result.Success<APIResponseEntity<List<OrderStoreDTO>>> data =
                ((Result.Success<APIResponseEntity<List<OrderStoreDTO>>>) result);
        return new Result.Success(
                data.getData().getData()
        );
    }
}
