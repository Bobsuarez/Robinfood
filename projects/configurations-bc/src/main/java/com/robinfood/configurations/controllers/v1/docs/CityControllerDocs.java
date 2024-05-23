package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CityDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "City", description = "Allows to get information of cities.")
public interface CityControllerDocs {

    /**
     * Método que consulta el listado de ciudades por pais
     *
     * @return respuesta JSON con listado de ciudades
     */
    @Operation(summary = "All cities info by country.")
    @ApiResponse(
        responseCode = "200", description = "Cities info.",
        content = {
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ApiResponseDTO.class)
            )
        }
    )
    ResponseEntity<ApiResponseDTO<List<CityDTOResponse>>> findAllCitiesByCountry(
        @PathVariable(name = "countryId") Long countryId
    );

    @Operation(summary = "List time zones by company country id.")
    @ApiResponse(responseCode = "200", description = "List time zone.",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = List.class))
        }
    )
    ResponseEntity<ApiResponseDTO<List<String>>> listTimeTimeZonesByCompanyCountryId(
        @Parameter(name = "company_country_id", description = "Field to filter by company country id.",
            required = true) Long storeId)
        throws JsonProcessingException;
}
