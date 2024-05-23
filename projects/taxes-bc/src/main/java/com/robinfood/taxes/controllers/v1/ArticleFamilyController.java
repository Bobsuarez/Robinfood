package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.ACTIVE;
import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_ARTICLE_FAMILIES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_ARTICLE_FAMILIES;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_ARTICLE_FAMILIES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_ARTICLE_FAMILIES_TAX;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.ArticleFamilyControllerDocs;
import com.robinfood.taxes.domain.ArticleTaxDetail;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.ArticleTaxDetailDTO;
import com.robinfood.taxes.dto.v1.create.CreateArticleFamilyDTO;
import com.robinfood.taxes.dto.v1.model.ArticleFamilyDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.ArticleFamily;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.services.ArticleFamilyService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/article-families")
public class ArticleFamilyController implements ArticleFamilyControllerDocs {

    private final ArticleFamilyService articleFamilyService;

    private final ModelMapper modelMapper;

    public ArticleFamilyController(
        @NotNull final ArticleFamilyService articleFamilyService,
        @NotNull final ModelMapper modelMapper) {
        this.articleFamilyService = articleFamilyService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_ARTICLE_FAMILIES + "')")
    public ResponseEntity<ApiResponseDTO<Page<ArticleFamilyDTO>>> list(
        @RequestParam(value = "product_type_id", required = false) @Min(1) Long productTypeId,
        @RequestParam(value = "article_id", required = false) @Min(1) Long articleId,
        @RequestParam(value = "status", required = false) @Min(1) Integer status,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ArticleFamily> articleFamilies = articleFamilyService
            .list(productTypeId, articleId, status, pageable);
        log.trace("Article families obtained successfully from service. {}", articleFamilies);

        log.trace("Generating Article families dto from article families. {}", articleFamilies);
        Page<ArticleFamilyDTO> articleFamilyDTOList = articleFamilies
            .map(this::generateArticleFamilyDTOFromArticleFamily);
        log.trace("DTO created successfully {}.", articleFamilyDTOList);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<Page<ArticleFamilyDTO>>builder()
                .message("Article families retrieved successfully")
                .code(HttpStatus.OK.value())
                .data(articleFamilyDTOList)
                .build());
    }

    @BasicLog
    @Override
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_ARTICLE_FAMILIES_TAX + "')")
    public ResponseEntity<ApiResponseDTO<List<ArticleTaxDetailDTO>>> listTaxDetail(
        @RequestParam(value = "product_type_id") @Min(1) Long productTypeId,
        @RequestParam(value = "article_id") @Min(1) Long articleId) {

        List<ArticleTaxDetail> articleTaxDetails = articleFamilyService
            .findDetail(articleId, productTypeId);

        List<ArticleTaxDetailDTO> articleTaxDetailDTOS = articleTaxDetails.stream()
            .map(article -> modelMapper.map(article, ArticleTaxDetailDTO.class))
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<ArticleTaxDetailDTO>>builder()
                .message("Article tax detail retrieved successfully")
                .code(HttpStatus.OK.value())
                .data(articleTaxDetailDTOS)
                .build());
    }


    @BasicLog
    @Override
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + CREATE_ARTICLE_FAMILIES + "')")
    public ResponseEntity<ApiResponseDTO<ArticleFamilyDTO>> create(
        @RequestBody @Valid CreateArticleFamilyDTO createArticleFamilyDTO)
        throws BusinessRuleException, JsonProcessingException {

        log.trace("Transforming received CreateArticleFamilyDTO to entity object. {}",
            createArticleFamilyDTO);
        ArticleFamily articleFamily = generateArticleFamilyFromCreateDTO(createArticleFamilyDTO);
        log.trace("Entity object created successfully from CreateArticleFamilyDTO. {}",
            articleFamily);

        ArticleFamily createdArticleFamily = articleFamilyService.create(articleFamily);
        log.trace("Article family created successfully on service. {}", createArticleFamilyDTO);

        log.trace("Generating ArticleFamilyDTO from created article family");
        ArticleFamilyDTO articleFamilyDTO =
            generateArticleFamilyDTOFromArticleFamily(createdArticleFamily);
        log.trace("ArticleFamilyDTO generated successfully. {}", articleFamilyDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<ArticleFamilyDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Article family created successfully.")
                .data(articleFamilyDTO)
                .build());
    }

    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + DELETE_ARTICLE_FAMILIES + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable @Min(1) Long id)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Request received on controller to delete ARTICLE FAMILY with ID {}.", id);

        articleFamilyService.delete(id);
        log.trace("Article family deleted successfully on service");

        log.trace("Generating ApiResponse from deleted article family.");
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Article family deleted successfully")
                .build());
    }

    private ArticleFamily generateArticleFamilyFromCreateDTO(
        CreateArticleFamilyDTO createArticleFamilyDTO) {
        return ArticleFamily.builder()
            .articleId(createArticleFamilyDTO.getArticleId())
            .productTypeId(createArticleFamilyDTO.getProductTypeId())
            .family(new Family(createArticleFamilyDTO.getFamilyId()))
            .status(ACTIVE)
            .build();
    }

    private ArticleFamilyDTO generateArticleFamilyDTOFromArticleFamily(
        ArticleFamily articleFamily) {
        ArticleFamilyDTO articleFamilyDTO = modelMapper.map(articleFamily, ArticleFamilyDTO.class);
        articleFamilyDTO.setFamilyId(articleFamily.getFamily().getId());
        return articleFamilyDTO;
    }
}
