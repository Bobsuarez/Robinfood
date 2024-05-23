package com.robinfood.app.controllers.orderhistory;

import com.google.gson.Gson;
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase;
import com.robinfood.app.usecases.getorderstatushistorybyorderuuid.IGetOrderStatusHistoryByOrderUuidUseCase;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.orderstatushistory.OrderStatusHistoryDTO;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.ORDER_HISTORY;
import static com.robinfood.core.constants.APIConstants.ORDER_STATUS_HISTORY_BY_UUID;

/**
 * Implementation of IOrderHistoryController
 */
@RestController
@RequestMapping(ORDERS_V1)
@Slf4j
@Validated
public class OrderHistoryController implements IOrderHistoryController {

    private final IGetOrderHistoryUseCase getOrderHistoryUseCase;
    private final IGetOrderStatusHistoryByOrderUuidUseCase getOrderStatusHistoryUseCase;

    public OrderHistoryController(
            IGetOrderHistoryUseCase getOrderHistoryUseCase,
            IGetOrderStatusHistoryByOrderUuidUseCase getOrderStatusHistoryUseCase
    ) {
        this.getOrderHistoryUseCase = getOrderHistoryUseCase;
        this.getOrderStatusHistoryUseCase = getOrderStatusHistoryUseCase;
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
            @PathVariable Long storeId,
            String timeZone
    ) {

        AbstractApiResponseBuilderDTO<EntityDTO<OrderHistoryItemDTO>> apiResponseDTOBuilder;

        if (localDateStart.isAfter(localDateEnd)) {

            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("The local start date cannot be greater than the local end date");

            return new ResponseEntity<>(
                    apiResponseDTOBuilder.getApiResponseDTO(),
                    HttpStatus.BAD_REQUEST
            );
        }

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

        EntityDTO<OrderHistoryItemDTO> orderHistoryItemDTO = getOrderHistoryUseCase.invoke(orderHistoryRequestDTO);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(orderHistoryItemDTO);

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }

    @GetMapping(ORDER_STATUS_HISTORY_BY_UUID)
    @SneakyThrows
    public ResponseEntity<ApiResponseDTO<List<OrderStatusHistoryDTO>>> getOrderStatusHistory(
            @PathVariable String uuid
    ) {
        final AbstractApiResponseBuilderDTO<List<OrderStatusHistoryDTO>> apiResponseDTOBuilder;
        final List<OrderStatusHistoryDTO> orderStatusHistoryDTOS = getOrderStatusHistoryUseCase.invoke(uuid);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(orderStatusHistoryDTOS);

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.OK);
    }
}
