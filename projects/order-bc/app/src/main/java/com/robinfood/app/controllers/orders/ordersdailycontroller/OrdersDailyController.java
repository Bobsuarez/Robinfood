package com.robinfood.app.controllers.orders.ordersdailycontroller;

import com.robinfood.app.usecases.getorderseeadytoinvoice.IGetOrdersDailyUseCase;
import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;

/**
 * Implementation of 'IOrdersDailyController'
 */
@RestController
@RequestMapping(ORDERS_V1 + "/{storeId}/daily")
@Slf4j
@Validated
public class OrdersDailyController implements IOrdersDailyController {

    private final IGetOrdersDailyUseCase getOrdersDailyUseCase;

    public OrdersDailyController(IGetOrdersDailyUseCase getOrdersDailyUseCase) {
        this.getOrdersDailyUseCase = getOrdersDailyUseCase;
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<List<OrderDailyDTO>>> invoke(
            @PathVariable(value = "storeId", required = true) Long storeId,
            @RequestHeader(name = "timeZone", required = true) String timeZone
    ) {
        AbstractApiResponseBuilderDTO<List<OrderDailyDTO>> apiResponseDTOBuilder;
        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();

        log.info("Receiving request orders daily storeId {} and ZoneTime {}", storeId, timeZone);

        List<OrderDailyDTO> orderDailyDTOS = this.getOrdersDailyUseCase.invoke(storeId, timeZone);

        log.info("Result Orders Daily UseCase {}", orderDailyDTOS);

        apiResponseDTOBuilder.build(orderDailyDTOS);

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

}
