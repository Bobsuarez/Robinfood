package com.robinfood.app.controllers.orders.ordersdailycontroller;

import com.robinfood.app.usecases.getordersdaily.IGetOrdersDailyUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;

/**
 * Implementation of IOrdersDailyController
 */
@RestController
@RequestMapping(ORDERS_V1 + "/{storeId}/daily")
@Slf4j
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
        log.info("Receiving request orders daily storeId {} and ZoneTime {}", storeId, timeZone);

        Result<List<OrderDailyDTO>> orderDailyDTOS = this.getOrdersDailyUseCase.invoke(storeId, timeZone);

        log.info("Result Orders Daily UseCase {}", orderDailyDTOS);

        ApiResponseDTO<List<OrderDailyDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (orderDailyDTOS instanceof Result.Error) {
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) orderDailyDTOS).getException().getLocalizedMessage()
            );
            httpStatus = ((Result.Error) orderDailyDTOS).getHttpStatus();
        } else {
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<OrderDailyDTO>>) orderDailyDTOS).getData()
            );
            httpStatus = HttpStatus.OK;
        }

        apiResponseDTO = new ApiResponseDTO<>(
                apiResponseDTO.getData(),
                httpStatus
        );

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
