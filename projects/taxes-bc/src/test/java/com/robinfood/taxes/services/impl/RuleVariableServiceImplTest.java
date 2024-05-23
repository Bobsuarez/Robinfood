package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.repositories.RuleVariableRepository;
import com.robinfood.taxes.repositories.TaxRuleRepository;
import java.time.LocalDateTime;
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
class RuleVariableServiceImplTest {

    @InjectMocks
    private RuleVariableServiceImpl ruleVariableService;

    @Mock
    private RuleVariableRepository ruleVariableRepository;

    @Mock
    private TaxRuleRepository taxRuleRepository;

    @Mock
    private AuditLogServiceImpl auditLogService;

    @Test
    void test_FindAll_Should_ListRuleVariables_When_ReceiveValidData() {

        List<RuleVariable> ruleVariableList = new ArrayList<>();

        RuleVariable ruleVariable = new RuleVariable();
        ruleVariable.setName("Test Name Rule Variable");
        ruleVariable.setDescription("Test Description Rule Variable");
        ruleVariable.setType("arrayNumber");
        ruleVariable.setPath("Test path");
        ruleVariable.setCreatedAt(LocalDateTime.now());
        ruleVariable.setUpdatedAt(LocalDateTime.now());
        ruleVariableList.add(ruleVariable);

        when(ruleVariableRepository.deletedAtIsNull()).thenReturn(ruleVariableList);

        assertAll(() -> ruleVariableService.findAll());

        verify(ruleVariableRepository, times(1)).deletedAtIsNull();
    }

    @Test
    void test_Create_Should_CreateRuleVariable_When_ValidInput_WithPath() throws JsonProcessingException {
        when(ruleVariableRepository.existsByPathAndNameAndDeletedAtIsNull( anyString(),  anyString()))
            .thenReturn(false);

        when(ruleVariableRepository.save(any(RuleVariable.class))).thenReturn(getRuleService("path"));
        doNothing().when(auditLogService).createAuditLog(any(RuleVariable.class));

        assertAll(() -> ruleVariableService.create(getRuleService("path")));

        verify(ruleVariableRepository, times(1))
            .existsByPathAndNameAndDeletedAtIsNull(anyString(), anyString());
        verify(ruleVariableRepository, times(1)).save(any(RuleVariable.class));
        verify(auditLogService, times(1)).createAuditLog(any(RuleVariable.class));
    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_ValidInput_WithPath_NameAlreadyExist() throws JsonProcessingException {
        when(ruleVariableRepository.existsByPathAndNameAndDeletedAtIsNull( anyString(),  anyString()))
            .thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> ruleVariableService.create(getRuleService("path")));

        verify(ruleVariableRepository, times(1))
            .existsByPathAndNameAndDeletedAtIsNull(anyString(), anyString());

    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_Path_Or_Value_Are_Null() {

        var ruleVariable = RuleVariable.builder()
            .name("Test Name Rule Variable")
            .description("Test Description Rule Variable")
            .type("arrayNumber")
            .build();

        assertThrows(BusinessRuleException.class, () -> ruleVariableService.create(ruleVariable));

    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_Path_Or_Value_AreNot_Null() {

        var ruleVariable = RuleVariable.builder()
            .name("Test Name Rule Variable")
            .description("Test Description Rule Variable")
            .type("arrayNumber")
            .path("Some path")
            .value("Some value")
            .build();

        assertThrows(BusinessRuleException.class, () -> ruleVariableService.create(ruleVariable));

    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_Is_Invalid_Type() {

        var ruleVariable = RuleVariable.builder()
            .name("Test Name Rule Variable")
            .description("Test Description Rule Variable")
            .type("doesnotexist")
            .path("Test path")
            .value(null)
            .build();

        assertThrows(BusinessRuleException.class, () -> ruleVariableService.create(ruleVariable));

    }

    @Test
    void test_Create_Should_CreateRuleVariable_When_ValidInput_WithValue() throws JsonProcessingException {

        when(ruleVariableRepository.save(any(RuleVariable.class))).thenReturn(getRuleService("value"));
        doNothing().when(auditLogService).createAuditLog(any(RuleVariable.class));

        assertAll(() -> ruleVariableService.create(getRuleService("value")));

        verify(ruleVariableRepository, times(1)).save(any(RuleVariable.class));
        verify(auditLogService, times(1)).createAuditLog(any(RuleVariable.class));
    }

    @Test
    void test_Delete_Should_DeleteRuleVariable_When_ValidIdReceived() throws Exception {

        when(ruleVariableRepository.findById(anyLong()))
            .thenReturn(Optional.of(getRuleService("path")));

        when(taxRuleRepository.existsByLeftVariableIdOrRightVariableId(anyLong()))
            .thenReturn(false);

        doNothing().when(ruleVariableRepository).delete(any(RuleVariable.class));

        doNothing().when(auditLogService).deleteAuditLog(any(AbstractBaseEntity.class));

        assertAll(()-> ruleVariableService.delete(1L));

        verify(ruleVariableRepository, times(1))
            .findById(anyLong());

        verify(taxRuleRepository, times(1))
            .existsByLeftVariableIdOrRightVariableId(anyLong());

        verify(ruleVariableRepository, times(1))
            .delete(any(RuleVariable.class));

        verify(auditLogService, times(1))
            .deleteAuditLog(any(AbstractBaseEntity.class));
    }

    @Test
    void test_Delete_Should_ThrowEntityNotFoundException_When_NoExistingIdReceived()
        throws Exception {

        when(ruleVariableRepository.findById(anyLong()))
            .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> ruleVariableService.delete(1L));

        verify(ruleVariableRepository, times(1))
            .findById(anyLong());

        verify(taxRuleRepository, times(0))
            .existsByLeftVariableIdOrRightVariableId(anyLong());

        verify(ruleVariableRepository, times(0))
            .delete(any(RuleVariable.class));

        verify(auditLogService, times(0))
            .deleteAuditLog(any(AbstractBaseEntity.class));
    }

    @Test
    void test_Delete_Should_ThrowBusinessRuleException_When_RuleVariableHasAssociations()
        throws Exception {

        when(ruleVariableRepository.findById(anyLong()))
            .thenReturn(Optional.of(getRuleService("path")));

        when(taxRuleRepository.existsByLeftVariableIdOrRightVariableId(anyLong()))
            .thenReturn(true);

        assertThrows(BusinessRuleException.class, ()-> ruleVariableService.delete(1L));

        verify(ruleVariableRepository, times(1))
            .findById(anyLong());

        verify(taxRuleRepository, times(1))
            .existsByLeftVariableIdOrRightVariableId(anyLong());

        verify(ruleVariableRepository, times(0))
            .delete(any(RuleVariable.class));

        verify(auditLogService, times(0))
            .deleteAuditLog(any(AbstractBaseEntity.class));
    }

    private RuleVariable getRuleService(String pathOrValue) {
        RuleVariable ruleVariable = null;
        if (pathOrValue.equals("path")) {
            ruleVariable = RuleVariable.builder()
                .name("Test Name Rule Variable")
                .description("Test Description Rule Variable")
                .type("arrayNumber")
                .path("Test path")
                .build();
        } else if (pathOrValue.equals("value")) {
            ruleVariable = RuleVariable.builder()
                .name("Test Name Rule Variable")
                .description("Test Description Rule Variable")
                .type("arrayString")
                .value("Test value")
                .build();
        }

        return ruleVariable;
    }

}
