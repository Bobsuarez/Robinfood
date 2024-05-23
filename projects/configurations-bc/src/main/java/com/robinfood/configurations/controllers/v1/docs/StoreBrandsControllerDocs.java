package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.AssignBrandToStoreRequestDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.StoreBrand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "StoreBrands", description = "Allows to assign brands to stores")
public interface StoreBrandsControllerDocs {

    @Operation(summary = "Assign brand to store.")
    @ApiResponse(responseCode = "200",
            description = "Assign brand to store.",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDTO.class))
            }
    )
    @ApiResponse(responseCode = "409",
            description = "StoreBrand alredy exists.",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<StoreBrand>> insert(
            @Parameter(name = "store_id",
                    description = "Store identifier", required = true)
                    AssignBrandToStoreRequestDTO assignBrandToStoreRequestDTO)
            throws JsonProcessingException, BusinessRuleException;

}
