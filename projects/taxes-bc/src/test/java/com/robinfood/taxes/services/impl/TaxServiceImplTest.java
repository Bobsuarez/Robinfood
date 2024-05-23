package com.robinfood.taxes.services.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.AbstractBaseEntity;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.FamilyRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.repositories.TaxRuleRepository;
import com.robinfood.taxes.repositories.TaxTypeRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaxServiceImplTest {

    @InjectMocks
    private TaxServiceImpl taxService;

    @Mock
    private TaxRepository taxRepository;

    @Mock
    private TaxRuleRepository taxRuleRepository;

    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private TaxTypeRepository taxTypeRepository;

    @Mock
    private ExpressionRepository expressionRepository;

    @Mock
    private AuditLogServiceImpl auditLogService;


    @Test
    void test_List_Should_ListTaxes_When_ReceiveValidFamilyId() {
        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax());

        when(taxRepository
            .findByFamilyIdAndDeletedAtIsNull(anyLong())).thenReturn(taxList);
        assertAll(() -> taxService.list(1L));

        verify(taxRepository, times(1)).findByFamilyIdAndDeletedAtIsNull(anyLong());

    }

    @Test
    void test_Create_Should_CreateAndReturnTax_When_ValidInput() throws JsonProcessingException {

        Family family = new Family(1L);
        family.setCountryId(1L);

        TaxType taxType = new TaxType(1L);
        taxType.setCountryId(1L);

        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        when(familyRepository.findById(1L)).thenReturn(Optional.of(family));
        when(expressionRepository.findById(1L)).thenReturn(Optional.of(expression));
        when(taxTypeRepository.findById(1L)).thenReturn(Optional.of(tax.getTaxType()));
        when(taxRepository.save(any(Tax.class))).thenReturn(tax);
        doNothing().when(auditLogService).createAuditLog(any(AbstractBaseEntity.class));

        assertAll(() -> taxService.create(tax));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(expressionRepository, times(1)).findById(anyLong());
        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).save(any(Tax.class));
        verify(auditLogService, times(1)).createAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_create_Should_ThrowEntityNotFoundException_When_FamilyDoesNotExists() {

        Family family = new Family(1L);
        family.setCountryId(1L);

        Tax tax = getTax();
        tax.setFamily(family);

        when(familyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taxService.create(tax))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessage("Family with id 1 not found.");

    }

    @Test
    void test_create_Should_ThrowEntityNotFoundException_When_ExpressionDoesNotExists() {

        Family family = new Family(1L);
        family.setCountryId(1L);

        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setExpression(expression);

        when(familyRepository.findById(1L)).thenReturn(Optional.of(family));
        when(expressionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taxService.create(tax))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessage("Expression with id 1 not found.");

    }

    @Test
    void test_create_Should_ThrowEntityNotFoundException_When_TaxTypeDoesNotExists() {

        Family family = new Family(1L);
        family.setCountryId(1L);

        TaxType taxType = new TaxType(1L);
        taxType.setCountryId(1L);

        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        when(familyRepository.findById(1L)).thenReturn(Optional.of(family));
        when(expressionRepository.findById(1L)).thenReturn(Optional.of(expression));
        when(taxTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taxService.create(tax))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessage("Tax type with id 1 not found.");

    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_FamilyAndTaxTypeAreFromDifferentCountries()
        throws JsonProcessingException {

        Family family = new Family(1L);
        family.setCountryId(2L);

        TaxType taxType = new TaxType(1L);
        taxType.setCountryId(1L);

        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        when(familyRepository.findById(1L)).thenReturn(Optional.of(family));
        when(expressionRepository.findById(1L)).thenReturn(Optional.of(expression));
        when(taxTypeRepository.findById(1L)).thenReturn(Optional.of(tax.getTaxType()));

        assertThatThrownBy(() -> taxService.create(tax))
            .isInstanceOf(BusinessRuleException.class)
            .hasMessage("Family and tax type are from different countries");


    }

    @Test
    void test_Update_Should_UpdateAndReturnTax_When_UpdateStatus() throws JsonProcessingException {

        Family family = new Family(1L);
        family.setCountryId(1L);

        TaxType taxType = new TaxType(1L);
        taxType.setCountryId(1L);

        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        Tax newTax = new Tax();
        newTax.setStatus(1);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(tax));
        when(taxRepository.save(any(Tax.class))).thenReturn(tax);
        doNothing().when(auditLogService).updateAuditLog(any(), any());

        assertAll(() -> taxService.update(1L, newTax));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).save(any(Tax.class));
        verify(auditLogService, times(1)).updateAuditLog(any(), any());

    }

    void test_Update_Should_UpdateAndReturnTax_When_ValidInput() throws JsonProcessingException {

        Family family = new Family(1L);
        family.setCountryId(1L);

        TaxType taxType = new TaxType(1L);
        taxType.setCountryId(1L);

        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        Family newFamily = new Family(2L);
        newFamily.setCountryId(2L);

        TaxType newTaxType = new TaxType(2L);
        newTaxType.setCountryId(2L);

        Expression newExpression = new Expression(2L);

        Tax newTax = getTax();
        newTax.setFamily(newFamily);
        newTax.setTaxType(newTaxType);
        newTax.setExpression(newExpression);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(tax));
        when(familyRepository.findById(2L)).thenReturn(Optional.of(newFamily));
        when(expressionRepository.findById(2L)).thenReturn(Optional.of(newExpression));
        when(taxTypeRepository.findById(2L)).thenReturn(Optional.of(newTaxType));
        when(taxRepository.save(any(Tax.class))).thenReturn(tax);
        doNothing().when(auditLogService).updateAuditLog(any(), any());

        assertAll(() -> taxService.update(1L, newTax));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(expressionRepository, times(1)).findById(anyLong());
        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1)).save(any(Tax.class));
        verify(auditLogService, times(1)).updateAuditLog(any(), any());

    }

    @Test
    void test_Update_Should_ThrowBusinessRuleException_When_FamilyAndTaxTypeAreFromDifferentCountries()
        throws JsonProcessingException {

        Family family = new Family(1L);
        family.setCountryId(1L);

        TaxType taxType = new TaxType(1L);
        taxType.setCountryId(1L);

        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        Family newFamily = new Family(2L);
        newFamily.setCountryId(1L);

        TaxType newTaxType = new TaxType(2L);
        newTaxType.setCountryId(2L);

        Expression newExpression = new Expression(2L);

        Tax newTax = getTax();
        newTax.setFamily(newFamily);
        newTax.setTaxType(newTaxType);
        newTax.setExpression(newExpression);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(tax));
        when(familyRepository.findById(2L)).thenReturn(Optional.of(newFamily));
        when(expressionRepository.findById(2L)).thenReturn(Optional.of(newExpression));
        when(taxTypeRepository.findById(2L)).thenReturn(Optional.of(newTaxType));

        assertThrows(BusinessRuleException.class, () -> taxService.update(1L, newTax));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(expressionRepository, times(1)).findById(anyLong());
        verify(taxTypeRepository, times(1)).findById(anyLong());

    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_TaxNotFound() {

        when(taxRepository.findById(1L)).thenReturn(Optional.ofNullable(any()));

        assertThrows(EntityNotFoundException.class, () -> taxService.update(1L, getTax()));

        verify(taxRepository, times(1)).findById(anyLong());

    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_FamilyNotFound() {

        Family family = new Family(1L);
        TaxType taxType = new TaxType(1L);
        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        Family newFamily = new Family(2L);
        TaxType newTaxType = new TaxType(2L);
        Expression newExpression = new Expression(2L);

        Tax newTax = getTax();
        newTax.setFamily(newFamily);
        newTax.setTaxType(newTaxType);
        newTax.setExpression(newExpression);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(tax));
        when(familyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any()));

        assertThrows(EntityNotFoundException.class, () -> taxService.update(1L, newTax));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(familyRepository, times(1)).findById(anyLong());

    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_TaxTypeNotFound() {

        Family family = new Family(1L);
        TaxType taxType = new TaxType(1L);
        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        Family newFamily = new Family(2L);
        TaxType newTaxType = new TaxType(2L);
        Expression newExpression = new Expression(2L);

        Tax newTax = getTax();
        newTax.setFamily(newFamily);
        newTax.setTaxType(newTaxType);
        newTax.setExpression(newExpression);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(tax));
        when(familyRepository.findById(2L)).thenReturn(Optional.of(newFamily));
        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any()));

        assertThrows(EntityNotFoundException.class, () -> taxService.update(1L, newTax));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(familyRepository, times(1)).findById(anyLong());
        verify(taxTypeRepository, times(1)).findById(anyLong());

    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_ExpressionNotFound() {

        Family family = new Family(1L);
        TaxType taxType = new TaxType(1L);
        Expression expression = new Expression(1L);

        Tax tax = getTax();
        tax.setFamily(family);
        tax.setTaxType(taxType);
        tax.setExpression(expression);

        Family newFamily = new Family(1L);
        TaxType newTaxType = new TaxType(1L);
        Expression newExpression = new Expression(2L);

        Tax newTax = getTax();
        newTax.setFamily(newFamily);
        newTax.setTaxType(newTaxType);
        newTax.setExpression(newExpression);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(tax));
        when(expressionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any()));

        assertThrows(EntityNotFoundException.class, () -> taxService.update(1L, newTax));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(expressionRepository, times(1)).findById(anyLong());

    }

    @Test
    void test_Delete_Should_DeleteTax_When_TaxExists()
        throws JsonProcessingException {

        when(taxRepository.findById(anyLong())).thenReturn(Optional.of(getTax()));
        when(taxRuleRepository
            .existsByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(false);
        doNothing().when(auditLogService).deleteAuditLog(any(AbstractBaseEntity.class));
        doNothing().when(taxRepository).delete(any(Tax.class));

        assertAll(() -> taxService.delete(1L));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(taxRuleRepository, times(1))
            .existsByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(taxRepository, times(1)).delete(any(Tax.class));
        verify(auditLogService, times(1)).deleteAuditLog(any(AbstractBaseEntity.class));
    }

    @Test
    void test_Delete_Should_ThrowEntityNotFoundException_When_TaxNotFound()
        throws JsonProcessingException {

        when(taxRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taxService.delete(1L));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(taxRuleRepository, times(0))
            .existsByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(taxRepository, times(0)).delete(any(Tax.class));
        verify(auditLogService, times(0)).deleteAuditLog(any(AbstractBaseEntity.class));
    }

    @Test
    void test_Delete_Should_ThrowBusinessRuleException_When_TaxIsActiveForTax_rules()
        throws JsonProcessingException {

        Tax tax = getTax();
        when(taxRepository.findById(anyLong())).thenReturn(Optional.of(tax));
        when(taxRuleRepository
            .existsByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> taxService.delete(1L));

        verify(taxRepository, times(1)).findById(anyLong());
        verify(taxRuleRepository, times(1))
            .existsByTaxIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(taxRepository, times(0)).delete(any(Tax.class));
        verify(auditLogService, times(0)).deleteAuditLog(any(AbstractBaseEntity.class));
    }

    private Tax getTax() {
        return Tax.builder()
            .value(BigDecimal.valueOf(10))
            .name("test")
            .description("test")
            .applyRules(true)
            .sapId("123")
            .status(1)
            .build();
    }
}
