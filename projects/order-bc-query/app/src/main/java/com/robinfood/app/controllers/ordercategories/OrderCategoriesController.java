package com.robinfood.app.controllers.ordercategories;

import com.robinfood.app.usecases.getordercategory.IGetOrderCategoryUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_CATEGORIES;
import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@RequestMapping(ORDERS_V1)
public class OrderCategoriesController implements IOrderCategoriesController {

    private final IGetOrderCategoryUseCase getOrderCategoryUseCase;

    public OrderCategoriesController(IGetOrderCategoryUseCase getOrderCategoryUseCase) {
        this.getOrderCategoryUseCase = getOrderCategoryUseCase;
    }

    @Override
    @GetMapping(ORDERS_CATEGORIES)
    public ResponseEntity<ApiResponseDTO<List<OrderCategoryDTO>>> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    ) {

        AbstractApiResponseBuilderDTO<List<OrderCategoryDTO>> apiResponseDTOBuilder;

        if (localDateEnd.isBefore(localDateStart)) {

            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("The start date cannot be greater than the end date");
            return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
        }

        final long days = DAYS.between(localDateStart, localDateEnd);

        final long LIMIT_DAYS = 1L;
        if (days > LIMIT_DAYS) {
            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("The range of days must be one");
            return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
        }

        List<OrderCategoryDTO> categoryDTOList = getOrderCategoryUseCase.invoke(
                localDateStart,
                localDateEnd,
                posId,
                timeZone
        );

        return ResponseEntity.ok(new ApiResponseDTO<>(categoryDTOList));
    }
}
