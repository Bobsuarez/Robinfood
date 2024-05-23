package com.robinfood.taxes.services.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.AbstractBaseEntity;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.repositories.ExpressionFormulaVariableRepository;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.FormulaVariableRepository;
import java.util.Collections;
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
class FormulaVariableServiceImplTest {

    @InjectMocks
    private FormulaVariableServiceImpl formulaVariableService;

    @Mock
    private FormulaVariableRepository formulaVariableRepository;

    @Mock
    private ExpressionRepository expressionRepository;

    @Mock
    private AuditLogServiceImpl auditLogService;

    @Mock
    private ExpressionFormulaVariableRepository expressionFormulaVariableRepository;

    @Test
    void test_FindAll_Should_ReturnFormulaVariableList_When_MethodIsCalled() {

        PageImpl<FormulaVariable> page =
            new PageImpl<>(Collections.singletonList(getFormulaVariable()));

        when(formulaVariableRepository.deletedAtIsNull(any(Pageable.class))).thenReturn(page);

        Page<FormulaVariable> responsePage =
            formulaVariableService.findAll(PageRequest.of(0, 10));

        assertNotNull(responsePage);
        assertTrue(responsePage.hasContent());
        verify(formulaVariableRepository, times(1))
            .deletedAtIsNull(any(Pageable.class));

    }

    @Test
    void test_Create_Should_CreateAndReturnFormulaVariable_When_ValidInput()
        throws JsonProcessingException {

        //Data
        FormulaVariable formulaVariable = FormulaVariable.builder()
            .name("formula 1")
            .description("description")
            .type("number")
            .value("1")
            .build();

        //When
        when(formulaVariableRepository.existsByNameAndDeletedAtIsNull(anyString()))
            .thenReturn(false);
        when(formulaVariableRepository.save(any(FormulaVariable.class)))
            .thenReturn(formulaVariable);
        doNothing().when(auditLogService).createAuditLog(any(AbstractBaseEntity.class));

        //Call
        assertAll(() -> formulaVariableService.create(formulaVariable));

        verify(formulaVariableRepository, times(1)).existsByNameAndDeletedAtIsNull(anyString());
        verify(formulaVariableRepository, times(1)).save(any(FormulaVariable.class));
        verify(auditLogService, times(1)).createAuditLog(any(AbstractBaseEntity.class));
    }

