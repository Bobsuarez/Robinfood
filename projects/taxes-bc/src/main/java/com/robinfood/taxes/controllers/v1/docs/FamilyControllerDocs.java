package com.robinfood.taxes.controllers.v1.docs;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateFamilyDTO;
import com.robinfood.taxes.dto.v1.model.FamilyDTO;
import com.robinfood.taxes.dto.v1.update.UpdateFamilyDTO;
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
import org.springframework.http.ResponseEntity;

@Tag(name = "Families",
    description = "Allows to perform operations of list, create, update, and delete on Families.")
public interface FamilyControllerDocs {

    @Operation(summary = "List families by country and FamilyType.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Families retrieved successfully.",
            content = {
                @Content(mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                        schema = @Schema(implementation = FamilyDTO.class)
                    )
                )
            }
        )
    })
    ResponseEntity<ApiResponseDTO<List<FamilyDTO>>> list(
        @Parameter(name = "country_id",
            required = true, description = "CountryId to filter") Long countryId,
        @Parameter(name = "family_type_id",
            required = true, description = "FamilyTypeId to filter") Long familyTypeId,
        @Parameter(name = "status", description = "Status to filter active families") Integer status);

    @Operation(summary = "Create family.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Family created successfully.",
            content = {
                @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = FamilyDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<FamilyDTO>> create(
        @Parameter(name = "family", description = "Family to created")
            CreateFamilyDTO createFamily
    ) throws BusinessRuleException, JsonProcessingException;

    @Operation(summary = "Update family")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Family updated successfully",
            content = {
                @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = FamilyDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<FamilyDTO>> update(
        @Parameter(name = "id", description = "Family id to be updated") Long id,
        @Parameter(name = "updateFamilyDTO", description = "Update family data")
            UpdateFamilyDTO updateFamilyDTO
    ) throws BusinessRuleException, JsonProcessingException;

    @Operation(summary = "Delete family")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Family deleted successfully",
            content = {
                @Content(mediaType = APPLICATION_JSON_VALUE)
            }
        )
    })
    ResponseEntity<ApiResponseDTO<String>> delete(@Parameter(name = "id") Long id)
        throws BusinessRuleException, JsonProcessingException;

}
