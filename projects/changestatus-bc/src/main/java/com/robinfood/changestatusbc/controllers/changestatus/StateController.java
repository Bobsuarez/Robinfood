package com.robinfood.changestatusbc.controllers.changestatus;

import com.robinfood.changestatusbc.dtos.ApiResponseDTO;
import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;
import com.robinfood.changestatusbc.usecases.changestatus.IChangeOrderStateUseCase;
import com.robinfood.changestatusbc.usecases.validatechangestate.IValidateChangeStateUseCase;
import com.robinfood.changestatusbc.utilities.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.changestatusbc.configs.constants.APIConstants.CHANGE_STATUS_ENDPOINT;
import static com.robinfood.changestatusbc.configs.constants.APIConstants.STATUS_CHANGE_SUCCESSFUL_MESSAGE;
import static com.robinfood.changestatusbc.configs.constants.APIConstants.V1;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.RECEIVING_MESSAGE_CHANGE_STATUS_HTTPS;

@Slf4j
@RestController
@RequestMapping(value = V1 + CHANGE_STATUS_ENDPOINT)
@Validated
public class StateController implements IStateController {

    private final IChangeOrderStateUseCase changeStatusOrderUseCase;
    private final IValidateChangeStateUseCase validateChangeStateUseCase;

    public StateController(
            IChangeOrderStateUseCase changeStatusOrderUseCase,
            IValidateChangeStateUseCase validateChangeStateUseCase
    ) {
        this.changeStatusOrderUseCase = changeStatusOrderUseCase;
        this.validateChangeStateUseCase = validateChangeStateUseCase;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDTO<WriteChangeStatusDTO>> changeStatusOrder(
            ChangeOrderStatusDTO changeOrderStatusDTO
    ) {

        log.info(
                RECEIVING_MESSAGE_CHANGE_STATUS_HTTPS.getMessageWithCode(),
                ObjectMapperSingleton.objectToJson(changeOrderStatusDTO)
        );

        validateChangeStateUseCase.invoke(changeOrderStatusDTO);

        WriteChangeStatusDTO responseTypeDeductionsDTO = changeStatusOrderUseCase.invoke(changeOrderStatusDTO);

        log.info("successfully");
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ApiResponseDTO.<WriteChangeStatusDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message(STATUS_CHANGE_SUCCESSFUL_MESSAGE)
                        .data(responseTypeDeductionsDTO)
                        .error(Boolean.FALSE)
                        .build()
                );
    }
}
