package com.robinfood.app.controllers.states;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.changestatusorders.IChangeOrderStateUseCase;
import com.robinfood.app.usecases.validchangeorder.IValidChangeOrderUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractBuilderApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkApiResponseDTO;
import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.dtos.staterespondto.StateChangeRespondDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.robinfood.core.constants.APIConstants.STATE_V1;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@RestController
@RequestMapping(STATE_V1)
@Slf4j
public class StateController implements IStateController {

    private final IChangeOrderStateUseCase changeOrderStateUseCase;

    private final IValidChangeOrderUseCase validChangeOrderUseCase;

    public StateController(
            IChangeOrderStateUseCase changeOrderStateUseCase,
            IValidChangeOrderUseCase validChangeOrderUseCase
    ) {
        this.changeOrderStateUseCase = changeOrderStateUseCase;
        this.validChangeOrderUseCase = validChangeOrderUseCase;
    }

    @Override
    @CrossOrigin(origins = "${spring.mvc.allowed-origin-bcc}")
    @PreAuthorize("hasAuthority('" + Permissions.STATE_CHANGE_REQUEST + "')")
    @PostMapping()
    public ResponseEntity<ApiResponseDTO<StateChangeRespondDTO>> changeState(
            @Valid @RequestBody StateChangeRequestDTO stateChangeRequestDTO
    ) {

        log.info("Order change state has started with request: {}", objectToJson(stateChangeRequestDTO));

        validChangeOrderUseCase.invoke(stateChangeRequestDTO);

        final StateChangeRespondDTO stateChangeRespondDTO = changeOrderStateUseCase.invoke(stateChangeRequestDTO);

        final AbstractBuilderApiResponseDTO<StateChangeRespondDTO> apiResponseDTOBuilder = new OkApiResponseDTO<>();
        apiResponseDTOBuilder.build(stateChangeRespondDTO);

        return new ResponseEntity<>(new ApiResponseDTO<>(stateChangeRespondDTO), HttpStatus.OK);
    }
}
