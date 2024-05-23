package com.robinfood.configurations.controllers.v1.docs;

import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.ActivateOrDeactivateDTO;
import com.robinfood.configurations.dto.v1.ResponseResolutionsWithPosDTO;
import com.robinfood.configurations.dto.v1.StoreResolutionDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Tag(name = "Resolutions", description = "Allows the administration of resolutions of a pos")
public interface StoreResolutionControllerDocs {

    @Operation(summary = "Save resolutions")
    @ApiResponse(responseCode = "201", description = "Save resolution information",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseResolutionsWithPosDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<List<ResponseResolutionsWithPosDTO>>> createStoreResolutions(
            @Valid @RequestBody List<StoreResolutionDTO> storeResolutionDTOS
    );

    @Operation(summary = "update resolutions")
    @ApiResponse(responseCode = "202", description = "update resolution information accepted",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StoreResolutionDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<Object>> updateStoreResolutions(
            @Valid @RequestBody StoreResolutionDTO storeResolutionDTOS,
            @Parameter(name = "resolutionId", description = "resolution id", required = true)
            @Min(1) Long resolutionId
    ) throws BusinessRuleException;

    @Operation(summary = "Activate or deactivate resolutions")
    @ApiResponse(responseCode = "202", description = "Activate or deactivate resolutions information",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseResolutionsWithPosDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<String>> activateOrDeactivate(
            @PathVariable("id") Long id,
            @Valid @RequestBody ActivateOrDeactivateDTO isActiveDTO
    );
}
