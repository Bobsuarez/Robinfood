package com.robinfood.repository.statusorderhistory;

import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusOrderHistoryRepository implements IStatusOrderHistoryRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public StatusOrderHistoryRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<List<StatusOrderHistoryDTO>> getStatusOrderHistory(String token, String uuid) {

        final Result<APIResponseEntity<List<StatusOrderHistoryDTO>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getStatusOrderHistory(token, uuid)
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<StatusOrderHistoryDTO>>> data =
                ((Result.Success<APIResponseEntity<List<StatusOrderHistoryDTO>>>) result);

        return new Result.Success(data.getData().getData());
    }
}
