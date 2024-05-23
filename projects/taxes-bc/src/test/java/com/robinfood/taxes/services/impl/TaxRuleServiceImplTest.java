package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.AbstractBaseEntity;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxRule;
import com.robinfood.taxes.repositories.RuleTypeRepository;
import com.robinfood.taxes.repositories.RuleVariableRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.repositories.TaxRuleRepository;
import com.robinfood.taxes.services.AuditLogService;
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
class TaxRuleServiceImplTest {

    @InjectMocks
    private TaxRuleServiceImpl taxRuleService;

    @Mock
    private TaxRuleRepository taxRuleRepository;

    @Mock
    private TaxRepository taxRepository;

    @Mock
    private RuleTypeRepository ruleTypeRepository;

    @Mock
    private RuleVariableRepository ruleVariableRepository;

    @Mock
    private AuditLogService auditLogService;

    @Test
    void test_List_Should_ReturnTaxRule_When_MethodIsCalled() {

        PageImpl<TaxRule> page =
            new PageImpl<>(Collections.singletonList(getTaxRule()));

        when(taxRuleRepository
            .findByTaxIdAndStatusAndDeletedAtIsNull(any(), any(), any(Pageable.class)))
            .thenReturn(page);

        Page<TaxRule> responsePage =
            taxRuleService.list(null, null, PageRequest.of(0, 10));

        assertNotNull(responsePage);
        assertTrue(responsePage.hasContent());
        verify(taxRuleRepository, times(1))
            .findByTaxIdAndStatusAndDeletedAtIsNull(any(), any(), any(Pageable.class));

    }

