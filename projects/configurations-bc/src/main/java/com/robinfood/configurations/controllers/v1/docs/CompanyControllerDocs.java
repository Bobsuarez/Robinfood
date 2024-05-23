package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.CompanyResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyBrandDTO;
import com.robinfood.configurations.dto.v1.models.CompanyBrandResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyDTO;
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
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Company", description = "Allows to get information of company.")
public interface CompanyControllerDocs {

    @Operation(summary = "Company information.")
    @ApiResponse(responseCode = "200", description = "Company information.",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CompanyDTO.class))}
    )
    ResponseEntity<ApiResponseDTO<CompanyDTO>> findById(@PathVariable("companyId") Long companyId);

    @Operation(summary = "Company Brand List")
    @ApiResponse(
            responseCode = "200",
            description = "List of company brands obtained successfully",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CompanyBrandDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<PageResponseDTO<CompanyBrandResponseDTO>>> listCompanyBrands(
            @PathVariable(name = "id", required = true) Long id,
            @Parameter(name = "name", description = "String to filter by company brand name.") String name,
            @Parameter(name = "store_id", description = "Number to filter by Id from the store.") Long storeId,
            @Parameter(name = "page", description = "Page to get data, starting with 0. "
                    + "Omit wit size to get all data.") Integer page,
            @Parameter(name = "size",
                    description = "Page size to get data. Omit with page to get all data.") Integer size,
            @Parameter(name = "sort", description = "Criteria to sort company brands. All options support asc or"
                    + " desc order: name,asc") Sort sort
    ) throws JsonProcessingException;

    @Operation(summary = "information of all companies")
    @ApiResponse(
            responseCode = "200",
            description = "information of all companies",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CompanyResponseDTO.class))}
    )
    ResponseEntity<ApiResponseDTO<CompanyResponseDTO>> findAllCompanies(
            @Parameter(name = "status", description = "Status is active companies") Long status
    );


}
