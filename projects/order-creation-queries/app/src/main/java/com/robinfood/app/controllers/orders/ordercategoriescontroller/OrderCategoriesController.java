package com.robinfood.app.controllers.orders.ordercategoriescontroller;

import com.google.gson.Gson;
import com.robinfood.app.usecases.getordercategory.IGetOrderCategoryUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.ordercategories.DataRequestOrderCategoryDTO;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
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
import static com.robinfood.core.constants.APIConstants.ORDER_REPORT_CATEGORIES;

@RestController
@RequestMapping(ORDERS_V1)
@Slf4j
public class OrderCategoriesController implements IOrderCategoriesController {

    private final IGetOrderCategoryUseCase iGetOrderCategoryUseCase;

    public OrderCategoriesController(IGetOrderCategoryUseCase iGetOrderCategoryUseCase) {
        this.iGetOrderCategoryUseCase = iGetOrderCategoryUseCase;
    }

    @Override
    @GetMapping(ORDER_REPORT_CATEGORIES)
    public ResponseEntity<ApiResponseDTO<List<OrderCategoryDTO>>> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    ) {

        final DataRequestOrderCategoryDTO
                dataRequestOrderCategoryDTO = DataRequestOrderCategoryDTO.builder()
                .localDateEnd(localDateEnd)
                .localDateStart(localDateStart)
                .posId(posId).timeZone(timeZone)
                .build();

        log.info("Receiving request get the orders grouped by categories {}",
                new Gson().toJson(dataRequestOrderCategoryDTO));

        final Result<List<OrderCategoryDTO>> entityDTOResult =
                iGetOrderCategoryUseCase.invoke(dataRequestOrderCategoryDTO);

        ApiResponseDTO<List<OrderCategoryDTO>> apiResponseDTO;
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
                    ((Result.Success<List<OrderCategoryDTO>>) entityDTOResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }

}
