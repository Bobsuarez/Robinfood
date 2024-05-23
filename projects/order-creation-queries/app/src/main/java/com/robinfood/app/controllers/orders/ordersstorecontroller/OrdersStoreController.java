package com.robinfood.app.controllers.orders.ordersstorecontroller;

import com.google.gson.Gson;
import com.robinfood.app.usecases.getordersstore.IGetOrdersStoreUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.ordersstore.DataOrderStoreRequestDTO;
import com.robinfood.core.dtos.ordersstore.OrderStoreDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.ORDER_STORE;

@RequestMapping(ORDERS_V1)
@RestController
@Slf4j
public class OrdersStoreController implements IOrdersStoreController {

    private final IGetOrdersStoreUseCase getOrdersStoreUseCase;

    public OrdersStoreController(IGetOrdersStoreUseCase getOrdersStoreUseCase) {
        this.getOrdersStoreUseCase = getOrdersStoreUseCase;
    }

    @Override
    @GetMapping(ORDER_STORE)
    public ResponseEntity<ApiResponseDTO<List<OrderStoreDTO>>> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long storeId,
            String timeZone
    ) {

        final DataOrderStoreRequestDTO dataOrderStoreRequestDTO = DataOrderStoreRequestDTO
                .builder()
                .localDateStart(localDateStart)
                .localDateEnd(localDateEnd)
                .storeId(storeId)
                .timeZone(timeZone)
                .build();

        log.info("Receiving request get Order Store {}", new Gson().toJson(dataOrderStoreRequestDTO));

        final Result<List<OrderStoreDTO>> orderStoreDTOResult =
                getOrdersStoreUseCase.invoke(dataOrderStoreRequestDTO);
        ApiResponseDTO<List<OrderStoreDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (orderStoreDTOResult instanceof Result.Error) {
            httpStatus = ((Result.Error) orderStoreDTOResult).getHttpStatus();
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) orderStoreDTOResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {
            httpStatus = HttpStatus.OK;
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<OrderStoreDTO>>) orderStoreDTOResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
