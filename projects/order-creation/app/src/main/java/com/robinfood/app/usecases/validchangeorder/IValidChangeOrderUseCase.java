package com.robinfood.app.usecases.validchangeorder;

import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;

public interface IValidChangeOrderUseCase {

    /**
     * Validate parameter of a petition to change th state
     *
     * @return The result of this step's operation
     */
    Boolean invoke(StateChangeRequestDTO stateChangeRequestDTO);
}
