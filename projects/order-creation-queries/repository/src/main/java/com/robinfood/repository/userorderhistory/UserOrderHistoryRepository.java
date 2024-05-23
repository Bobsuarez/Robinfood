package com.robinfood.repository.userorderhistory;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcAPI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IUserOrderHistoryRepository
 */
@Repository
@Slf4j
public class UserOrderHistoryRepository implements IUserOrderHistoryRepository {

    private final OrderBcAPI orderBcAPI;

    public UserOrderHistoryRepository(OrderBcAPI orderBcAPI) {
        this.orderBcAPI = orderBcAPI;
    }

    @Override
    public Result<EntityDTO<ResponseOrderDTO>> getOrderHistory(
        Integer currentPage,
        Integer perPage,
        String token,
        Long userId
    ) {
        final Result<APIResponseEntity<EntityDTO<ResponseOrderDTO>>> result = NetworkExtensionsKt.safeAPICall(
            orderBcAPI.getOrderHistoryByUser(
                token,
                userId,
                currentPage,
                perPage
            )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            log.error("Error get order history by user {}", resultError.getException().getMessage());
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }
        final Result.Success<APIResponseEntity<EntityDTO<ResponseOrderDTO>>> data =
            ((Result.Success<APIResponseEntity<EntityDTO<ResponseOrderDTO>>>) result);
        return new Result.Success(
            data.getData().getData()
        );
    }
}
