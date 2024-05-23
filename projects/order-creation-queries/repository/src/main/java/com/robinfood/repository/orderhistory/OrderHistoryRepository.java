package com.robinfood.repository.orderhistory;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderhistory.request.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.orderhistory.response.OrderHistoryItemDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.orderhistory.response.OrderHistoryItemEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IOrderHistoryRepository
 */
@Repository
public class OrderHistoryRepository implements IOrderHistoryRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrderHistoryRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<EntityDTO<OrderHistoryItemDTO>> getOrderHistory(
            OrderHistoryRequestDTO orderHistoryRequestDTO,
            String token
    ) {
        final Result<APIResponseEntity<EntityDTO<OrderHistoryItemEntity>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getOrderHistoryByStore(
                        orderHistoryRequestDTO.getStoreId(),
                        orderHistoryRequestDTO.getChannelsId(),
                        orderHistoryRequestDTO.getCurrentPage(),
                        orderHistoryRequestDTO.getIsDelivery(),
                        orderHistoryRequestDTO.getLocalDateEnd(),
                        orderHistoryRequestDTO.getLocalDateStart(),
                        orderHistoryRequestDTO.getPerPage(),
                        orderHistoryRequestDTO.getSearchText(),
                        orderHistoryRequestDTO.getTimeZone(),
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<EntityDTO<OrderHistoryItemEntity>>> data =
                ((Result.Success<APIResponseEntity<EntityDTO<OrderHistoryItemEntity>>>) result);

        return new Result.Success(
                data.getData().getData()
        );
    }
}
