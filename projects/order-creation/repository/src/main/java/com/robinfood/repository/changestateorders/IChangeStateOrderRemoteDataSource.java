package com.robinfood.repository.changestateorders;

import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;

import java.util.concurrent.CompletableFuture;

public interface IChangeStateOrderRemoteDataSource {

    /**
     * Returns the result of the order state change
     * @param changeStateOrderRequestEntity the info for change order state
     * @param token the authorization token
     * @return a future that contains the result of status change
     */
    CompletableFuture<ChangeStateOrderRespondEntity> invoke(
            ChangeStateOrderRequestEntity changeStateOrderRequestEntity,
            String token
    );
}
