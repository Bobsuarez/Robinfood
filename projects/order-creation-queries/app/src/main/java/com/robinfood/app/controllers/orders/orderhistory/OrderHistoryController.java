package com.robinfood.app.controllers.orders.orderhistory;

import com.google.gson.Gson;
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderhistory.response.OrderHistoryItemDTO;
import com.robinfood.core.dtos.orderhistory.request.OrderHistoryRequestDTO;
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
import static com.robinfood.core.constants.APIConstants.ORDER_HISTORY;

/**
 * Implementation of IOrderHistoryController
 */
@RestController
@RequestMapping(ORDERS_V1)
@Slf4j
public class OrderHistoryController implements IOrderHistoryController {

    private final IGetOrderHistoryUseCase getOrderHistoryUseCase;

    public OrderHistoryController(IGetOrderHistoryUseCase getOrderHistoryUseCase) {
        this.getOrderHistoryUseCase = getOrderHistoryUseCase;
    }

    @GetMapping(ORDER_HISTORY)
    @Override
    public ResponseEntity<ApiResponseDTO<EntityDTO<OrderHistoryItemDTO>>> getOrderHistoryByStore(
            List<Long> channelsId,
            Integer currentPage,
            Boolean isDelivery,
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Integer perPage,
            String searchText,
            Long storeId,
            String timeZone
    ) {

        final OrderHistoryRequestDTO orderHistoryRequestDTO = OrderHistoryRequestDTO.builder()
                .channelsId(channelsId)
                .currentPage(currentPage)
                .isDelivery(isDelivery)
                .localDateEnd(localDateEnd)
                .localDateStart(localDateStart)
                .perPage(perPage)
                .searchText(searchText)
                .storeId(storeId)
                .timeZone(timeZone)
                .build();

        log.info("Receiving request get order history by store: {}", new Gson().toJson(orderHistoryRequestDTO));

        Result<EntityDTO<OrderHistoryItemDTO>> entityDTOResult = getOrderHistoryUseCase.invoke(orderHistoryRequestDTO);

        ApiResponseDTO<EntityDTO<OrderHistoryItemDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (entityDTOResult instanceof Result.Error) {

            httpStatus = ((Result.Error) entityDTOResult).getHttpStatus();

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) entityDTOResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {

            httpStatus = HttpStatus.OK;

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<EntityDTO<OrderHistoryItemDTO>>) entityDTOResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
