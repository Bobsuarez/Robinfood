package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.components.AuthenticationFacade;
import com.robinfood.taxes.models.AbstractBaseEntity;
import com.robinfood.taxes.models.AuditLog;
import com.robinfood.taxes.repositories.AuditLogRepository;
import com.robinfood.taxes.security.User;
import java.time.LocalDateTime;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceImplTest {

    @InjectMocks
    private AuditLogServiceImpl auditLogService;

    @Mock
    private AuditLogRepository auditLogRepository;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Authentication authentication;


    @Test
    void test_Create_Should_CreateAuditLog_When_ReceiveValidParameters()
        throws JsonProcessingException {

        AbstractBaseEntity entity = getAbstractBaseEntity();
        User applicationUser = new User("123456", "", "", new HashSet<>());

        when(authentication.getPrincipal()).thenReturn(applicationUser);
        when(authenticationFacade.getAuthentication()).thenReturn(authentication);
        when(auditLogRepository.save(any(AuditLog.class)))
            .thenReturn(mock(AuditLog.class));

        assertAll(() -> auditLogService.createAuditLog(entity));
        verify(auditLogRepository,
            times(1)).save(any(AuditLog.class));
        verify(authenticationFacade, times(1)).getAuthentication();
    }

    @Test
    void test_Update_Should_UpdateAuditLog_When_ReceiveValidParameters()
        throws JsonProcessingException {

        AbstractBaseEntity entity = getAbstractBaseEntity();
        User applicationUser = new User("123456", "", "", new HashSet<>());

        when(authentication.getPrincipal()).thenReturn(applicationUser);
        when(authenticationFacade.getAuthentication()).thenReturn(authentication);

        when(auditLogRepository.save(any(AuditLog.class)))
            .thenReturn(mock(AuditLog.class));

        assertAll(() -> auditLogService.updateAuditLog(entity, entity));
        verify(auditLogRepository,
            times(1)).save(any(AuditLog.class));
        verify(authenticationFacade, times(1)).getAuthentication();
    }

    @Test
    void test_Delete_Should_DeleteAuditLog_When_ReceiveValidParameters() {

        AbstractBaseEntity entity = getAbstractBaseEntity();
        User applicationUser = new User("123456", "", "", new HashSet<>());

        when(authentication.getPrincipal()).thenReturn(applicationUser);
        when(authenticationFacade.getAuthentication()).thenReturn(authentication);

        when(auditLogRepository.save(any(AuditLog.class)))
            .thenReturn(mock(AuditLog.class));

        assertAll(() -> auditLogService.deleteAuditLog(entity));
        verify(auditLogRepository,
            times(1)).save(any(AuditLog.class));
        verify(authenticationFacade, times(1)).getAuthentication();
    }

    private AbstractBaseEntity getAbstractBaseEntity() {
        AbstractBaseEntity abstractBaseEntity = new AbstractBaseEntity(1L){};
        abstractBaseEntity.setCreatedAt(LocalDateTime.now());
        abstractBaseEntity.setUpdatedAt(LocalDateTime.now());

        return abstractBaseEntity;
    }

}