    @Test
    void test_Create_Should_CreateAndReturnFormulaVariable_When_ValidValueWhenTypeExpression()
        throws JsonProcessingException {

        //Data
        FormulaVariable formulaVariable = FormulaVariable.builder()
            .name("formula 1")
            .description("description")
            .type("expression")
            .value("1")
            .build();

        //When
        when(expressionRepository.existsByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(true);
        when(formulaVariableRepository.existsByNameAndDeletedAtIsNull(anyString()))
            .thenReturn(false);
        when(formulaVariableRepository.save(any(FormulaVariable.class)))
            .thenReturn(formulaVariable);
        doNothing().when(auditLogService).createAuditLog(any(AbstractBaseEntity.class));

        //Call
        assertAll(() -> formulaVariableService.create(formulaVariable));

        verify(expressionRepository, times(1)).existsByIdAndDeletedAtIsNull(anyLong());
        verify(formulaVariableRepository, times(1)).existsByNameAndDeletedAtIsNull(anyString());
        verify(formulaVariableRepository, times(1)).save(any(FormulaVariable.class));
        verify(auditLogService, times(1)).createAuditLog(any(AbstractBaseEntity.class));
    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_TypeNotValid()
        throws JsonProcessingException {

        //Data
        FormulaVariable formulaVariable = FormulaVariable.builder()
            .name("formula 1")
            .description("description")
            .type("fake")
            .value("1")
            .build();

        //Call
        assertThatThrownBy(() -> formulaVariableService.create(formulaVariable))
            .isInstanceOf(BusinessRuleException.class)
            .hasMessage("Type fake not valid");
    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_InvalidValueWhenTypeExpression()
        throws JsonProcessingException {

        //Data
        FormulaVariable formulaVariable = FormulaVariable.builder()
            .name("formula 1")
            .description("description")
            .type("expression")
            .value("2")
            .build();

        //When
        when(expressionRepository.existsByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(false);

        //Call
        assertThatThrownBy(() -> formulaVariableService.create(formulaVariable))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessage("Expression with id: 2 not found");

        verify(expressionRepository, times(1)).existsByIdAndDeletedAtIsNull(anyLong());
    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_NameExists()
        throws JsonProcessingException {

        //Data
        FormulaVariable formulaVariable = FormulaVariable.builder()
            .name("Formula")
            .description("description")
            .type("expression")
            .value("2")
            .build();

        //When
        when(expressionRepository.existsByIdAndDeletedAtIsNull(anyLong()))
            .thenReturn(true);
        when(formulaVariableRepository.existsByNameAndDeletedAtIsNull(anyString()))
            .thenReturn(true);

        //Call
        assertThatThrownBy(() -> formulaVariableService.create(formulaVariable))
            .isInstanceOf(BusinessRuleException.class)
            .hasMessage("Name Formula already exists");

        verify(formulaVariableRepository, times(1)).existsByNameAndDeletedAtIsNull(anyString());

    }

    @Test
    void test_Delete_Should_DeleteFormulaVariable_When_FormulaVariableExists()
        throws JsonProcessingException {
        when(formulaVariableRepository.findById(anyLong()))
            .thenReturn(Optional.of(getFormulaVariable()));
        when(expressionFormulaVariableRepository
            .existsByFormulaVariableIdAndDeletedAtIsNull(anyLong())).thenReturn(false);
        doNothing().when(formulaVariableRepository).delete(any(FormulaVariable.class));
        doNothing().when(auditLogService).deleteAuditLog(any(FormulaVariable.class));

        assertAll(() -> formulaVariableService.delete(1L));

        verify(formulaVariableRepository, times(1)).findById(anyLong());
        verify(expressionFormulaVariableRepository, times(1))
            .existsByFormulaVariableIdAndDeletedAtIsNull(anyLong());
        verify(formulaVariableRepository, times(1)).delete(any(FormulaVariable.class));
        verify(auditLogService, times(1)).deleteAuditLog(any(FormulaVariable.class));
    }

    @Test
    void test_Delete_Should_ThrowEntityNotFoundException_When_FormulaVariableDoesNotExist()
        throws JsonProcessingException {
        when(formulaVariableRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> formulaVariableService.delete(1L));

        verify(formulaVariableRepository, times(1)).findById(anyLong());
        verify(expressionFormulaVariableRepository, times(0))
            .existsByFormulaVariableIdAndDeletedAtIsNull(anyLong());
        verify(formulaVariableRepository, times(0)).delete(any(FormulaVariable.class));
        verify(auditLogService, times(0)).createAuditLog(any(FormulaVariable.class));
    }

    @Test
    void test_Delete_Should_ThrowBusinessException_When_FormulaVariableIsAssociatedToExpressionFormulaVariable()
        throws JsonProcessingException {
        when(formulaVariableRepository.findById(anyLong()))
            .thenReturn(Optional.of(getFormulaVariable()));
        when(expressionFormulaVariableRepository
            .existsByFormulaVariableIdAndDeletedAtIsNull(anyLong())).thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> formulaVariableService.delete(1L));

        verify(formulaVariableRepository, times(1)).findById(anyLong());
        verify(expressionFormulaVariableRepository, times(1))
            .existsByFormulaVariableIdAndDeletedAtIsNull(anyLong());
        verify(formulaVariableRepository, times(0)).delete(any(FormulaVariable.class));
        verify(auditLogService, times(0)).createAuditLog(any(FormulaVariable.class));
    }

    private FormulaVariable getFormulaVariable() {
        return FormulaVariable.builder()
            .name("Name")
            .description("Desc")
            .type("Type")
            .value("Val")
            .build();
    }

}
