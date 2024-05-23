package com.robinfood.repository.orderfilter;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Implementation of IOrderFilterRepository
 */
@Repository
@Slf4j
public class OrderFilterRepository implements IOrderFilterRepository {

    private final OrderBcAPI orderBcAPI;

    public OrderFilterRepository(OrderBcAPI orderBcAPI) {
        this.orderBcAPI = orderBcAPI;
    }

    @Override
    public Result<EntityDTO<OrderDTO>> getOrderFilter(
            Integer currentPage,
            String filterText,
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Integer perPage,
            Long storeId,
            String timeZone,
            String token
    ) {

        log.info(
                "Invoke get order filter " +
                        "currentPage {}, " +
                        "filterText {}, " +
                        "localDateEnd {}, " +
                        "localDateStart {}, " +
                        "perPage {}, " +
                        "storeId {} y " +
                        "timeZone {}",
                currentPage, filterText, localDateEnd, localDateStart, perPage, storeId, timeZone
        );

        final Result<APIResponseEntity<EntityDTO<OrderEntity>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcAPI.getOrderFilter(
                        currentPage,
                        filterText,
                        localDateEnd,
                        localDateStart,
                        perPage,
                        storeId,
                        timeZone,
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;

            log.info("Error get from orderBc Result.Error {}, getHttpStatus {}",
                    resultError.getException(), resultError.getHttpStatus());

            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<EntityDTO<OrderEntity>>> data =
                ((Result.Success<APIResponseEntity<EntityDTO<OrderEntity>>>) result);

        return new Result.Success(
                data.getData().getData()
        );
    }
}
