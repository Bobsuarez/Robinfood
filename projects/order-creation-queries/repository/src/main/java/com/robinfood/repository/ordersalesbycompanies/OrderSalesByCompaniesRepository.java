package com.robinfood.repository.ordersalesbycompanies;

import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class OrderSalesByCompaniesRepository implements IOrderSalesByCompaniesRepository{

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrderSalesByCompaniesRepository (
            OrderBcQueriesAPI orderBcQueriesAPI
    ) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<ResponseOrderActiveSalesDTO> getSalesToOrderByCompanies(
            List<Integer> idsCompanies,
            String dateTimeCurrent,
            List<String> timezones,
            String token
    ) {

        log.info("Invoke get rest order bc query  {}: {}", idsCompanies, dateTimeCurrent);

        final Result<APIResponseEntity<ResponseOrderActiveSalesDTO>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getActiveSalesToOrderByCompanies(
                        idsCompanies,
                        dateTimeCurrent,
                        timezones,
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<ResponseOrderActiveSalesDTO>> data =
                ((Result.Success<APIResponseEntity<ResponseOrderActiveSalesDTO>>) result);
        return new Result.Success(
                data.getData().getData()
        );
    }
}
