package com.robinfood.localserver.commons.repositories;

import com.robinfood.localserver.commons.dtos.http.ApiResponseOrderOrQueriesDTO;

/**
 * Repository that handles the creation of the order electronic billing response.
 */
public interface IOrderDetailRepository {

    /**
     * creates an order detail
     * @param orderId order detail
     * @return a future containing the result of the operation
     */
    ApiResponseOrderOrQueriesDTO invoke(
            String tokenUser,
            Long orderId
    );
}
