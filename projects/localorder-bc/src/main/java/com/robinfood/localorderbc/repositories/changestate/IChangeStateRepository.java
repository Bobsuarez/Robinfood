package com.robinfood.localorderbc.repositories.changestate;

import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.entities.changestate.ChangeStateEntity;

public interface IChangeStateRepository {

    /**
     *
     * @param token token of service
     * @param changeStateDTO entry parameters
     * @return the Change of status of a successful or failed order
     */
    ChangeStateEntity responseChangeState(String token, ChangeStateDTO changeStateDTO);
}
