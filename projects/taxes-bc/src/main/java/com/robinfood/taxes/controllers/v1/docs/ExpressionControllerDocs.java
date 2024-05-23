package com.robinfood.taxes.controllers.v1.docs;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateExpressionDTO;
import com.robinfood.taxes.dto.v1.model.ExpressionDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Expressions", description = "Allow to perform CRUD operations on Expressions.")
public interface ExpressionControllerDocs {

    @Operation(summary = "Create a expression.")
    @ApiResponse(
        responseCode = "201",
        description = "Expression created successfully.",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ExpressionDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<ExpressionDTO>> create(
        @Parameter(name = "expression", description = "Expression to create.")
            CreateExpressionDTO createExpression) throws JsonProcessingException;

    @Operation(summary = "Delete a expression")
    @ApiResponse(
        responseCode = "200",
        description = "Expression deleted successfully",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ApiResponseDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable(name = "id") Long id)
        throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "List expressions")
    @ApiResponse(
        responseCode = "200",
        description = "Expressions retrieved successfully",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = ExpressionDTO.class)))
        }
    )
    ResponseEntity<ApiResponseDTO<Page<ExpressionDTO>>> findAll(int page, int size, boolean active);
}
