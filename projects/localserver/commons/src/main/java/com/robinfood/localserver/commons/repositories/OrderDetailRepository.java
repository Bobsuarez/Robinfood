package com.robinfood.localserver.commons.repositories;

import com.robinfood.localserver.commons.dtos.http.ApiResponseOrderOrQueriesDTO;
import com.robinfood.localserver.commons.entities.http.ApiResponseOrderOrQueriesEntity;
import com.robinfood.localserver.commons.mappers.orders.IApiResponseOrderOrQueriesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of ICreateOrdersElectronicBillingRepository
 */
@Repository
@Slf4j
public class OrderDetailRepository implements IOrderDetailRepository {

    private final IApiResponseOrderOrQueriesMapper apiResponseOrderOrQueriesMapper;
    private final IOrderDetailDataSource orderDetailDataSource;

    public OrderDetailRepository(IApiResponseOrderOrQueriesMapper orderDetailMapper,
                                 IOrderDetailDataSource orderDetailDataSource) {
        this.apiResponseOrderOrQueriesMapper = orderDetailMapper;
        this.orderDetailDataSource = orderDetailDataSource;
    }

    @Override
    public ApiResponseOrderOrQueriesDTO invoke(
            String tokenUser,
            Long orderId
    ) {

        log.debug("Execute get order detail repository for id order {}", orderId);

        ApiResponseOrderOrQueriesEntity apiResponseOrderOrQueriesEntity = orderDetailDataSource
                .invoke(tokenUser, orderId);

        return apiResponseOrderOrQueriesMapper.apiResponseOrderOrQueriesEntityToApiResponseOrderOrQueriesDTO(
                apiResponseOrderOrQueriesEntity);
    }
}
