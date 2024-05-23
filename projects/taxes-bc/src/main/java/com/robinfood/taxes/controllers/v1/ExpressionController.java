package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.GeneralConstants.ACTIVE_STATUS;
import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_EXPRESSIONS;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_EXPRESSIONS;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_EXPRESSIONS;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.ExpressionControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateExpressionDTO;
import com.robinfood.taxes.dto.v1.model.ExpressionDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.services.ExpressionService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/expressions")
public class ExpressionController implements ExpressionControllerDocs {

    private final ExpressionService expressionService;

    private final ModelMapper modelMapper;

    public ExpressionController(@NotNull final ExpressionService expressionService,
        @NotNull final ModelMapper modelMapper) {
        this.expressionService = expressionService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @Override
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + CREATE_EXPRESSIONS + "')")
    public ResponseEntity<ApiResponseDTO<ExpressionDTO>> create(
        @RequestBody @Valid CreateExpressionDTO createExpression) throws JsonProcessingException {
        log.trace("Transforming received DTO to entity object: {}.", createExpression);
        Expression expression = generateExpressionFromCreateExpressionDTO(createExpression);
        log.trace("Entity object created successfully: {}.", expression);

        Expression createdExpression = expressionService.create(expression);

        log.trace("Generating Expression DTO from created expression: {}.", createdExpression);
        ExpressionDTO expressionMapped = modelMapper.map(createdExpression, ExpressionDTO.class);
        log.trace("Expression DTO generated successfully: {}.", expressionMapped);

        log.trace("Creating ApiResponse object from created expression: {}.", expressionMapped);
        ResponseEntity<ApiResponseDTO<ExpressionDTO>>
            response = ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<ExpressionDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Expression created successfully.")
                .data(expressionMapped)
                .build());
        log.trace("ApiResponse object created successfully: {}.", response);

        return response;
    }

    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + DELETE_EXPRESSIONS + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable(name = "id") Long id)
        throws JsonProcessingException, BusinessRuleException {
        expressionService.delete(id);

        return ResponseEntity.ok(ApiResponseDTO.<String>builder()
            .code(HttpStatus.NO_CONTENT.value())
            .message("Expression deleted successfully")
            .build());
    }

    @BasicLog
    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_EXPRESSIONS + "')")
    public ResponseEntity<ApiResponseDTO<Page<ExpressionDTO>>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "false") boolean active) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Expression> expressionPage = expressionService.findAll(active, pageable);
        Page<ExpressionDTO> dtoPage = expressionPage.map(item ->
            modelMapper.map(item, ExpressionDTO.class));

        return ResponseEntity.ok(ApiResponseDTO.<Page<ExpressionDTO>>builder()
            .code(HttpStatus.OK.value())
            .message("Expressions retrieved successfully")
            .data(dtoPage)
            .build());
    }

    private static Expression generateExpressionFromCreateExpressionDTO(
        CreateExpressionDTO createExpression) {
        return Expression.builder()
            .value(createExpression.getValue())
            .description(createExpression.getDescription())
            .status(ACTIVE_STATUS)
            .build();
    }

}
