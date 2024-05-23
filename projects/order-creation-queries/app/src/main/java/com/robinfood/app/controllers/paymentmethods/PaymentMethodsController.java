package com.robinfood.app.controllers.paymentmethods;

import com.robinfood.app.usecases.getlistpaymentmethods.IGetListPaymentMethodsUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.PAYMENT_METHODS;
import static com.robinfood.core.constants.APIConstants.PAYMENT_METHODS_V1;

@RestController
@RequestMapping(PAYMENT_METHODS_V1)
@Slf4j
public class PaymentMethodsController implements IPaymentMethodsController {

    private final IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase;

    public PaymentMethodsController(IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase) {
        this.getListPaymentMethodsUseCase = getListPaymentMethodsUseCase;
    }

    @Override
    @GetMapping(value = PAYMENT_METHODS)
    public ResponseEntity<ApiResponseDTO<List<PaymentMethodsFilterDTO>>> invoke() {

        log.info("Receiving request get list payment methods");

        Result<List<PaymentMethodsFilterDTO>> methodsFilterDTOResult = getListPaymentMethodsUseCase.invoke();

        ApiResponseDTO<List<PaymentMethodsFilterDTO>> apiResponseDTO;
        HttpStatus httpStatus;

        if (methodsFilterDTOResult instanceof Result.Error) {

            httpStatus = ((Result.Error) methodsFilterDTOResult).getHttpStatus();

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) methodsFilterDTOResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {

            httpStatus = HttpStatus.OK;

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<List<PaymentMethodsFilterDTO>>) methodsFilterDTOResult).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
