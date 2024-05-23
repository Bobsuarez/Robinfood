package com.robinfood.repository.report.salessegment;

import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.OrderBcQueriesAPI;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service()
public class SalesSegmentRepository implements ISalesSegmentRepository {

    private final OrderBcQueriesAPI orderBcQueriesAPI;

    public SalesSegmentRepository(OrderBcQueriesAPI orderBcQueriesAPI) {
        this.orderBcQueriesAPI = orderBcQueriesAPI;
    }

    @Override
    public Result<GetSaleBySegmentDTO> getSalesSegment(
            List<Long> brands,
            List<Long> companies,
            List<Long> channels,
            LocalDateTime dateTimeCurrent,
            List<Long> paymentMethods,
            List<Long> stores,
            List<String> timezones,
            String token
    ) {

        final Result<APIResponseEntity<GetSaleBySegmentDTO>> result = NetworkExtensionsKt.safeAPICall(
                orderBcQueriesAPI.getSalesSegment(
                        brands,
                        companies,
                        channels,
                        dateTimeCurrent,
                        paymentMethods,
                        stores,
                        timezones,
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<GetSaleBySegmentDTO>> data =
                ((Result.Success<APIResponseEntity<GetSaleBySegmentDTO>>) result);

        return new Result.Success(data.getData().getData());
    }

}
