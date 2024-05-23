package com.robinfood.repository.changestatusorders;

import com.robinfood.core.entities.changestatusordersrequestentities.ChangeStatusOrdersRequestEntity;
import java.util.concurrent.CompletableFuture;

/**
 * Remote configuration data source that connects to external APIs to change order status
 */
public interface IChangeStatusOrdersRemoteDataSource {

    /**
     * Returns the result of the order status change
     * @param changeStatusOrdersRequestEntity the info for change order status
     * @param token the authorization token
     * @return a future that contains the result of status change
     */
    CompletableFuture<Boolean> invoke(
            ChangeStatusOrdersRequestEntity changeStatusOrdersRequestEntity,
            String token
    );
}
