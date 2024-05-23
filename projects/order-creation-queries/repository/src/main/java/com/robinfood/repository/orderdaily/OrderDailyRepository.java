package com.robinfood.repository.orderdaily;

import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderDailyEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.OrderDailyMappers;
import com.robinfood.network.api.OrderBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of IOrderDailyRepository
 */
@Repository
@Slf4j
public class OrderDailyRepository implements IOrderDailyRepository {

    private final OrderBcAPI orderBcAPI;

    public OrderDailyRepository(OrderBcAPI orderBcAPI) {
        this.orderBcAPI = orderBcAPI;
    }

    @Override
    public Result<List<OrderDailyDTO>> getOrderDaily(
            Long storeId,
            String timeZone,
            String token
    ) {

        log.info("Execute Get Order Daily Repository token {}, timeZone {}, storeId {}", token, timeZone, storeId);

        final Result<APIResponseEntity<List<OrderDailyEntity>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcAPI.getOrderDaily(
                        storeId,
                        timeZone,
                        token
                )
        );

        log.info("Result  Get Order Daily Order API {}", result);

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<OrderDailyEntity>>> data =
                ((Result.Success<APIResponseEntity<List<OrderDailyEntity>>>) result);

        return new Result.Success(
                OrderDailyMappers.orderDailyEntityToOrderDailyDtoList(data.getData().getData())
        );
    }
}
