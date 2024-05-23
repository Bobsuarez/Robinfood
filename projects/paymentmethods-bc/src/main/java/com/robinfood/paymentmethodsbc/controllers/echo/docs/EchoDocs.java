package com.robinfood.paymentmethodsbc.controllers.echo.docs;

import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Echo services")
public interface EchoDocs {
    /**
     * Servicio de pruebas
     * @param message mensaje a enviar
     * @return ResponseResultDTO<String>
     */
    @Operation(summary = "Servicio de echo")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Mensaje recibido",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    ResponseDTO<String> echo(
        @RequestParam(defaultValue = "Hello!") String message
    );
}
