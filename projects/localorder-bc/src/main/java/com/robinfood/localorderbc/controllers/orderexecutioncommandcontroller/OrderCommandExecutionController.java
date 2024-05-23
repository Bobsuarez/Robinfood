package com.robinfood.localorderbc.controllers.orderexecutioncommandcontroller;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import com.robinfood.localorderbc.enums.ApiResponseEnum;
import com.robinfood.localorderbc.usecases.orderexecutioncommandusecase.ICommandExecutionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.localorderbc.configs.constants.APIConstants.COMMAND_EXECUTION;

@RestController
@RequestMapping(COMMAND_EXECUTION)
@Slf4j
public class OrderCommandExecutionController implements IOrderCommandExecutionController {

    private final ICommandExecutionUseCase commandExecutionUseCase;

    public OrderCommandExecutionController(ICommandExecutionUseCase commandExecutionUseCase) {
        this.commandExecutionUseCase = commandExecutionUseCase;
    }

    @Override
    @PostMapping
    public ResponseEntity<APIResponseDTO<OrderCommandExecutionDTO>> invoke(
            @RequestBody() OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO) {

        log.info("Order Command Execution Controller Execute {}", orderCommandExecutionRequestDTO);

        final OrderCommandExecutionDTO orderCommandExecutionDTO = commandExecutionUseCase
                .invoke(orderCommandExecutionRequestDTO);

        log.info("Order Command Execution UseCase Result {}", orderCommandExecutionDTO);

        AbstractApiResponseBuilderDTO<OrderCommandExecutionDTO> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(orderCommandExecutionDTO, ApiResponseEnum.RESPONSE_OK_ORDER_CREATE);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());

    }
}
