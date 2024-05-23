package com.robinfood.app.controllers.paymentmethods;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPaymentMethodsController {

    /**
     * Order details filter information payment methods
     * @returns the list of payment methods
     */
    ResponseEntity<ApiResponseDTO<List<PaymentMethodsFilterDTO>>> invoke();

}
