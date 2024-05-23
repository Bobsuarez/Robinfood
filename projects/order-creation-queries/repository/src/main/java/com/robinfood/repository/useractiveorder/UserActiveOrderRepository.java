package com.robinfood.repository.useractiveorder;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcAPI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of IUserActiveOrderRepository
 */
@Repository
@Slf4j
public class UserActiveOrderRepository implements IUserActiveOrderRepository {

    private final OrderBcAPI orderBcAPI;

    public UserActiveOrderRepository(OrderBcAPI orderBcAPI) {
        this.orderBcAPI = orderBcAPI;
    }

    @Override
    public Result<List<ResponseOrderDTO>> getActiveOrders(
        String token,
        Long userId
    ) {
        final Result<APIResponseEntity<List<ResponseOrderDTO>>> result = NetworkExtensionsKt.safeAPICall(
            orderBcAPI.getActiveOrdersByUser(
                token,
                userId
            )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            log.error("Error get active orders by user {}", resultError.getException().getMessage());
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }
        final Result.Success<APIResponseEntity<List<ResponseOrderDTO>>> data =
            ((Result.Success<APIResponseEntity<List<ResponseOrderDTO>>>) result);
        return new Result.Success(
            data.getData().getData()
        );
    }
}
