package com.robinfood.repository.orderspaid;

import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;
import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrdersPaidRepository implements IOrdersPaidRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrdersPaidRepository (OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<GetOrdersPaidDTO> getDataOrdersPaid(
            DataOrdersPaidRequestDTO dataOrdersPaidRequestDTO,
            String token
    ) {
        log.info("Invoke get orders paid to filter bc query  {}", dataOrdersPaidRequestDTO);

        final Result<APIResponseEntity<GetOrdersPaidDTO>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getPaidOrders(
                        dataOrdersPaidRequestDTO.getBrandIds(),
                        dataOrdersPaidRequestDTO.getCompanyId(),
                        dataOrdersPaidRequestDTO.getCurrentPage(),
                        dataOrdersPaidRequestDTO.getIdCustomFilter(),
                        dataOrdersPaidRequestDTO.getLocalDateStart(),
                        dataOrdersPaidRequestDTO.getLocalDateEnd(),
                        dataOrdersPaidRequestDTO.getOriginIds(),
                        dataOrdersPaidRequestDTO.getPaymentMethodIds(),
                        dataOrdersPaidRequestDTO.getPerPage(),
                        dataOrdersPaidRequestDTO.getStatusCode(),
                        dataOrdersPaidRequestDTO.getStoreIds(),
                        dataOrdersPaidRequestDTO.getTimezone(),
                        dataOrdersPaidRequestDTO.getValueCustomFilter(),
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<GetOrdersPaidDTO>> data =
                ((Result.Success<APIResponseEntity<GetOrdersPaidDTO>>) result);

        return new Result.Success(data.getData().getData());
    }
}
