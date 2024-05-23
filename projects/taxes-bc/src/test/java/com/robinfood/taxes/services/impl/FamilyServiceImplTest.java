package com.robinfood.taxes.services.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.repositories.FamilyRepository;
import com.robinfood.taxes.repositories.FamilyTypeRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FamilyServiceImplTest {

    @InjectMocks
    private FamilyServiceImpl familyService;

    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private FamilyTypeRepository familyTypeRepository;

    @Mock
    private TaxRepository taxRepository;

    @Mock
    private AuditLogServiceImpl auditLogService;

    @Test
    void test_List_Should_ReturnFamilies_When_ValidInput() {

        Family family = getFamily();

        when(familyRepository.findByCountryIdAndFamilyTypeIdAndStatus(anyLong(), anyLong(), anyInt()))
            .thenReturn(Collections.singletonList(family));

        assertAll(()-> familyService.list(1L, 1L, 1));

        verify(familyRepository, times(1))
            .findByCountryIdAndFamilyTypeIdAndStatus(anyLong(), anyLong(), anyInt());

    }

    @Test
    void test_List_Should_ReturnFamilies_When_OptionalParamsNotReceived() {

        Family family = getFamily();

        when(familyRepository
            .findByCountryIdAndFamilyTypeIdAndStatus(anyLong(), anyLong(), eq(null)))
            .thenReturn(Collections.singletonList(family));

        assertAll(() -> familyService.list(1L, 1L, null));

        verify(familyRepository, times(1))
            .findByCountryIdAndFamilyTypeIdAndStatus(anyLong(), anyLong(), eq(null));
    }

    @Test
    void test_Create_Should_CreateFamily_When_ValidInput() throws JsonProcessingException {
        when(familyTypeRepository.findById(anyLong())).thenReturn(Optional.of(familyTypeCreate()));
        when(familyRepository.existsByCountryIdAndNameAndDeletedAtIsNull(anyLong(), anyString()))
            .thenReturn(false);
        when(familyRepository.save(any(Family.class))).thenAnswer(i -> {
            Family family = i.getArgument(0);
            family.setId(1L);
            return family;
        });
        doNothing().when(auditLogService).createAuditLog(any(Family.class));

        assertAll(() -> familyService.create(getFamily()));

        verify(familyTypeRepository, times(1)).findById(anyLong());
        verify(familyRepository, times(1))
            .existsByCountryIdAndNameAndDeletedAtIsNull(anyLong(), anyString());
        verify(familyRepository, times(1)).save(any(Family.class));
        verify(auditLogService, times(1)).createAuditLog(any(Family.class));
    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_FamilyTypeDoesNotExist() {
        when(familyTypeRepository.findById(anyLong())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> familyService.create(getFamily()));

        verify(familyTypeRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_FamilyWithCountryAndNameAlreadyExist() {
        when(familyTypeRepository.findById(anyLong())).thenReturn(Optional.of(familyTypeCreate()));
        when(familyRepository.existsByCountryIdAndNameAndDeletedAtIsNull(anyLong(), anyString()))
            .thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> familyService.create(getFamily()));

        verify(familyTypeRepository, times(1)).findById(anyLong());
        verify(familyRepository, times(1))
            .existsByCountryIdAndNameAndDeletedAtIsNull(anyLong(), anyString());
    }

    @Test
    void test_UpdateFamily_Should_UpdateFamily_When_ValidInput() throws JsonProcessingException {

        FamilyType familyType = FamilyType.builder()
            .name("test family type")
            .build();
        familyType.setId(2L);

        Family family = Family.builder()
            .name("test")
            .countryId(1L)
            .familyType(new FamilyType(1L))
            .status(1)
            .build();
        family.setId(1L);
        family.setCreatedAt(LocalDateTime.now());
        family.setUpdatedAt(LocalDateTime.now());

        Family newFamily = Family.builder()
            .name("new test")
            .countryId(2L)
            .familyType(familyType)
            .status(2)
            .build();
        newFamily.setId(2L);

        when(familyRepository.findById(anyLong()))
            .thenReturn(Optional.of(family));

        when(familyRepository
            .existsByCountryIdAndNameAndDeletedAtIsNullAndIdNot(anyLong(), anyString(), anyLong()))
            .thenReturn(false);

        when(familyRepository.save(any(Family.class)))
            .thenReturn(newFamily);

        when(familyTypeRepository.findById(anyLong()))
            .thenReturn(Optional.of(familyType));

        doNothing().when(auditLogService).updateAuditLog(any(), any());

        assertAll(() -> familyService.update(1L, newFamily));

        verify(familyRepository,
            times(1)).findById(anyLong());
        verify(familyRepository,
            times(1))
            .existsByCountryIdAndNameAndDeletedAtIsNullAndIdNot(anyLong(), anyString(), anyLong());
        verify(familyRepository,
            times(1)).save(any(Family.class));
        verify(auditLogService, times(1))
            .updateAuditLog(any(), any());
        verify(familyTypeRepository,
            times(1)).findById(anyLong());
    }

    @Test
    void test_UpdateFamily_Should_ThrowEntityNotFoundException_When_FamilyNotExist() {
        Family family = Family.builder()
            .name("test")
            .countryId(6L)
            .familyType(new FamilyType(6L))
            .status(1)
            .build();
        family.setId(1L);
        family.setCreatedAt(LocalDateTime.now());
        family.setUpdatedAt(LocalDateTime.now());

        when(familyRepository.findById(anyLong()))
            .thenReturn(Optional.ofNullable(any()));

        assertThrows(EntityNotFoundException.class, () -> familyService.update(3L, family));
        verify(familyRepository, times(1)).findById(anyLong());

    }

    @Test
    void test_UpdateFamily_Should_ThrowBusinessRuleException_When_NameIsNotUnique() {
        Family family = Family.builder()
            .name("test")
            .countryId(1L)
            .familyType(new FamilyType(1L))
            .status(1)
            .build();
        family.setId(1L);
        family.setCreatedAt(LocalDateTime.now());
        family.setUpdatedAt(LocalDateTime.now());

        Family newFamily = Family.builder()
            .name("test exist")
            .countryId(1L)
            .familyType(new FamilyType(1L))
            .status(1)
            .build();
        family.setId(1L);
        family.setCreatedAt(LocalDateTime.now());
        family.setUpdatedAt(LocalDateTime.now());

        when(familyRepository.findById(anyLong()))
            .thenReturn(Optional.of(family));

        when(familyRepository
            .existsByCountryIdAndNameAndDeletedAtIsNullAndIdNot(anyLong(), anyString(), anyLong()))
            .thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> familyService.update(1L, newFamily));

        verify(familyRepository,
            times(1)).findById(anyLong());
        verify(familyRepository,
            times(1))
            .existsByCountryIdAndNameAndDeletedAtIsNullAndIdNot(anyLong(), anyString(), anyLong());
    }

    @Test
    void test_Update_Should_UpdateFamily_When_Family_NotFound() {

        Family family = Family.builder()
            .name("test")
            .countryId(1L)
            .familyType(new FamilyType(1L))
            .status(1)
            .build();
        family.setId(1L);
        family.setCreatedAt(LocalDateTime.now());
        family.setUpdatedAt(LocalDateTime.now());

        when(familyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any()));

        assertThatThrownBy(() -> familyService.update(1L, family))
            .isInstanceOf(EntityNotFoundException.class);
        verify(familyRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_Update_Should_UpdateFamily_When_FamilyTypeNotFound() {

        Family family = Family.builder()
            .name("test")
            .countryId(1L)
            .familyType(new FamilyType(1L))
            .status(1)
            .build();
        family.setId(1L);
        family.setCreatedAt(LocalDateTime.now());
        family.setUpdatedAt(LocalDateTime.now());

        Family newFamily = Family.builder()
            .familyType(new FamilyType(5L))
            .build();

        when(familyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(family));

        assertThatThrownBy(() -> familyService.update(1L, newFamily))
            .isInstanceOf(EntityNotFoundException.class);
        verify(familyRepository, times(1)).findById(anyLong());
        verify(familyTypeRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_Delete_Should_DeleteFamily_When_ValidFamilyId()
        throws JsonProcessingException {

        Family family = getFamily();
        when(familyRepository.findById(anyLong())).thenReturn(Optional.of(family));
        when(taxRepository
            .existsByFamilyIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(false);
        doNothing().when(auditLogService).deleteAuditLog(any(AbstractBaseEntity.class));
        doNothing().when(familyRepository).delete(any(Family.class));

        assertAll(()-> familyService.delete(1L));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1))
            .existsByFamilyIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(familyRepository, times(1)).delete(any(Family.class));
        verify(auditLogService, times(1)).deleteAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Delete_Should_ThrowEntityNotFoundException_When_FamilyIdNotFound()
        throws JsonProcessingException {

        when(familyRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> familyService.delete(1L));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(0))
            .existsByFamilyIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(familyRepository, times(0)).delete(any(Family.class));
        verify(auditLogService, times(0)).deleteAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Delete_Should_ThrowBusinessRuleException_When_FamilyIsActiveForTaxes()
        throws JsonProcessingException {

        Family family = getFamily();
        when(familyRepository.findById(anyLong())).thenReturn(Optional.of(family));
        when(taxRepository
            .existsByFamilyIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt()))
            .thenReturn(true);

        assertThrows(BusinessRuleException.class, ()-> familyService.delete(1L));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(taxRepository, times(1))
            .existsByFamilyIdAndStatusAndDeletedAtIsNull(anyLong(), anyInt());
        verify(familyRepository, times(0)).delete(any(Family.class));
        verify(auditLogService, times(0)).deleteAuditLog(any(AbstractBaseEntity.class));

    }

    private Family getFamily() {

        FamilyType familyType = FamilyType.builder()
            .name("Family type")
            .build();
        familyType.setId(1L);

        Family family = Family.builder()
            .countryId(1L)
            .familyType(familyType)
            .name("Family")
            .status(1)
            .build();
        family.setId(1L);

        return family;
    }

    private FamilyType familyTypeCreate() {
        FamilyType familyType = new FamilyType(1L);
        familyType.setName("name");
        return familyType;
    }

}
