package com.robinfood.repository.changestateorders;

import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;

import java.util.concurrent.CompletableFuture;

public interface IChangeStateOrdersRepository {

    /**
     * Returns the result of the order state change
     * @param stateChangeRequestDTO the info for change order state
     * @param token the authorization token
     * @return a future that contains the result of status change
     */
    CompletableFuture<ChangeStateOrderRespondEntity> invoke(
            StateChangeRequestDTO stateChangeRequestDTO,
            String token
    );
}
