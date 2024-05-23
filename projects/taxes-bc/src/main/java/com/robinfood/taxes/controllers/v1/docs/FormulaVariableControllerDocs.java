package com.robinfood.taxes.controllers.v1.docs;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateFormulaVariableDTO;
import com.robinfood.taxes.dto.v1.model.FormulaVariableDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Formula variables")
public interface FormulaVariableControllerDocs {

    @Operation(summary = "List formula variables.")
    @ApiResponse(
        responseCode = "200",
        description = "List formula variables.",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = FormulaVariableDTO.class))
            )
        }
    )
    ResponseEntity<ApiResponseDTO<Page<FormulaVariableDTO>>> findAll(
        @Parameter(name = "page") Integer page, @Parameter(name = "size") Integer size);

    @Operation(summary = "Create formula variable.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Formula variable created successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = FormulaVariableDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<FormulaVariableDTO>> create(
        @Parameter(name = "Formula variable") CreateFormulaVariableDTO createFormulaVariableDTO)
        throws BusinessRuleException, JsonProcessingException;

    @Operation(summary = "Delete formula variable.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Formula variable deleted successfully.",
            content = {
                @Content(mediaType = APPLICATION_JSON_VALUE)
            }
        )
    })
    ResponseEntity<ApiResponseDTO<String>> delete(@Parameter(name = "id") Long id)
        throws BusinessRuleException, JsonProcessingException;

}