    @Test
    void test_Create_Should_ReturnTaxRule_When_CreatedSuccessfully()
        throws JsonProcessingException {
        when(taxRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            Tax tax = new Tax(id);
            return Optional.of(tax);
        });
        when(ruleTypeRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            RuleType ruleType = new RuleType(id);
            return Optional.of(ruleType);
        });
        when(ruleVariableRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            RuleVariable ruleVariable = new RuleVariable(id);
            return Optional.of(ruleVariable);
        });
        when(taxRuleRepository
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong())).thenReturn(false);
        when(taxRuleRepository.save(any(TaxRule.class))).thenReturn(getTaxRule());
        doNothing().when(auditLogService).createAuditLog(any(TaxRule.class));

        assertAll(() -> taxRuleService.create(getTaxRule()));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(2)).findById(anyLong());
        verify(taxRuleRepository, times(1))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(1)).save(any(TaxRule.class));
        verify(auditLogService, times(1)).createAuditLog(any(TaxRule.class));
    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_TaxDoesNotExist()
        throws JsonProcessingException {
        when(taxRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.create(getTaxRule()));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(0)).findById(anyLong());
        verify(ruleVariableRepository, times(0)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).createAuditLog(any(TaxRule.class));
    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_RuleTypeDoesNotExist()
        throws JsonProcessingException {
        when(taxRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            Tax tax = new Tax(id);
            return Optional.of(tax);
        });
        when(ruleTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.create(getTaxRule()));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(0)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).createAuditLog(any(TaxRule.class));
    }

    @Test
    void test_Create_Should_ThrowBusinessException_When_LeftAndRightVariablesAreTheSame()
        throws JsonProcessingException {
        when(taxRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            Tax tax = new Tax(id);
            return Optional.of(tax);
        });
        when(ruleTypeRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            RuleType ruleType = new RuleType(id);
            return Optional.of(ruleType);
        });

        TaxRule taxRule = getTaxRule();
        taxRule.getLeftVariable().setId(1L);
        taxRule.getRightVariable().setId(1L);

        assertThrows(BusinessRuleException.class, () -> taxRuleService.create(taxRule));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(0)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).createAuditLog(any(TaxRule.class));
    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_LeftVariableDoesNotExist()
        throws JsonProcessingException {
        when(taxRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            Tax tax = new Tax(id);
            return Optional.of(tax);
        });
        when(ruleTypeRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            RuleType ruleType = new RuleType(id);
            return Optional.of(ruleType);
        });
        when(ruleVariableRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.create(getTaxRule()));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(1)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).createAuditLog(any(TaxRule.class));
    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_RightVariableDoesNotExist()
        throws JsonProcessingException {
        when(taxRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            Tax tax = new Tax(id);
            return Optional.of(tax);
        });
        when(ruleTypeRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            RuleType ruleType = new RuleType(id);
            return Optional.of(ruleType);
        });
        when(ruleVariableRepository.findById(1L)).thenReturn(Optional.of(new RuleVariable(1L)));
        when(ruleVariableRepository.findById(2L)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.create(getTaxRule()));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(2)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).createAuditLog(any(TaxRule.class));
    }

    @Test
    void test_Create_Should_Return404Code_When_RuleTypeDoesNotExist()
        throws JsonProcessingException {
        when(taxRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            Tax tax = new Tax(id);
            return Optional.of(tax);
        });
        when(ruleTypeRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            RuleType ruleType = new RuleType(id);
            return Optional.of(ruleType);
        });
        when(ruleVariableRepository.findById(anyLong())).thenAnswer(i -> {
            Long id = i.getArgument(0);
            RuleVariable ruleVariable = new RuleVariable(id);
            return Optional.of(ruleVariable);
        });
        when(taxRuleRepository
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong())).thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> taxRuleService.create(getTaxRule()));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(2)).findById(anyLong());
        verify(taxRuleRepository, times(1))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).createAuditLog(any(TaxRule.class));
    }

    @Test
    void test_Update_Should_ReturnTaxRule_When_UpdatedSuccessfully()
        throws JsonProcessingException {
        Tax tax = new Tax(1L);
        RuleType ruleType = new RuleType(1L);

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.of(getTaxRule()));
        when(taxRepository.findById(anyLong())).thenReturn(Optional.of(tax));
        when(ruleTypeRepository.findById(anyLong())).thenReturn(Optional.of(ruleType));
        when(ruleVariableRepository.findById(1L)).thenReturn(Optional.of(new RuleVariable(1L)));
        when(ruleVariableRepository.findById(2L)).thenReturn(Optional.of(new RuleVariable(2L)));

        when(taxRuleRepository
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong())).thenReturn(false);
        when(taxRuleRepository.save(any(TaxRule.class))).thenReturn(getTaxRule());
        doNothing().when(auditLogService).updateAuditLog(any(TaxRule.class), any(TaxRule.class));

        assertAll(() -> taxRuleService.update(1L, getTaxRule()));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(2)).findById(anyLong());
        verify(taxRuleRepository, times(1))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(1)).save(any(TaxRule.class));
        verify(auditLogService, times(1)).updateAuditLog(any(TaxRule.class), any(TaxRule.class));
    }

    @Test
    void test_Update_Should_ReturnTaxRule_When_FieldChanged()
        throws JsonProcessingException {
        TaxRule taxRule = new TaxRule();
        taxRule.setRuleType(new RuleType(2L));
        Tax tax = new Tax(1L);
        RuleType ruleType = new RuleType(1L);

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.of(getTaxRule()));
        when(taxRepository.findById(anyLong())).thenReturn(Optional.of(tax));
        when(ruleTypeRepository.findById(anyLong())).thenReturn(Optional.of(ruleType));
        when(ruleVariableRepository.findById(1L)).thenReturn(Optional.of(new RuleVariable(1L)));
        when(ruleVariableRepository.findById(2L)).thenReturn(Optional.of(new RuleVariable(2L)));

        when(taxRuleRepository
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong())).thenReturn(false);
        when(taxRuleRepository.save(any(TaxRule.class))).thenReturn(getTaxRule());
        doNothing().when(auditLogService).updateAuditLog(any(TaxRule.class), any(TaxRule.class));

        assertAll(() -> taxRuleService.update(1L, taxRule));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(2)).findById(anyLong());
        verify(taxRuleRepository, times(1))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(1)).save(any(TaxRule.class));
        verify(auditLogService, times(1)).updateAuditLog(any(TaxRule.class), any(TaxRule.class));
    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_TaxRuleDoesNotExist()
        throws JsonProcessingException {

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.update(1L, getTaxRule()));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(0)).findById(anyLong());
        verify(ruleTypeRepository, times(0)).findById(anyLong());
        verify(ruleVariableRepository, times(0)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).updateAuditLog(any(TaxRule.class), any(TaxRule.class));
    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_TaxDoesNotExist()
        throws JsonProcessingException {

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.of(getTaxRule()));
        when(taxRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.update(1L, getTaxRule()));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(0)).findById(anyLong());
        verify(ruleVariableRepository, times(0)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).updateAuditLog(any(TaxRule.class), any(TaxRule.class));
    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_RuleTypeDoesNotExist()
        throws JsonProcessingException {

        Tax tax = new Tax(1L);

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.of(getTaxRule()));
        when(taxRepository.findById(anyLong())).thenReturn(Optional.of(tax));
        when(ruleTypeRepository.findById(anyLong())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.update(1L, getTaxRule()));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(0)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).updateAuditLog(any(TaxRule.class), any(TaxRule.class));
    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_LeftVariableDoesNotExist()
        throws JsonProcessingException {

        Tax tax = new Tax(1L);
        RuleType ruleType = new RuleType(1L);

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.of(getTaxRule()));
        when(taxRepository.findById(anyLong())).thenReturn(Optional.of(tax));
        when(ruleTypeRepository.findById(anyLong())).thenReturn(Optional.of(ruleType));
        when(ruleVariableRepository.findById(1L)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.update(1L, getTaxRule()));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(1)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).updateAuditLog(any(TaxRule.class), any(TaxRule.class));
    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_RightVariableDoesNotExist()
        throws JsonProcessingException {

        Tax tax = new Tax(1L);
        RuleType ruleType = new RuleType(1L);

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.of(getTaxRule()));
        when(taxRepository.findById(anyLong())).thenReturn(Optional.of(tax));
        when(ruleTypeRepository.findById(anyLong())).thenReturn(Optional.of(ruleType));
        when(ruleVariableRepository.findById(1L)).thenReturn(Optional.of(new RuleVariable(1L)));
        when(ruleVariableRepository.findById(2L)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.update(1L, getTaxRule()));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(2)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).updateAuditLog(any(TaxRule.class), any(TaxRule.class));
    }

    @Test
    void test_Update_Should_ThrowBusinessException_When_LeftAndRightVariablesAreTheSame()
        throws JsonProcessingException {

        Tax tax = new Tax(1L);
        RuleType ruleType = new RuleType(1L);

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.of(getTaxRule()));
        when(taxRepository.findById(anyLong())).thenReturn(Optional.of(tax));
        when(ruleTypeRepository.findById(anyLong())).thenReturn(Optional.of(ruleType));

        TaxRule taxRule = getTaxRule();
        taxRule.getLeftVariable().setId(1L);
        taxRule.getRightVariable().setId(1L);

        assertThrows(BusinessRuleException.class, () -> taxRuleService.update(1L, taxRule));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).findById(anyLong());
        verify(ruleTypeRepository, times(1)).findById(anyLong());
        verify(ruleVariableRepository, times(0)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                anyLong(), anyLong(), anyLong(), anyLong());
        verify(taxRuleRepository, times(0)).save(any(TaxRule.class));
        verify(auditLogService, times(0)).updateAuditLog(any(TaxRule.class), any(TaxRule.class));

    }

    @Test
    void test_Delete_Should_DeleteTaxRule_When_ValidTaxRuleId()
        throws JsonProcessingException {

        TaxRule taxRule = getTaxRule();
        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.of(taxRule));
        doNothing().when(auditLogService).deleteAuditLog(any(AbstractBaseEntity.class));
        doNothing().when(taxRuleRepository).delete(any(TaxRule.class));

        assertAll(() -> taxRuleService.delete(1L));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRuleRepository, times(1)).delete(any(TaxRule.class));
        verify(auditLogService, times(1)).deleteAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Delete_Should_ThrowEntityNotFoundException_When_FamilyIdNotFound()
        throws JsonProcessingException {

        when(taxRuleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taxRuleService.delete(1L));

        verify(taxRuleRepository, times(1)).findById(anyLong());
        verify(taxRuleRepository, times(0)).delete(any(TaxRule.class));
        verify(auditLogService, times(0)).deleteAuditLog(any(AbstractBaseEntity.class));

    }

    private TaxRule getTaxRule() {
        TaxRule taxRule = TaxRule.builder()
            .tax(new Tax(1L))
            .ruleType(new RuleType(1L))
            .leftVariable(new RuleVariable(1L))
            .rightVariable(new RuleVariable(2L))
            .status(1)
            .build();
        return taxRule;
    }

}
