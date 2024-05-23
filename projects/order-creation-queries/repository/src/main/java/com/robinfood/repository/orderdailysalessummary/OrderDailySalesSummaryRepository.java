package com.robinfood.repository.orderdailysalessummary;

import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderDailySaleSummaryEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.OrderDailySalesSummaryMappers;
import java.time.LocalDate;

import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IOrderDetailRepository
 */
@Slf4j
@Repository
public class OrderDailySalesSummaryRepository implements IOrderDailySalesSummaryRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrderDailySalesSummaryRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<OrderDailySaleSummaryDTO> getOrderDailySalesSummary(String token, Long storeId, LocalDate createdAt) {

        final Result<APIResponseEntity<OrderDailySaleSummaryEntity>> response = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getOrderDailySalesSummary(token, storeId, createdAt)
        );

        if (response instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) response;
            log.error("Order Daily Sale Summary service could not be parsed: [{}]", resultError);
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<OrderDailySaleSummaryEntity>> data =
            ((Result.Success<APIResponseEntity<OrderDailySaleSummaryEntity>>) response);

        log.info("Order Daily Sale Summary retrieved successfully: [{}]", data.getData().getData());
        return new Result.Success(
            OrderDailySalesSummaryMappers.orderDailySaleSummaryEntityToDTO(data.getData().getData())
        );

    }
}
