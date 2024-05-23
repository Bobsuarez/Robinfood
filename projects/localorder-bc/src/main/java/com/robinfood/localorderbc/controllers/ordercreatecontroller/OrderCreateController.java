package com.robinfood.localorderbc.controllers.ordercreatecontroller;

import com.robinfood.localorderbc.dtos.OrderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.request.OrderUpdateRequestDTO;
import com.robinfood.localorderbc.enums.ApiResponseEnum;
import com.robinfood.localorderbc.usecases.ordercreateusecase.IOrderCreateUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.localorderbc.configs.constants.APIConstants.ORDER_V1;
import static com.robinfood.localorderbc.configs.constants.APIConstants.UPDATE_ORDER;

@RestController
@RequestMapping(ORDER_V1)
@Slf4j
public class OrderCreateController implements IOrderCreateController {

    private final IOrderCreateUseCase orderCreateUseCase;

    public OrderCreateController(IOrderCreateUseCase orderCreateUseCase) {
        this.orderCreateUseCase = orderCreateUseCase;
    }

    @PostMapping
    public ResponseEntity<APIResponseDTO<OrderDTO>> invoke(@RequestBody() OrderDTO order) {
        
        log.info("Order Create Controller Execute {}", order);

        final OrderDTO orderDTO = this.orderCreateUseCase.invoke(order);

        log.info("Order Create UseCase Result {}", order);

        AbstractApiResponseBuilderDTO<OrderDTO> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(orderDTO, ApiResponseEnum.RESPONSE_OK_ORDER_CREATE);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }


    @PostMapping(UPDATE_ORDER)
    public ResponseEntity<APIResponseDTO<OrderDTO>> updateOrder(
            @RequestBody() OrderUpdateRequestDTO orderUpdateRequestDTO) {

        log.info("orderUpdateRequestDTO {}", orderUpdateRequestDTO);

        OrderDTO orderDTO = this.orderCreateUseCase.update(orderUpdateRequestDTO.getOrderId(),
                orderUpdateRequestDTO.getStatusId());

        AbstractApiResponseBuilderDTO<OrderDTO> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(orderDTO, ApiResponseEnum.RESPONSE_OK_ORDER_CHANGE_STATE);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }

}
