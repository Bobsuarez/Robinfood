package com.robinfood.app.controllers.orders.orderpayment;

import com.google.gson.Gson;
import com.robinfood.app.usecases.getorderpayment.IGetOrderPaymentUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.orderpayment.DataOrderPaymentRequestDTO;
import com.robinfood.core.dtos.orderpayment.OrderPaymentDTO;
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
import static com.robinfood.core.constants.APIConstants.ORDER_PAYMENT;

@RestController
@RequestMapping(ORDERS_V1)
@Slf4j
public class OrderPaymentController implements IOrderPaymentController {

    private final IGetOrderPaymentUseCase getOrderPaymentUseCase;

    public OrderPaymentController(IGetOrderPaymentUseCase getPosResolutionUseCase) {
        this.getOrderPaymentUseCase = getPosResolutionUseCase;
    }

    @Override
    @GetMapping(ORDER_PAYMENT)
    public ResponseEntity<ApiResponseDTO<List<OrderPaymentDTO>>> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    ) {

        final DataOrderPaymentRequestDTO dataOrderPaymentRequestDTO = DataOrderPaymentRequestDTO
                .builder()
                .localDateStart(localDateStart)
                .localDateEnd(localDateEnd)
                .posId(posId)
                .timeZone(timeZone)
                .build();

        log.info("Receiving request get Order Payments {}", new Gson().toJson(dataOrderPaymentRequestDTO));

        final Result<List<OrderPaymentDTO>> paymentDTOResult =
                getOrderPaymentUseCase.invoke(dataOrderPaymentRequestDTO);

        ApiResponseDTO<List<OrderPaymentDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (paymentDTOResult instanceof Result.Error) {
            httpStatus = ((Result.Error) paymentDTOResult).getHttpStatus();
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) paymentDTOResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {
            httpStatus = HttpStatus.OK;
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<OrderPaymentDTO>>) paymentDTOResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
