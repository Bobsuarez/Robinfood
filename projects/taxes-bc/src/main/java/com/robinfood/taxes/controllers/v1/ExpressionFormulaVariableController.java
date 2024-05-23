package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_EXPRESSION_FORMULA_VARIABLES;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.ExpressionFormulaVariableControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateExpressionFormulaVariableDTO;
import com.robinfood.taxes.dto.v1.model.ExpressionFormulaVariableDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.ExpressionFormulaVariable;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.services.ExpressionFormulaVariableService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/expression-formula-variables")
public class ExpressionFormulaVariableController implements
    ExpressionFormulaVariableControllerDocs {

    private final ExpressionFormulaVariableService expressionFormulaVariableService;
    private final ModelMapper modelMapper;

    public ExpressionFormulaVariableController(
        ExpressionFormulaVariableService expressionFormulaVariableService,
        ModelMapper modelMapper) {
        this.expressionFormulaVariableService = expressionFormulaVariableService;
        this.modelMapper = modelMapper;
    }


    @BasicLog
    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize(
        "hasRole('" + SERVICE + "') || hasRole('" + CREATE_EXPRESSION_FORMULA_VARIABLES + "')")
    public ResponseEntity<ApiResponseDTO<ExpressionFormulaVariableDTO>> create(
        @RequestBody @Valid CreateExpressionFormulaVariableDTO createExpressionFormulaVariableDTO)
        throws JsonProcessingException, BusinessRuleException {

        log.trace("Generating expression formula variable object from DTO {}",
            createExpressionFormulaVariableDTO);
        ExpressionFormulaVariable expressionFormulaVariable = generateExpressionFormulaVariableFromCreateDto(
            createExpressionFormulaVariableDTO);
        log.trace("Expression formula variable object generate successfully {}",
            expressionFormulaVariable);

        ExpressionFormulaVariable expressionFormulaVariableCreated = expressionFormulaVariableService
            .create(expressionFormulaVariable);
        log.trace("Expression formula variables created successfully on service {}",
            expressionFormulaVariableCreated);

        log.trace("Generating DTO from created expression formula variable {}",
            expressionFormulaVariableCreated);
        ExpressionFormulaVariableDTO expressionFormulaVariableDTO = modelMapper
            .map(expressionFormulaVariableCreated, ExpressionFormulaVariableDTO.class);
        log.trace("DTO generated successfully {}", expressionFormulaVariableDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<ExpressionFormulaVariableDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Expression formula variable created successfully")
                .data(expressionFormulaVariableDTO)
                .build());

    }

    private static ExpressionFormulaVariable generateExpressionFormulaVariableFromCreateDto(
        CreateExpressionFormulaVariableDTO createExpressionFormulaVariableDTO) {
        return ExpressionFormulaVariable.builder()
            .expression(new Expression(createExpressionFormulaVariableDTO.getExpressionId()))
            .formulaVariable(new FormulaVariable(
                createExpressionFormulaVariableDTO.getFormulaVariableId()))
            .build();
    }
}
