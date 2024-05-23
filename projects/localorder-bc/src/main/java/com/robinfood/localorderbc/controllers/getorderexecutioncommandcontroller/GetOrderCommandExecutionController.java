package com.robinfood.localorderbc.controllers.getorderexecutioncommandcontroller;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.enums.ApiResponseEnum;
import com.robinfood.localorderbc.usecases.getorderexecutioncommandusecase.IGetOrderCommandExecutionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static com.robinfood.localorderbc.configs.constants.APIConstants.COMMAND_EXECUTION;

@RestController
@RequestMapping(COMMAND_EXECUTION)
@Slf4j
public class GetOrderCommandExecutionController implements IGetOrderCommandExecutionController{

    private final IGetOrderCommandExecutionUseCase getOrderCommandExecutionUseCase;

    public GetOrderCommandExecutionController(IGetOrderCommandExecutionUseCase getOrderCommandExecutionUseCase) {
        this.getOrderCommandExecutionUseCase = getOrderCommandExecutionUseCase;
    }

    @Override
    @GetMapping
    public ResponseEntity<APIResponseDTO<List<OrderCommandExecutionDTO>>> invoke() {

        log.info("Get Order Command Execution without processing");

        final List<OrderCommandExecutionDTO> orderCommandExecutionDTOList = getOrderCommandExecutionUseCase.invoke();

        log.info("Order Command Execution Controller Result {}", orderCommandExecutionDTOList);

        AbstractApiResponseBuilderDTO<List<OrderCommandExecutionDTO>> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(orderCommandExecutionDTOList, ApiResponseEnum.RESPONSE_OK_ORDER_CREATE);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }
}
