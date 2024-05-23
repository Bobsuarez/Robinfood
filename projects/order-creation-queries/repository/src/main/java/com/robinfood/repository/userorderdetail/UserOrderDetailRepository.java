package com.robinfood.repository.userorderdetail;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcAPI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IUserOrderDetailRepository
 */
@Repository
@Slf4j
public class UserOrderDetailRepository implements IUserOrderDetailRepository {

    private final OrderBcAPI orderBcAPI;

    public UserOrderDetailRepository(OrderBcAPI orderBcAPI) {
        this.orderBcAPI = orderBcAPI;
    }

    @Override
    public Result<ResponseOrderDetailDTO> getOrderDetail(
        String orderUId,
        String token,
        Long userId
    ) {
        final Result<APIResponseEntity<ResponseOrderDetailDTO>> result = NetworkExtensionsKt.safeAPICall(
            orderBcAPI.getOrderDetailByUser(
                token,
                userId,
                orderUId
            )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            log.error("Error get order detail {}", resultError.getException().getMessage());
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }
        final Result.Success<APIResponseEntity<ResponseOrderDetailDTO>> data =
            ((Result.Success<APIResponseEntity<ResponseOrderDetailDTO>>) result);
        return new Result.Success(
            data.getData().getData()
        );
    }
}
