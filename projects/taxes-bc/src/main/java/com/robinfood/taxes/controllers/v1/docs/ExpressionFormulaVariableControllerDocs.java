package com.robinfood.taxes.controllers.v1.docs;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateExpressionFormulaVariableDTO;
import com.robinfood.taxes.dto.v1.model.ExpressionFormulaVariableDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Expression Formula Variables")
public interface ExpressionFormulaVariableControllerDocs {

    @Operation(summary = "Create a expression formula variable.")
    @ApiResponse(
        responseCode = "201",
        description = "Expression formula variable created successfully.",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ExpressionFormulaVariableDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<ExpressionFormulaVariableDTO>> create(
        @Parameter(name = "Expression formula variable", description = "Expression Formula Variable to create.")
            CreateExpressionFormulaVariableDTO createFormulaVariableExpressionDTO)
        throws JsonProcessingException, BusinessRuleException;
}
