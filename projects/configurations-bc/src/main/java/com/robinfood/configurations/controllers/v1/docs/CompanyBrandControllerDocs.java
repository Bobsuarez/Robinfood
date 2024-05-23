package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyBrandResponseDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Company Brands", description = "Allows to get information of company brands.")
public interface CompanyBrandControllerDocs {

    @Operation(summary = "Company brands information.")
    @ApiResponse(responseCode = "200", description = "Company brands information.",
        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CompanyBrandResponseDTO.class))}
    )
    ResponseEntity<ApiResponseDTO<CompanyBrandResponseDTO>> findById(@PathVariable("id") Long id);

    @Operation(summary = "Company brands information.")
    @ApiResponse(responseCode = "200", description = "Company brands information.",
        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CompanyBrandResponseDTO.class))}
    )
    ResponseEntity<ApiResponseDTO<CompanyBrandResponseDTO>> findByMenuBrandId(
        @PathVariable("id") Long menuBrandId)
        throws JsonProcessingException, BusinessRuleException;
}
