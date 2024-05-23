package com.robinfood.app.controllers.storeorder;

import com.robinfood.app.usecases.getstoreorders.IGetStoreOrdersUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.storeorder.StoreOrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.STORE_ORDER;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Implementation of IStoreOrderController
 */
@RestController
@RequestMapping(ORDERS_V1)
public class StoreOrderController implements IStoreOrderController {

    private final IGetStoreOrdersUseCase getStoreOrdersUseCase;

    public StoreOrderController(IGetStoreOrdersUseCase getStoreOrdersUseCase) {
        this.getStoreOrdersUseCase = getStoreOrdersUseCase;
    }

    @Override
    @GetMapping(STORE_ORDER)
    public ResponseEntity<ApiResponseDTO<List<StoreOrderDTO>>> invoke(
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Long storeId,
            String timeZone
    ) {
        AbstractApiResponseBuilderDTO<List<StoreOrderDTO>> apiResponseDTOBuilder;

        if (localDateEnd.isBefore(localDateStart)) {

            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("The start date cannot be greater than the end date");
            return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
        }

        final long days = DAYS.between(localDateStart, localDateEnd);

        final long LIMIT_DAYS = 0L;
        if (days > LIMIT_DAYS) {
            apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
            apiResponseDTOBuilder.build("The range of days must be one");
            return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new ApiResponseDTO<>(getStoreOrdersUseCase.invoke(
                localDateEnd,
                localDateStart,
                storeId,
                timeZone
        )));
    }
}
