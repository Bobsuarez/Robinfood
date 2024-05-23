package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.AbstractBaseEntity;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.repositories.TaxTypeRepository;
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
class TaxTypeServiceImplTest {

    @InjectMocks
    private TaxTypeServiceImpl taxTypeService;

    @Mock
    private TaxRepository taxRepository;

    @Mock
    private TaxTypeRepository taxTypeRepository;

    @Mock
    private AuditLogServiceImpl auditLogService;

    @Test
    void test_list_Should_ListTaxes_When_ReceiveValidCountryId() {
        List<TaxType> taxesList = new ArrayList<>();
        TaxType tax = new TaxType(1L);
        tax.setCountryId(1L);
        tax.setStatus(2);
        taxesList.add(tax);

        when(taxTypeRepository
            .findByCountryId(anyLong(), eq(null))).thenReturn(taxesList);

        assertAll(() -> taxTypeService.list(1L, null));

        verify(taxTypeRepository, times(1)).findByCountryId(anyLong(), eq(null));
    }

    @Test
    void test_list_Should_ListTaxes_When_ReceiveValidCountryIdAndStatus() {
        List<TaxType> taxesList = new ArrayList<>();
        TaxType tax = new TaxType(1L);
        tax.setCountryId(1L);
        tax.setStatus(2);
        taxesList.add(tax);

        when(taxTypeRepository
            .findByCountryId(anyLong(), any())).thenReturn(taxesList);

        assertAll(() -> taxTypeService.list(1L, 1));

        verify(taxTypeRepository, times(1)).findByCountryId(anyLong(), any());
    }

    @Test
    void test_Create_Should_CreateAndReturnTax_When_ValidInput() throws JsonProcessingException {

        TaxType tax = getTaxType();

        when(taxTypeRepository.existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong()))
            .thenReturn(false);
        when(taxTypeRepository.save(any(TaxType.class))).thenReturn(tax);
        doNothing().when(auditLogService).createAuditLog(any(AbstractBaseEntity.class));

        assertAll(() -> taxTypeService.create(tax));

        verify(taxTypeRepository, times(1))
            .existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong());
        verify(taxTypeRepository, times(1)).save(any(TaxType.class));
        verify(auditLogService, times(1)).createAuditLog(any(AbstractBaseEntity.class));

    }


    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_AlreadyExistingTaxNameForCountry()
        throws JsonProcessingException {

        TaxType tax = getTaxType();

        when(taxTypeRepository.existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong()))
            .thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> taxTypeService.create(tax));

        verify(taxTypeRepository, times(1))
            .existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong());
        verify(taxTypeRepository, times(0)).save(any(TaxType.class));
        verify(auditLogService, times(0)).createAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Update_Should_ThrowEntityNotFoundException_When_TaxNotFound()
        throws JsonProcessingException {

        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any()));

        assertThrows(EntityNotFoundException.class, () -> taxTypeService.update(1L, getTaxType()));

        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxTypeRepository, times(0))
            .existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong());
        verify(taxTypeRepository, times(0)).save(any(TaxType.class));
        verify(auditLogService, times(0))
            .updateAuditLog(any(AbstractBaseEntity.class), any(AbstractBaseEntity.class));

    }

    @Test
    void test_Update_Should_ThrowBusinessRuleException_When_NameAndCountryAlreadyExist()
        throws Exception {

        TaxType tax = TaxType.builder()
            .name("nameTest")
            .status(2)
            .build();

        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.of(getTaxType()));
        when(taxTypeRepository.existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong()))
            .thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> taxTypeService.update(1L, tax));

        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxTypeRepository, times(1))
            .existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong());
        verify(taxTypeRepository, times(0)).save(any(TaxType.class));
        verify(auditLogService, times(0))
            .updateAuditLog(any(AbstractBaseEntity.class), any(AbstractBaseEntity.class));
    }

    @Test
    void test_Update_Should_ThrowBusinessRuleException_When_StatusNotValid()
        throws Exception {

        TaxType tax = TaxType.builder()
            .name("nameTest")
            .status(3)
            .build();

        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.of(getTaxType()));
        when(taxTypeRepository.existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong()))
            .thenReturn(false);

        assertThrows(BusinessRuleException.class, () -> taxTypeService.update(1L, tax));

        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxTypeRepository, times(1))
            .existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong());
        verify(taxTypeRepository, times(0)).save(any(TaxType.class));
        verify(auditLogService, times(0))
            .updateAuditLog(any(AbstractBaseEntity.class), any(AbstractBaseEntity.class));
    }

    @Test
    void test_Update_Should_Tax_When_ReceivedValidTax() throws Exception {

        TaxType tax = TaxType.builder()
            .name("nameTest")
            .status(2)
            .build();

        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.of(getTaxType()));
        when(taxTypeRepository.existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong()))
            .thenReturn(false);

        assertAll(() -> taxTypeService.update(1L, tax));

        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxTypeRepository, times(1))
            .existsByNameAndCountryIdAndDeletedAtIsNull(anyString(), anyLong());
        verify(taxTypeRepository, times(1)).save(any(TaxType.class));
        verify(auditLogService, times(1))
            .updateAuditLog(any(), any());
    }

    @Test
    void test_Delete_Should_DeleteTaxType_When_ValidTaxTypeId()
            throws JsonProcessingException {

        TaxType taxType = getTaxType();
        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.of(taxType));
        when(taxRepository
                .existsByTaxTypeIdAndDeletedAtIsNull(anyLong()))
                .thenReturn(false);
        doNothing().when(auditLogService).deleteAuditLog(any(AbstractBaseEntity.class));
        doNothing().when(taxTypeRepository).delete(any(TaxType.class));

        assertAll(()-> taxTypeService.delete(1L));

        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1))
                .existsByTaxTypeIdAndDeletedAtIsNull(anyLong());
        verify(taxTypeRepository, times(1)).delete(any(TaxType.class));
        verify(auditLogService, times(1)).deleteAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Delete_Should_ThrowEntityNotFoundException_When_TaxTypeIdNotFound()
            throws JsonProcessingException {

        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> taxTypeService.delete(1L));

        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(0))
                .existsByTaxTypeIdAndDeletedAtIsNull(anyLong());
        verify(taxTypeRepository, times(0)).delete(any(TaxType.class));
        verify(auditLogService, times(0)).deleteAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Delete_Should_ThrowBusinessRuleException_When_TaxTypeIsActiveForTaxes()
            throws JsonProcessingException {

        TaxType taxType = getTaxType();
        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.of(taxType));
        when(taxRepository
                .existsByTaxTypeIdAndDeletedAtIsNull(anyLong()))
                .thenReturn(true);

        assertThrows(BusinessRuleException.class, ()-> taxTypeService.delete(1L));

        verify(taxTypeRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1))
                .existsByTaxTypeIdAndDeletedAtIsNull(anyLong());
        verify(taxTypeRepository, times(0)).delete(any(TaxType.class));
        verify(auditLogService, times(0)).deleteAuditLog(any(AbstractBaseEntity.class));

    }

    private TaxType getTaxType() {
        return TaxType.builder()
            .name("test")
            .countryId(1L)
            .status(1)
            .build();
    }
}
