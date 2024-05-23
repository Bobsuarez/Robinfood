package com.robinfood.localserver.commons.repositories;

import com.robinfood.localserver.commons.entities.http.ApiResponseOrderOrQueriesEntity;

/**
 * Remote configuration data source that connects to external APIs to create an order electronic billing response
 */
public interface IOrderDetailDataSource {

    /**
     * Returns the result of create an order electronic billing response
     * @param tokenUser the info to create order electronic billing response
     * @return a future that contains the result creation of an order electronic billing response
     */
    ApiResponseOrderOrQueriesEntity invoke(
            String tokenUser,
            Long orderId
    );
}
