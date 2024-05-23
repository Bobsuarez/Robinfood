package com.robinfood.taxes.controllers.v1.docs;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.TaxRuleCompleteDTO;
import com.robinfood.taxes.dto.v1.create.CreateTaxRuleDTO;
import com.robinfood.taxes.dto.v1.model.TaxRuleDTO;
import com.robinfood.taxes.dto.v1.update.UpdateTaxRuleDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;

@Tag(name = "Tax Rules", description = "Allow to perform CRUD operations on Tax Rules.")
public interface TaxRuleControllerDocs {

    @Operation(summary = "List tax rules")
    @ApiResponse(
        responseCode = "200",
        description = "List tax rules",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(
                    schema = @Schema(implementation = TaxRuleCompleteDTO.class)
                )
            )
        }
    )
    ResponseEntity<ApiResponseDTO<Page<TaxRuleCompleteDTO>>> list(
        @Parameter(name = "tax_id", description = "Tax id to filter tax rules list") Long taxId,
        @Parameter(name = "status", description = "Status to filter tax rules list") Integer status,
        @Parameter(name = "page") Integer page, @Parameter(name = "size") Integer size
    );

    @Operation(summary = "Create tax rule.")
    @ApiResponse(
        responseCode = "201",
        description = "Tax rule created successfully.",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = TaxRuleDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<TaxRuleDTO>> create(
        @Parameter(name = "tax_rule", description = "Tax rule to create.")
            CreateTaxRuleDTO createTaxRule
    ) throws BusinessRuleException, JsonProcessingException;

    @Operation(summary = "Update tax rule.")
    @ApiResponse(
        responseCode = "200",
        description = "Tax rule updated successfully.",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = TaxRuleDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<TaxRuleDTO>> update(
        @Parameter(name = "id", description = "Tax rule id to update") Long id,
        @Parameter(name = "tax_rule", description = "Tax rule to update.")
            UpdateTaxRuleDTO updateTaxRuleDTO
    ) throws BusinessRuleException, JsonProcessingException;

    @Operation(summary = "Delete tax rule")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tax rule deleted successfully",
            content = {
                @Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)
            }
        )
    })
    ResponseEntity<ApiResponseDTO<String>> delete(@Parameter(name = "id") Long id)
        throws BusinessRuleException, JsonProcessingException;

}
