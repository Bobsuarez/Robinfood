package com.robinfood.repository.changestatusorders;

import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import java.util.concurrent.CompletableFuture;

/**
 * Repository that handles the order status change data.
 */
public interface IChangeStatusOrdersRepository {

    /**
     * Changes the status of sent orders
     * @param changeStatusOrdersRequestDTO the info for change order status
     * @param token the authorization token
     * @return a future containing the result of the operation
     */
    CompletableFuture<Boolean> invoke(
            ChangeStatusOrdersRequestDTO changeStatusOrdersRequestDTO,
            String token
    );
}
