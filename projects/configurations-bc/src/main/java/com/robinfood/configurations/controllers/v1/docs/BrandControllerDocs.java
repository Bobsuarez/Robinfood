package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.BrandDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Brand", description = "Allows to get information of brand by companies.")
public interface BrandControllerDocs {
    /**
     * Método que consulta la marcas
     *
     * @return respuesta JSON de las marcas
     */
    @Operation(summary = "Get brands info.")
    @ApiResponse(
            responseCode = "200", description = "Brands info.",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDTO.class)
                    )
            }
    )
    ResponseEntity<ApiResponseDTO<PageResponseDTO<BrandDTO>>> findAll(
            @Parameter(name = "page", description = "Page to get data, starting with 1. "
                + "Omit wit size to get all data.") Integer page,
            @Parameter(name = "size",
                description = "Page size to get data. Omit with page to get all data.") Integer size,
            @Parameter(name = "enabled",
                    description = "Flag is active brands. Omit with all brands.") Boolean enabled,
            @Parameter(name = "sort", description = "Criteria to sort status. All options support asc or"
              + " desc order: name,asc") Sort sort)
    throws JsonProcessingException;
}
