package com.robinfood.app.controllers.statusorderhistory;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Size;
import java.util.List;

@Tag(
        name = "Get Status Order History",
        description = "Allows you to obtain status order history information"
)
public interface IStatusOrderHistoryController {

    @Operation(summary = "Get Status Order History")
    @ApiResponse(
            responseCode = "200", description = "Get Status Order History.",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDTO.class)
                    )
            }
    )
    ResponseEntity<ApiResponseDTO<List<StatusOrderHistoryDTO>>> invoke(
            @PathVariable
            @Size(min = 36, max = 36)
            @Parameter(
                    name = "uuid",
                    description = "Unique order identifier."
            ) String uuid
    );
}
