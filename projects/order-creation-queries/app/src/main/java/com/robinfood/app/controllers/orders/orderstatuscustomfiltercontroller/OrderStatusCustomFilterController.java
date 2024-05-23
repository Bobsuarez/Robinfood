package com.robinfood.app.controllers.orders.orderstatuscustomfiltercontroller;

import com.robinfood.app.usecases.getliststatuscustomfilter.IGetStatusCustomFilterUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.StatusCustomFilterDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.STATUS_CUSTOM_FILTER;
import static com.robinfood.core.constants.APIConstants.STATUS_V1;

@RequestMapping(STATUS_V1)
@RestController
@Slf4j
public class OrderStatusCustomFilterController implements IOrderStatusCustomFilterController{

    public final IGetStatusCustomFilterUseCase getStatusCustomFilterUseCase;

    public OrderStatusCustomFilterController (IGetStatusCustomFilterUseCase getStatusCustomFilterUseCase) {
        this.getStatusCustomFilterUseCase = getStatusCustomFilterUseCase;
    }

    @Override
    @GetMapping(STATUS_CUSTOM_FILTER)
    public ResponseEntity<ApiResponseDTO<List<StatusCustomFilterDTO>>> invoke() {

        log.info("Receiving request get status filter");


        final Result<List<StatusCustomFilterDTO>> listStatusFilterDTOResult =
                getStatusCustomFilterUseCase.invoke();

        ApiResponseDTO<List<StatusCustomFilterDTO>> apiResponseDTO;

        HttpStatus httpStatus;

        if (listStatusFilterDTOResult instanceof Result.Error) {
            httpStatus = ((Result.Error) listStatusFilterDTOResult).getHttpStatus();
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) listStatusFilterDTOResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {
            httpStatus = HttpStatus.OK;
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<StatusCustomFilterDTO>>) listStatusFilterDTOResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
