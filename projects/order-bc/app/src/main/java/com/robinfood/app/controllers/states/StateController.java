package com.robinfood.app.controllers.states;

import com.robinfood.app.logging.mappeddiagnosticcontext.ChangeStatesOrderLog;
import com.robinfood.app.usecases.changestatusorders.IChangeOrderStateUseCase;
import com.robinfood.app.usecases.validatechangestate.IValidateChangeStateUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import com.robinfood.core.utilities.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.robinfood.core.constants.APIConstants.STATE_V1;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.RECEIVING_MESSAGE_CHANGE_STATUS_HTTPS;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.RESPONSE_CHANGE_STATUS_HTTPS;

@RestController
@RequestMapping(STATE_V1)
@Slf4j
public class StateController implements IStateController {

    private final IChangeOrderStateUseCase changeOrderStateAPiUseCase;

    private final IValidateChangeStateUseCase validateChangeStateUseCase;

    public StateController(IChangeOrderStateUseCase changeOrderStateAPiUseCase,
                           IValidateChangeStateUseCase validateChangeStateUseCase) {
        this.changeOrderStateAPiUseCase = changeOrderStateAPiUseCase;
        this.validateChangeStateUseCase = validateChangeStateUseCase;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDTO<WriteChangeStatusDTO>> changeStateOrder(
            @Valid @RequestBody() ChangeOrderStatusDTO changeOrderStatusDTO
    ) {

        AbstractApiResponseBuilderDTO<WriteChangeStatusDTO> apiResponseDTOBuilder;
        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();

        log.info(
                RECEIVING_MESSAGE_CHANGE_STATUS_HTTPS.getMessageWithCode(),
                ObjectMapperSingleton.objectToJson(changeOrderStatusDTO)
        );

        ChangeStatesOrderLog.invoke(changeOrderStatusDTO);

        validateChangeStateUseCase.invoke(changeOrderStatusDTO);

        WriteChangeStatusDTO responseTypeDeductionsDTO = changeOrderStateAPiUseCase.invoke(changeOrderStatusDTO);

        log.info(
                RESPONSE_CHANGE_STATUS_HTTPS.getMessageWithCode(),
                ObjectMapperSingleton.objectToJson(changeOrderStatusDTO),
                ObjectMapperSingleton.objectToJson(responseTypeDeductionsDTO)
        );

        apiResponseDTOBuilder.build(responseTypeDeductionsDTO);

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }
}
