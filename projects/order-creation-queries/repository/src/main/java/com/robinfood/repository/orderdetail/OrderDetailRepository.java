package com.robinfood.repository.orderdetail;

import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.OrderDetailMappers;
import java.util.List;

import com.robinfood.network.api.OrderBcAPI;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IOrderDetailRepository
 */
@Repository
public class OrderDetailRepository implements IOrderDetailRepository {

    private final OrderBcAPI orderBcAPI;

    public OrderDetailRepository(OrderBcAPI orderBcAPI) {
        this.orderBcAPI = orderBcAPI;
    }

    @Override
    public Result<List<OrderDetailDTO>> getOrderDetail(
            String token,
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    ) {
        final Result<APIResponseEntity<List<OrderDetailEntity>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcAPI.getOrderDetail(
                        token,
                        orderIds,
                        orderUids,
                        orderUuid
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<OrderDetailEntity>>> data =
                ((Result.Success<APIResponseEntity<List<OrderDetailEntity>>>) result);

        return new Result.Success(
                OrderDetailMappers.toOrderDetailDTOs(data.getData().getData())
        );
    }
}
