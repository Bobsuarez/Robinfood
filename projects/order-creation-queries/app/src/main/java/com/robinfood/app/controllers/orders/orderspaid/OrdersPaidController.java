package com.robinfood.app.controllers.orders.orderspaid;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.getorderspaid.IGetOrdersPaidUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;
import com.robinfood.core.dtos.orderspaid.OrdersPaidResponseDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.core.constants.APIConstants.ORDER_PAID;
import static com.robinfood.core.constants.APIConstants.ORDER_PAID_V1;

@RequestMapping(ORDER_PAID_V1)
@RestController
@Slf4j
public class OrdersPaidController implements IOrdersPaidController{

    private final IGetOrdersPaidUseCase getOrdersPaidUseCase;

    public OrdersPaidController (IGetOrdersPaidUseCase getOrdersPaidUseCase) {
        this.getOrdersPaidUseCase = getOrdersPaidUseCase;
    }

    @Override
    @GetMapping(ORDER_PAID)
    @PreAuthorize(Permissions.OR_VIEW_HISTORY_CANCELED)
    public ResponseEntity<ApiResponseDTO<OrdersPaidResponseDTO>> invoke(
            DataOrdersPaidRequestDTO dataOrdersPaidRequestDTO,
            String timeZone
    ) {

        dataOrdersPaidRequestDTO.setTimezone(timeZone);

        final Result<OrdersPaidResponseDTO> ordersPaidResponseDTOResult = getOrdersPaidUseCase.invoke(
               dataOrdersPaidRequestDTO
        );

        ApiResponseDTO<OrdersPaidResponseDTO> apiResponseDTO;

        final HttpStatus httpStatus;

        if (ordersPaidResponseDTOResult instanceof Result.Error) {
            httpStatus = ((Result.Error) ordersPaidResponseDTOResult).getHttpStatus();
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) ordersPaidResponseDTOResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {
            httpStatus = HttpStatus.OK;
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<OrdersPaidResponseDTO>) ordersPaidResponseDTOResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
