package com.robinfood.taxes.controllers.v1.docs;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.ArticleTaxDetailDTO;
import com.robinfood.taxes.dto.v1.create.CreateArticleFamilyDTO;
import com.robinfood.taxes.dto.v1.model.ArticleFamilyDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Article Families",
    description = "Allows to perform operations for Family, Article and Product Type relationship")
public interface ArticleFamilyControllerDocs {

    @Operation(summary = "List article families.")
    @ApiResponse(
        responseCode = "200",
        description = "List article families.",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = ArticleFamilyDTO.class))
            )
        }
    )
    ResponseEntity<ApiResponseDTO<Page<ArticleFamilyDTO>>> list(
        @Parameter(name = "product_type_id",
            description = "product_type_id to filter") Long productTypeId,
        @Parameter(name = "article_id",
            description = "article_id to filter") Long articleId,
        @Parameter(name = "status",
            description = "Status to filter active article families.") Integer status,
        @Parameter(name = "page") Integer page, @Parameter(name = "size") Integer size);

    @Operation(summary = "List detail article families.")
    @ApiResponse(
        responseCode = "200",
        description = "List detail article families.",
        content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = ArticleFamilyDTO.class))
            )
        }
    )
    ResponseEntity<ApiResponseDTO<List<ArticleTaxDetailDTO>>> listTaxDetail(
        @Parameter(name = "product_type_id",
            description = "product_type_id to filter") Long productTypeId,
        @Parameter(name = "article_id",
            description = "article_id to filter") Long articleId);

    @Operation(summary = "Create article family.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Article family created successfully.",
            content = {
                @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArticleTaxDetailDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<ArticleFamilyDTO>> create(
        @Parameter(name = "articleFamily", description = "Article family to created")
            CreateArticleFamilyDTO createArticleFamilyDTO
    ) throws BusinessRuleException, JsonProcessingException;

    @Operation(summary = "Delete article family.")
    @ApiResponse(
        responseCode = "200",
        description = "Article family deleted successfully",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        }
    )
    ResponseEntity<ApiResponseDTO<String>> delete(@Parameter(name = "id") Long id)
        throws BusinessRuleException, JsonProcessingException;

}
