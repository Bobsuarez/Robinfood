package com.robinfood.taxes.controllers.v1.docs;

import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.model.RuleTypeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Rule Types")
public interface RuleTypeControllerDocs {

    @Operation(summary = "List rule types")
    @ApiResponse(
        responseCode = "200",
        description = "List rule types",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(
                    schema = @Schema(implementation = RuleTypeDTO.class)
                )
            )
        }
    )
    ResponseEntity<ApiResponseDTO<List<RuleTypeDTO>>> findAll();

}
