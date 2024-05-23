package com.robinfood.app.usecases.changestatusorders;

import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.dtos.staterespondto.StateChangeRespondDTO;

public interface IChangeOrderStateUseCase {

    /**
     * Updates orders state
     *
     * @param stateChangeRequestDTO orders change state request
     * @return message send to the queue
     */
    StateChangeRespondDTO invoke (StateChangeRequestDTO stateChangeRequestDTO);
}
