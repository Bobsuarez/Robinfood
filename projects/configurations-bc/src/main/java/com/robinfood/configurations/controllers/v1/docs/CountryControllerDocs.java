package com.robinfood.configurations.controllers.v1.docs;

import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CountryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Country", description = "Allows to get information of countries.")
public interface CountryControllerDocs {

    /**
     * Método que consulta el listado de países
     *
     * @return respuesta JSON con listado de países
     */
    @Operation(summary = "All countries info.")
    @ApiResponse(
        responseCode = "200", description = "Country info.",
        content = {
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ApiResponseDTO.class)
            )
        }
    )
    ResponseEntity<ApiResponseDTO<List<CountryDTO>>> findAllCountries();
}
