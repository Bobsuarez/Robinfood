package com.robinfood.app.controllers.states;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.dtos.staterespondto.StateChangeRespondDTO;
import org.springframework.http.ResponseEntity;

public interface IStateController {

    /**
     * Updates orders state
     *
     * @param stateChangeRequestDTO the information to change state of a order
     * @return Success message update
     */
    ResponseEntity<ApiResponseDTO<StateChangeRespondDTO>> changeState(
            StateChangeRequestDTO stateChangeRequestDTO
    );
}
