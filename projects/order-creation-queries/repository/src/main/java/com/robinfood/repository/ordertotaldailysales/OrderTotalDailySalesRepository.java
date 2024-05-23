package com.robinfood.repository.ordertotaldailysales;

import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import com.robinfood.core.dtos.OrderTotalDailySalesResponseDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.robinfood.network.api.OrderBcQueriesAPI;
import org.springframework.stereotype.Repository;

@Repository
public class OrderTotalDailySalesRepository implements IOrderTotalDailySalesRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public OrderTotalDailySalesRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<List<OrderTotalDailySalesDTO>> getOrderTotalDailySales(String token, int storeId, LocalDate date) {

        final Result<APIResponseEntity<List<OrderTotalDailySalesResponseDTO>>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getOrderTotalDailySales(token, String.valueOf(storeId), date)
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<List<OrderTotalDailySalesResponseDTO>>> data =
            ((Result.Success<APIResponseEntity<List<OrderTotalDailySalesResponseDTO>>>) result);

        List<OrderTotalDailySalesResponseDTO> list = data.getData().getData();
        List<OrderTotalDailySalesDTO> listResp = new ArrayList<>();
        for (OrderTotalDailySalesResponseDTO item: list) {
            String totalDailySales = String.format(Locale.ENGLISH,"%.2f", item.getTotalDailySales());
            listResp.add(
                new OrderTotalDailySalesDTO(
                    totalDailySales,
                    item.getPaymentMethodId(),
                    item.getPaymentMethodName(),
                    item.getTotalOrders())
            );
        }

        return new Result.Success(listResp);

    }

}
