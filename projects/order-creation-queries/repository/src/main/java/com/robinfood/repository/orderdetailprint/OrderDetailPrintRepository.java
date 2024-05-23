package com.robinfood.repository.orderdetailprint;

import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.OrderDetailPrintMappers;
import java.util.List;

import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IOrderDetailPrintRepository
 */
@Repository
@Slf4j
public class OrderDetailPrintRepository implements IOrderDetailPrintRepository {

    @Autowired
    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrderDetailPrintRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<List<OrderDetailDTO>> getOrderDetailPrint(
            String token,
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    ) {
        final Result<APIResponseEntity<List<OrderDetailEntity>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getOrderDetailPrint(
                        token,
                        orderIds,
                        orderUids,
                        orderUuid
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            log.info("Error get from orderBc  Result.Error {}, getHttpStatus {}",
                    resultError.getException(), resultError.getHttpStatus());
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }
        final Result.Success<APIResponseEntity<List<OrderDetailEntity>>> data =
                ((Result.Success<APIResponseEntity<List<OrderDetailEntity>>>) result);
        return new Result.Success(
                OrderDetailPrintMappers.toOrderDetailPrintDTO(data.getData().getData())
        );
    }
}
