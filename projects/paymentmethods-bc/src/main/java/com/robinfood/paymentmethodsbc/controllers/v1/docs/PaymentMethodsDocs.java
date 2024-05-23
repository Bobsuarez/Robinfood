package com.robinfood.paymentmethodsbc.controllers.v1.docs;

import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.PaymentMethodDetailsDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Servicios de payment methods")
public interface PaymentMethodsDocs {
    @Operation(
        summary = "Permite obtener un listado de métodos de pago por tienda y flujo"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Listado de métodos de pago por tienda y flujo",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = String.class)
                    )
                }
            )
        }
    )
    List<PaymentMethodDetailsDTO> getPaymentMethodsByStoreAndChannelAndOrigin(
        @RequestParam(name = "store_id", required = true) Long storeId,
        @RequestParam(name = "channel_id", required = true) Long channelId,
        @RequestParam(name = "origin_id", required = true) Long originId
    )
        throws BaseException;
}
