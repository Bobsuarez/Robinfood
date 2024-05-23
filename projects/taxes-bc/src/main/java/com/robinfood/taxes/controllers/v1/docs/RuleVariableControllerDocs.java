package com.robinfood.taxes.controllers.v1.docs;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateRuleVariableDTO;
import com.robinfood.taxes.dto.v1.model.RuleVariableDTO;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Rule Variables",
    description = "Allows to perform operations of list, create, update, and delete on Rule Variables.")
public interface RuleVariableControllerDocs {

    @Operation(summary = "List rule variables")
    @ApiResponse(
        responseCode = "200",
        description = "List rule variables successfully",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(
                    schema = @Schema(implementation = RuleVariableDTO.class)
                )
            )
        }
    )
    ResponseEntity<ApiResponseDTO<List<RuleVariableDTO>>> findAll();

    @Operation(summary = "Create rule variable.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Rule variable created successfully.",
            content = {
                @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RuleVariableDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<RuleVariableDTO>> create(
        @Parameter(name = "Rule variable", description = "Rule variable to be created")
            CreateRuleVariableDTO createRuleVariableDTO
    ) throws BusinessRuleException, JsonProcessingException;

    @Operation(summary = "Delete rule variable")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Rule variable deleted successfully",
            content = {
                @Content(mediaType = APPLICATION_JSON_VALUE)
            }
        )
    })
    ResponseEntity<ApiResponseDTO<String>> delete(@Parameter(name = "id") Long id)
        throws BusinessRuleException, JsonProcessingException;

}
