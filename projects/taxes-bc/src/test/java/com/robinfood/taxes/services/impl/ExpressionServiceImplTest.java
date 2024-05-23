package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.repositories.ExpressionFormulaVariableRepository;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.services.AuditLogService;
import java.util.Arrays;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ExpressionServiceImplTest {

    @InjectMocks
    private ExpressionServiceImpl expressionService;

    @Mock
    private ExpressionRepository expressionRepository;

    @Mock
    private TaxRepository taxRepository;

    @Mock
    private ExpressionFormulaVariableRepository expressionFormulaVariableRepository;

    @Mock
    private AuditLogService auditLogService;

    @Test
    void test_Create_Should_ReturnExpression_When_CreatedSuccessfully()
        throws JsonProcessingException {
        when(expressionRepository.save(any(Expression.class))).thenReturn(getExpression());
        doNothing().when(auditLogService).createAuditLog(any(Expression.class));

        assertAll(() -> expressionService.create(getExpression()));

        verify(expressionRepository, times(1)).save(any(Expression.class));
        verify(auditLogService, times(1)).createAuditLog(any(Expression.class));
    }

    @Test
    void test_findAll_Should_ReturnAllExpressions_When_ActiveIsFalse() {
        // Given
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Expression expression = getExpression();
        Page<Expression> expressionPage = new PageImpl<>(Arrays.asList(expression));

        // When
        when(expressionRepository.findAll(any(Pageable.class))).thenReturn(expressionPage);
        expressionService.findAll(false, pageRequest);

        // Then
        verify(expressionRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void test_findAll_Should_ReturnActiveExpressions_When_ActiveIsTrue() {
        // Given
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Expression expression = getExpression();
        Page<Expression> expressionPage = new PageImpl<>(Arrays.asList(expression));

        // When
        when(expressionRepository.findAllByStatusAndDeletedAtIsNull(any(Integer.class),
            any(Pageable.class))).thenReturn(expressionPage);
        expressionService.findAll(true, pageRequest);

        // Then
        verify(expressionRepository, times(1))
            .findAllByStatusAndDeletedAtIsNull(any(Integer.class), any(Pageable.class));
    }

    @Test
    void test_delete_Should_DeleteExpression_When_GivenAValidId()
        throws BusinessRuleException, JsonProcessingException {
        // Given
        Long id = 1L;
        Expression expression = getExpression();

        // When
        when(expressionRepository.findById(anyLong())).thenReturn(Optional.of(expression));
        when(taxRepository.existsByExpressionIdAndDeletedAtIsNull(anyLong())).thenReturn(false);
        expressionService.delete(id);

        // Then
        verify(expressionRepository, times(1)).deleteById(anyLong());
        verify(auditLogService, times(1)).deleteAuditLog(any(Expression.class));
    }

    @Test
    void test_delete_Should_ThrowNotFoundException_When_ExpressionDoesntExist()
        throws BusinessRuleException, JsonProcessingException {
        // Given
        Long id = 1L;

        // When
        when(expressionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> expressionService.delete(id));

        // Then
        verify(expressionRepository, times(0)).deleteById(anyLong());
        verify(auditLogService, times(0)).deleteAuditLog(any(Expression.class));
    }

    @Test
    void test_delete_Should_ThrowBusinessException_When_ExpressionIsAssociatedToATax()
        throws BusinessRuleException, JsonProcessingException {
        // Given
        Long id = 1L;
        Expression expression = getExpression();

        // When
        when(taxRepository.existsByExpressionIdAndDeletedAtIsNull(anyLong())).thenReturn(true);
        assertThrows(BusinessRuleException.class, () -> expressionService.delete(id));

        // Then
        verify(taxRepository, times(1))
            .existsByExpressionIdAndDeletedAtIsNull(anyLong());
        verify(expressionRepository, times(0)).deleteById(anyLong());
        verify(auditLogService, times(0)).deleteAuditLog(any(Expression.class));
    }

    @Test
    void test_delete_Should_ThrowBusinessException_When_ExpressionIsAssociatedToAFormulaVariable()
        throws BusinessRuleException, JsonProcessingException {
        // Given
        Long id = 1L;
        Expression expression = getExpression();

        // When
        when(taxRepository.existsByExpressionIdAndDeletedAtIsNull(anyLong())).thenReturn(false);
        when(expressionFormulaVariableRepository.existsByExpressionIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(true);
        assertThrows(BusinessRuleException.class, () -> expressionService.delete(id));

        // Then
        verify(taxRepository, times(1))
            .existsByExpressionIdAndDeletedAtIsNull(anyLong());
        verify(expressionFormulaVariableRepository, times(1))
            .existsByExpressionIdAndDeletedAtIsNull(anyLong());
        verify(expressionRepository, times(0)).deleteById(anyLong());
        verify(auditLogService, times(0)).deleteAuditLog(any(Expression.class));
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

}
