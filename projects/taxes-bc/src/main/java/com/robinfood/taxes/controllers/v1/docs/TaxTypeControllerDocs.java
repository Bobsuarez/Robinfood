package com.robinfood.taxes.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateTaxTypeDTO;
import com.robinfood.taxes.dto.v1.model.TaxTypeDTO;
import com.robinfood.taxes.dto.v1.update.UpdateTaxTypeDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Tax Types")
public interface TaxTypeControllerDocs {

    @Operation(summary = "List tax types")
    @ApiResponse(
        responseCode = "200",
        description = "List tax types",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(
                    schema = @Schema(implementation = TaxTypeDTO.class)
                )
            )
        }
    )
    ResponseEntity<ApiResponseDTO<List<TaxTypeDTO>>> list(
        @Parameter(name = "country_id", description = "Country id to filter tax types list") Long countryId,
        @Parameter(name = "status", description = "Status to filter tax types list") Integer status

    );

    @Operation(summary = "Create tax type")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Tax type created successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TaxTypeDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<TaxTypeDTO>> create(
        @Parameter(name = "tax type", description = "Tax type to be created")
            CreateTaxTypeDTO createTaxTypeDTO) throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "Update a tax type")
    @ApiResponse(
        responseCode = "200",
        description = "Tax type updated successfully",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = TaxTypeDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<TaxTypeDTO>> update(
        @Parameter(name = "id", description = "Tax type id to be update") Long id,
        @Parameter(name = "updateTaxTypeDTO", description = "Update tax type data") UpdateTaxTypeDTO updateTaxDTO)
        throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "Delete a tax type")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tax type deleted successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    }
            )
    })
    ResponseEntity<ApiResponseDTO<String>> delete(@Parameter(name = "id") Long id)
            throws BusinessRuleException, JsonProcessingException;

}
