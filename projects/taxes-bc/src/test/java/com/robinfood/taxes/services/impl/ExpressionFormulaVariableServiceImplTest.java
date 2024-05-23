package com.robinfood.taxes.services.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.ExpressionFormulaVariable;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.repositories.ExpressionFormulaVariableRepository;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.FormulaVariableRepository;
import com.robinfood.taxes.services.AuditLogService;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpressionFormulaVariableServiceImplTest {

    @InjectMocks
    private ExpressionFormulaVariableServiceImpl expressionFormulaVariableService;

    @Mock
    private ExpressionFormulaVariableRepository expressionFormulaVariableRepository;

    @Mock
    private ExpressionRepository expressionRepository;

    @Mock
    private FormulaVariableRepository formulaVariableRepository;

    @Mock
    private AuditLogService auditLogService;

    @Test
    void test_Create_Should_ReturnExpressionFormulaVariable_When_CreatedSuccessfully()
        throws JsonProcessingException {

        when(expressionRepository.existsByIdAndDeletedAtIsNull(anyLong())).thenReturn(true);
        when(formulaVariableRepository.existsByIdAndDeletedAtIsNull(anyLong())).thenReturn(true);
        when(expressionFormulaVariableRepository
            .existsByExpressionIdAndFormulaVariableIdAndDeletedAtIsNull(anyLong(), anyLong()))
            .thenReturn(false);
        when(expressionFormulaVariableRepository.save(any(ExpressionFormulaVariable.class)))
            .thenReturn(getExpressionFormulaVariable());
        doNothing().when(auditLogService).createAuditLog(any(ExpressionFormulaVariable.class));

        assertAll(() -> expressionFormulaVariableService.create(getExpressionFormulaVariable()));

        verify(expressionRepository, times(1))
            .existsByIdAndDeletedAtIsNull(anyLong());
        verify(formulaVariableRepository, times(1))
            .existsByIdAndDeletedAtIsNull(anyLong());
        verify(expressionFormulaVariableRepository, times(1))
            .existsByExpressionIdAndFormulaVariableIdAndDeletedAtIsNull(anyLong(), anyLong());
        verify(expressionFormulaVariableRepository, times(1))
            .save(any(ExpressionFormulaVariable.class));
        verify(auditLogService, times(1)).createAuditLog(any(ExpressionFormulaVariable.class));
    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_ExpressionNotFound() {

        when(expressionRepository.existsByIdAndDeletedAtIsNull(anyLong())).thenReturn(false);

        //Call
        assertThatThrownBy(
            () -> expressionFormulaVariableService.create(getExpressionFormulaVariable()))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessage("Entity Expression with Id 1 not found");

        verify(expressionRepository, times(1))
            .existsByIdAndDeletedAtIsNull(anyLong());
    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_FormulaVariableNotFound() {

        when(expressionRepository.existsByIdAndDeletedAtIsNull(anyLong())).thenReturn(true);
        when(formulaVariableRepository.existsByIdAndDeletedAtIsNull(anyLong())).thenReturn(false);

        //Call
        assertThatThrownBy(
            () -> expressionFormulaVariableService.create(getExpressionFormulaVariable()))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessage("Entity Formula Variable with id 1 not found");

        verify(expressionRepository, times(1))
            .existsByIdAndDeletedAtIsNull(anyLong());
        verify(formulaVariableRepository, times(1))
            .existsByIdAndDeletedAtIsNull(anyLong());
    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_ExpressionAndFormulaVariableAlreadyExists() {

        when(expressionRepository.existsByIdAndDeletedAtIsNull(anyLong())).thenReturn(true);
        when(formulaVariableRepository.existsByIdAndDeletedAtIsNull(anyLong())).thenReturn(true);
        when(expressionFormulaVariableRepository
            .existsByExpressionIdAndFormulaVariableIdAndDeletedAtIsNull(anyLong(), anyLong()))
            .thenReturn(true);

        //Call
        assertThatThrownBy(
            () -> expressionFormulaVariableService.create(getExpressionFormulaVariable()))
            .isInstanceOf(BusinessRuleException.class)
            .hasMessage("Expression id 1 and formula variable id 1 already exists");

        verify(expressionRepository, times(1))
            .existsByIdAndDeletedAtIsNull(anyLong());
        verify(formulaVariableRepository, times(1))
            .existsByIdAndDeletedAtIsNull(anyLong());
        verify(expressionFormulaVariableRepository, times(1))
            .existsByExpressionIdAndFormulaVariableIdAndDeletedAtIsNull(anyLong(), anyLong());
    }

    private Expression getExpression() {
        Expression expression = Expression.builder()
            .value("value")
            .description("description")
            .status(1)
            .build();
        expression.setId(1L);
        return expression;
    }

    private FormulaVariable getFormulaVariable() {
        FormulaVariable formulaVariable = FormulaVariable.builder()
            .type("number")
            .name("variable")
            .value("1")
            .description("description")
            .build();
        formulaVariable.setId(1L);
        return formulaVariable;
    }

    private ExpressionFormulaVariable getExpressionFormulaVariable() {
        ExpressionFormulaVariable expressionFormulaVariable = ExpressionFormulaVariable.builder()
            .expression(getExpression())
            .formulaVariable(getFormulaVariable())
            .build();
        expressionFormulaVariable.setId(1L);
        return expressionFormulaVariable;
    }


}
