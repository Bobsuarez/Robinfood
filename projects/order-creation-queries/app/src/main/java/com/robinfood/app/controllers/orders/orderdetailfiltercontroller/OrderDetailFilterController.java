package com.robinfood.app.controllers.orders.orderdetailfiltercontroller;

import com.robinfood.app.usecases.orderdetailfilter.IOrderDetailFilterUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
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

        Result<EntityDTO<OrderDTO>> entityDTOResult = this.orderDetailFilterUseCase.invoke(
                currentPage,
                filterText,
                localDateEnd,
                localDateStart,
                perPage,
                storeId,
                timeZone
        );

        ApiResponseDTO<EntityDTO<OrderDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (entityDTOResult instanceof Result.Error) {
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) entityDTOResult).getException().getLocalizedMessage()
            );
            httpStatus = ((Result.Error) entityDTOResult).getHttpStatus();
        } else {
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<EntityDTO<OrderDTO>>) entityDTOResult).getData()
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
