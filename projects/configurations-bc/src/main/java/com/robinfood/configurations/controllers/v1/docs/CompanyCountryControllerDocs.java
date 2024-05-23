package com.robinfood.configurations.controllers.v1.docs;

import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyCountryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CompanyCountryControllerDocs {
    /**
     * Método que consulta la marcas por compañia
     *
     * @return respuesta JSON con objeto de marca
     */
    @Operation(summary = "Get brand info by company.")
    @ApiResponse(
            responseCode = "200", description = "Brand info.",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDTO.class)
                    )
            }
    )
    ResponseEntity<ApiResponseDTO<CompanyCountryDTO>> findBrandByCompany(
            @PathVariable(name = "companyCountryId") Long companyCountryId,
            @PathVariable(name = "branId") Long branId);
}
