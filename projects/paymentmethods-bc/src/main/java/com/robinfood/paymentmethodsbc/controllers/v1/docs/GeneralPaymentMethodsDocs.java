package com.robinfood.paymentmethodsbc.controllers.v1.docs;

import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.GeneralPaymentMethodDetailsDTO;
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
public interface GeneralPaymentMethodsDocs {

    @Operation(
        summary = "Permite obtener un listado de métodos de pago por tienda, canal, flujo y terminal"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Listado de métodos de pago disponibles",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = String.class)
                    )
                }
            )
        }
    )
    List<GeneralPaymentMethodDetailsDTO> getPaymentMethodsByStoreChannelFlowAndTerminal(
        @RequestParam(name = "storeId") Long storeId,
        @RequestParam(name = "channelId") Long channelId,
        @RequestParam(name = "flowId") Long flowId,
        @RequestParam(name = "terminalUuid", required = false) String terminalUuid
    );
}
