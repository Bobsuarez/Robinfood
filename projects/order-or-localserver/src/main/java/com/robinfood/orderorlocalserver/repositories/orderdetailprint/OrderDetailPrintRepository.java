package com.robinfood.orderorlocalserver.repositories.orderdetailprint;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.entities.APIResponseEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailEntity;
import com.robinfood.orderorlocalserver.enums.Result;
import com.robinfood.orderorlocalserver.mappers.OrderDetailPrintMappers;
import com.robinfood.orderorlocalserver.network.OrderBcAPI;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.robinfood.orderorlocalserver.constants.ApiConstants.API_GET_ORDER_DETAIL;

@Repository
@Slf4j
public class OrderDetailPrintRepository implements IOrderDetailPrintRepository {


    private final OrderBcAPI orderBcAPI;

    @Value( "${feign.client.url.orderBcUrl}" )
    private String orderBcUrl;

    public OrderDetailPrintRepository(OrderBcAPI orderBcAPI) {
        this.orderBcAPI = orderBcAPI;
    }

    @Override
    public Result<List<OrderDetailDTO>> getOrderDetailPrint(
            String token, List<Long> orderIds, List<String> orderUids, List<String> orderUuid
    ) {

        log.info("Starting repository to get the order detail from order-bc. " +
                "This is the orderIds list {} and this is the token {}", orderIds, token);

        try {

            log.info("Sending request to {} with path {}", orderBcUrl, API_GET_ORDER_DETAIL);

            APIResponseEntity<List<OrderDetailEntity>> result =
                    orderBcAPI.getOrderDetailPrint(token, orderIds, orderUids, orderUuid);

            log.info("This the response from order-bc {}", result);

            Result.Success<APIResponseEntity<List<OrderDetailEntity>>> data =
                    new Result.Success<>(result);

            return new Result.Success<>(
                    OrderDetailPrintMappers.toOrderDetailPrintDTO(data.getData().getData())
            );

        } catch(FeignException error) {

            log.info("Error while getting order detail from order-bc. This is the exception.", error);

            return new Result.Error(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
