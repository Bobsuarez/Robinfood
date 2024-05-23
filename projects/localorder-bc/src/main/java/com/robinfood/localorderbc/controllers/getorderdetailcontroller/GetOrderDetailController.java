package com.robinfood.localorderbc.controllers.getorderdetailcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.OrderResponseDTO;
import com.robinfood.localorderbc.enums.ApiResponseEnum;
import com.robinfood.localorderbc.usecases.findallorderbystatusidusecase.IFindAllOrdersByStatusIdUseCase;
import com.robinfood.localorderbc.usecases.getorderdatailbyidusecase.IGetOrderDetailByIdUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.localorderbc.configs.constants.APIConstants.GET_ORDERS_BY_STATUS_ID;
import static com.robinfood.localorderbc.configs.constants.APIConstants.ORDER_V1;

@RestController
@RequestMapping(ORDER_V1)
@Slf4j
public class GetOrderDetailController implements IGetOrderDetailController {

    private final IGetOrderDetailByIdUseCase getOrderDetailByIdUseCase;
    private final IFindAllOrdersByStatusIdUseCase iFindAllOrdersByStatusId;

    public GetOrderDetailController(IGetOrderDetailByIdUseCase getOrderDetailByIdUseCase,
                                    IFindAllOrdersByStatusIdUseCase iFindAllOrdersByStatusId) {
        this.getOrderDetailByIdUseCase = getOrderDetailByIdUseCase;
        this.iFindAllOrdersByStatusId = iFindAllOrdersByStatusId;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<APIResponseDTO<JsonNode>> invoke(
            @PathVariable final Long orderId
    ) throws JsonProcessingException {

        log.info("Get Order Detail Controller Execute {}", orderId);

        final String orderJsonString = this.getOrderDetailByIdUseCase.invoke(orderId);

        log.info("Get Order Detail UseCase Result {}", orderId);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode orderJson = mapper.readTree(orderJsonString);

        AbstractApiResponseBuilderDTO<JsonNode> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(orderJson, ApiResponseEnum.RESPONSE_OK_GET_ORDER);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }

    @GetMapping(GET_ORDERS_BY_STATUS_ID+"/{statusId}")
    public ResponseEntity<APIResponseDTO<List<OrderResponseDTO>>> getOrdersByStatusId(
            @PathVariable final Long statusId
    ) throws JsonProcessingException {

        log.info("Get All Order By StatusId {}", statusId);

        List<OrderResponseDTO> listOrders = this.iFindAllOrdersByStatusId.invoke(statusId);

        log.info("Get All Order Result {}", listOrders);

        AbstractApiResponseBuilderDTO<List<OrderResponseDTO>> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(listOrders, ApiResponseEnum.RESPONSE_OK_ALL_GET_ORDER);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }
}
