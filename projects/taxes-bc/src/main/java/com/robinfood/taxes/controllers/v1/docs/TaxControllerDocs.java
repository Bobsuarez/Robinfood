package com.robinfood.taxes.controllers.v1.docs;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.TaxCompleteDTO;
import com.robinfood.taxes.dto.v1.create.CreateTaxDTO;
import com.robinfood.taxes.dto.v1.model.TaxDTO;
import com.robinfood.taxes.dto.v1.model.TaxTypeDTO;
import com.robinfood.taxes.dto.v1.update.UpdateTaxDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Taxes")
public interface TaxControllerDocs {

    @Operation(summary = "List taxes")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "List Taxes successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TaxCompleteDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<List<TaxCompleteDTO>>> list(
        @Parameter(name = "family_id", description = "Family Id to filter Taxes") Long familyId)
        throws JsonProcessingException, BusinessRuleException;


    @Operation(summary = "Create tax")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Tax created successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TaxTypeDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<TaxDTO>> create(
        @Parameter(name = "tax", description = "Tax to be created")
            CreateTaxDTO createTaxDTO) throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "Update tax")
    @ApiResponse(
        responseCode = "200",
        description = "Tax updated successfully",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = TaxDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<TaxDTO>> update(
        @Parameter(name = "id", description = "Tax id to be update") Long id,
        @Parameter(name = "updateTaxDTO", description = "Update tax data") UpdateTaxDTO updateTaxDTO)
        throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "Delete tax")
    @ApiResponse(
        responseCode = "200",
        description = "Tax deleted successfully",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE)
        }
    )
    ResponseEntity<ApiResponseDTO<String>> delete(@Parameter(name = "id") Long id)
        throws BusinessRuleException, JsonProcessingException;

}
