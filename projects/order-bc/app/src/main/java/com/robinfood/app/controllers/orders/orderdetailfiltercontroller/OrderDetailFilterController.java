package com.robinfood.app.controllers.orders.orderdetailfiltercontroller;

import com.robinfood.app.usecases.orderdetailfilter.IOrderDetailFilterUseCase;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.dtos.response.PaginationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.ORDER_FILTER;

/**
 * Implementation of 'IOrderDetailFilterController'
 */
@RestController
@RequestMapping(ORDERS_V1 + ORDER_FILTER)
@Slf4j
@Validated
public class OrderDetailFilterController implements IOrderDetailFilterController {

    private final IOrderDetailFilterUseCase orderDetailFilterUseCase;

    public OrderDetailFilterController(IOrderDetailFilterUseCase orderDetailFilterUseCase) {
        this.orderDetailFilterUseCase = orderDetailFilterUseCase;
    }

    @GetMapping()
    @Override
    public ResponseEntity<ApiResponseDTO<EntityDTO<OrderDTO>>> invoke(
            Integer currentPage,
            String filterText,
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Integer perPage,
            Long storeId,
            String timeZone
    ) {

        log.info(
                "Receiving request orders filter " +
                        "currentPage {}, " +
                        "filterText {}, " +
                        "localDateEnd {}, " +
                        "localDateStart {}, " +
                        "perPage {}, " +
                        "storeId {} y " +
                        "timeZone {}",
                currentPage, filterText, localDateEnd, localDateStart, perPage, storeId, timeZone
        );

        Page<OrderDTO> orderDTOS = this.orderDetailFilterUseCase.invoke(
                currentPage,
                filterText,
                localDateEnd,
                localDateStart,
                perPage,
                storeId,
                timeZone
        );

        EntityDTO<OrderDTO> response = EntityDTO.<OrderDTO>builder()
                .items(orderDTOS.getContent())
                .pagination(
                        PaginationDTO.builder()
                                .perPage(perPage)
                                .lastPage(orderDTOS.getTotalPages())
                                .total(orderDTOS.getTotalElements())
                                .page(currentPage)
                                .build()
                )
                .build();

        return new ResponseEntity<>(new ApiResponseDTO<>(response), HttpStatus.OK);
    }

}
