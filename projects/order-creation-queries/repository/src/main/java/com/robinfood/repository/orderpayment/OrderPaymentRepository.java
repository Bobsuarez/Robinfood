package com.robinfood.repository.orderpayment;

import com.robinfood.core.dtos.orderpayment.DataOrderPaymentRequestDTO;
import com.robinfood.core.dtos.orderpayment.OrderPaymentDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class OrderPaymentRepository implements IOrderPaymentRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrderPaymentRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<List<OrderPaymentDTO>> getDataOrderPayment(
            DataOrderPaymentRequestDTO dataOrderPaymentRequestDTO,
            String token
    ) {

        log.info("Invoke get rest order bc query  {}", dataOrderPaymentRequestDTO);

        final Result<APIResponseEntity<List<OrderPaymentDTO>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getOrderPaymentMethods(
                        dataOrderPaymentRequestDTO.getPosId(),
                        dataOrderPaymentRequestDTO.getLocalDateEnd(),
                        dataOrderPaymentRequestDTO.getLocalDateStart(),
                        dataOrderPaymentRequestDTO.getTimeZone(),
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<OrderPaymentDTO>>> data =
                ((Result.Success<APIResponseEntity<List<OrderPaymentDTO>>>) result);
        return new Result.Success(
                data.getData().getData()
        );
    }
}
