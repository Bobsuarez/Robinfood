package com.robinfood.app.controllers.paymentmethods;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionDTO;
import org.springframework.http.ResponseEntity;

public interface IPaymentMethodController {

    ResponseEntity<ApiResponseDTO<Object>> processPaymentMethodResult(TransactionDTO transaction);

}
