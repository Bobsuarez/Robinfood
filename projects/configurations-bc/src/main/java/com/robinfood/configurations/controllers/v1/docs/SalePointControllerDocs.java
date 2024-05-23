package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.SalePointDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Sale Points", description = "Allows to get information of sale points.")
public interface SalePointControllerDocs {

    @Operation(summary = "Sale point info.")
    @ApiResponse(responseCode = "200",
        description = "Sale point info.",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = SalePointDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<SalePointDTO>> find(
        @Parameter(name = "pos_id", description = "POS identifier", required = true) Long posId,
        @Parameter(name = "brand_id", description = "Brand identifier") Long brandId)
        throws JsonProcessingException;
}
