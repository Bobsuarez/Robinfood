package com.robinfood.repository.ordercategory;

import com.robinfood.core.dtos.ordercategories.DataRequestOrderCategoryDTO;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of IOrderHistoryRepository
 */
@Repository
public class OrderCategoryRepository implements IOrderCategoryRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrderCategoryRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<List<OrderCategoryDTO>> getOrderListCategories(
            DataRequestOrderCategoryDTO dataRequestOrderCategoryDTO,
            String token
    ) {
        final Result<APIResponseEntity<List<OrderCategoryDTO>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getOrdersGroupedByCategories(
                        dataRequestOrderCategoryDTO.getPosId(),
                        dataRequestOrderCategoryDTO.getLocalDateEnd(),
                        dataRequestOrderCategoryDTO.getLocalDateStart(),
                        dataRequestOrderCategoryDTO.getTimeZone(),
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<OrderCategoryDTO>>> data =
                ((Result.Success<APIResponseEntity<List<OrderCategoryDTO>>>) result);

        return new Result.Success(
                data.getData().getData()
        );
    }
}
