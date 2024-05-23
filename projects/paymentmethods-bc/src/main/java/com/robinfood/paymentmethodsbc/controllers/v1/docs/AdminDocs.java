package com.robinfood.paymentmethodsbc.controllers.v1.docs;

import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "Servicios de administración")
public interface AdminDocs {
    @Operation(
        summary = "Permite re-cachear la configuración de payment gateways"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Cache reiniciada exitosamente",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = String.class)
                    )
                }
            )
        }
    )
    ResponseDTO<String> cacheReset() throws BaseException;
}
