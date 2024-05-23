package com.robinfood.localserver.commons.repositories;

import com.robinfood.localserver.commons.constants.network.feignclients.OrderOrQueriesApi;
import com.robinfood.localserver.commons.entities.http.ApiResponseOrderOrQueriesEntity;
import com.robinfood.localserver.commons.enums.logs.StatusLogEnum;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICreateOrdersElectronicBillingRemoteDataSource
 */
@Slf4j
@Repository
public class OrderDetailDataSource implements IOrderDetailDataSource {

    private final OrderOrQueriesApi orderOrQueriesApi;

    public OrderDetailDataSource(OrderOrQueriesApi orderOrQueriesApi) {

        this.orderOrQueriesApi = orderOrQueriesApi;
    }

    @Override
    public ApiResponseOrderOrQueriesEntity invoke(String tokenUser, Long orderId) {

        ApiResponseOrderOrQueriesEntity responseEntityResult = new ApiResponseOrderOrQueriesEntity();
        try {
            log.info("Execute get Order Detail remote Data Source ");

            List<Long> ordersId = new ArrayList<>();
            ordersId.add(orderId);

            responseEntityResult = orderOrQueriesApi.getOrderDetail(tokenUser, ordersId);

            log.info("The order products found successfully {}", responseEntityResult);
        } catch (FeignException exception) {

            log.error(StatusLogEnum.GET_ORDER_FROM_ORDER_QUERIES_FAILED.getMessage() + " for order:{} because {}",
                    orderId,
                    exception.contentUTF8(),
                    exception);
        }

        return responseEntityResult;
    }
}
