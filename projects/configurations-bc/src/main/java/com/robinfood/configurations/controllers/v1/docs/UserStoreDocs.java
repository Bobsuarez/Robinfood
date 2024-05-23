package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.UserStoreDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface UserStoreDocs {

    @Operation(summary = "Store info by user id.")
    @ApiResponse(responseCode = "200", description = "Store info.",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserStoreDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<UserStoreDTO>> findStoreByUserId(
            @Parameter(name = "userId", description = "User identifier",
                    required = true) Long userId)
            throws BusinessRuleException, JsonProcessingException;
}
