package com.robinfood.app.controllers.paymentmethods;

import com.robinfood.core.dtos.PaymentMethodEntityDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Controller of payment methods information
 */
public interface IPaymentMethodsController {

    /**
     * Controller of the information to obtain the payment methods
     *
     * @return List of payment methods
     */
    @Operation(summary = "get all payment methods")
    @ApiResponse(
            responseCode = "200",
            description = "get all payment methods",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PaymentMethodEntityDTO.class))}
    )
    ResponseEntity<ApiResponseDTO<List<PaymentMethodEntityDTO>>> invoke();

}
