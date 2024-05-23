package com.robinfood.configurations.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.components.AuthenticationFacade;
import com.robinfood.configurations.dto.v1.security.UserDTO;
import com.robinfood.configurations.models.AuditLog;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.Country;
import com.robinfood.configurations.models.State;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreIdentifierType;
import com.robinfood.configurations.models.StoreType;
import com.robinfood.configurations.models.Zone;
import com.robinfood.configurations.repositories.jpa.AuditLogRepository;
import java.time.LocalDateTime;
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

    private static final LocalDateTime CURRENT_DATE = LocalDateTime.MIN;
    private static final String TEST = "TEST";
    private static final Long TEST_LONG = 1L;

    @Test
    void test_Create_Should_CreateAuditLog_When_ReceiveValidParameters() {
        Store store = buildStoreAllParams();
        UserDTO applicationUser = getUserDTO();

        when(authentication.getPrincipal()).thenReturn(applicationUser);
        when(authenticationFacade.getAuthentication()).thenReturn(authentication);
        when(auditLogRepository.save(any(AuditLog.class))).thenReturn(mock(AuditLog.class));

        assertAll(() -> auditLogService.createAuditLog(store));
        verify(auditLogRepository, times(1)).save(any(AuditLog.class));
        verify(authenticationFacade, times(1)).getAuthentication();
    }

    @Test
    void test_Update_Should_UpdateAuditLog_When_ReceiveValidParameters() {
        Store store = buildStore();
        UserDTO applicationUser = getUserDTO();

        when(authentication.getPrincipal()).thenReturn(applicationUser);
        when(authenticationFacade.getAuthentication()).thenReturn(authentication);

        when(auditLogRepository.save(any(AuditLog.class)))
            .thenReturn(mock(AuditLog.class));

        assertAll(() -> auditLogService.updateAuditLog(store, store));
        verify(auditLogRepository,
            times(1)).save(any(AuditLog.class));
        verify(authenticationFacade, times(1)).getAuthentication();
    }

    @Test
    void test_Delete_Should_DeleteAuditLog_When_ReceiveValidParameters() {
        Store store = buildStoreAllParams();
        UserDTO applicationUser = getUserDTO();
        store.getZones().setCity(buildCity());
        store.setTaxRegime("");
        store.setCurrencySymbol("");
        store.setCurrencyType("");
        store.setUuid("");
        store.setStoreIdentifierType(StoreIdentifierType.builder().name("")
                .createdAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .id(1L)
                .updatedAt(LocalDateTime.now()).build());
        store.getZones().setCreatedAt(LocalDateTime.now());
        store.getZones().setDeletedAt(LocalDateTime.now());
        store.getZones().setUpdatedAt(LocalDateTime.now());
        store.getStoreType().setName("");
        store.getStoreType().setCreatedAt(LocalDateTime.now());
        store.getStoreType().setDeletedAt(LocalDateTime.now());
        store.getStoreType().setUpdatedAt(LocalDateTime.now());

        when(authentication.getPrincipal()).thenReturn(applicationUser);
        when(authenticationFacade.getAuthentication()).thenReturn(authentication);

        when(auditLogRepository.save(any(AuditLog.class)))
            .thenReturn(mock(AuditLog.class));

        assertAll(() -> auditLogService.deleteAuditLog(store));
        verify(auditLogRepository,
            times(1)).save(any(AuditLog.class));
        verify(authenticationFacade, times(1)).getAuthentication();
    }

    private UserDTO getUserDTO() {
        return UserDTO.builder()
            .id(123456L).build();
    }

    private Country buildCountry() {
        return Country.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .build();
    }

    private State buildState() {
        return State.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .country(buildCountry())
            .build();
    }

    private City buildCity() {
        return City.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .timezone(TEST)
            .state(buildState())
            .build();
    }

    private Store buildStore() {
        return Store.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .address(TEST)
            .location(TEST)
            .phone(TEST)
            .email(TEST)
            .internalName(TEST)
            .identifier(TEST)
            .city(buildCity())
            .company(Company.builder().id(1L).build())
            .storeType(new StoreType(1L))
            .zones(new Zone(1L))
            .build();
    }

    private Zone  buildZone(){
        return Zone.builder().id(1L)
                .createdAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .city(buildCity())
                .build();
    }
    
    private StoreType buildStoreType(){
        return StoreType.builder().id(1l)
                .name("")
                .createdAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();
    }

    private StoreIdentifierType buildStoreIdentifierType(){
        return  StoreIdentifierType.builder().name("")
                .createdAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .id(1L)
                .updatedAt(LocalDateTime.now()).build();
    }

    private Store buildStoreAllParams(){
        return Store.builder()
                .id(TEST_LONG)
                .createdAt(CURRENT_DATE)
                .updatedAt(CURRENT_DATE)
                .deletedAt(null)
                .name(TEST)
                .address(TEST)
                .location(TEST)
                .phone(TEST)
                .email(TEST)
                .internalName(TEST)
                .identifier(TEST)
                .city(buildCity())
                .taxRegime("")
                .currencySymbol("")
                .currencyType("")
                .uuid("")
                .storeIdentifierType(buildStoreIdentifierType())
                .company(Company.builder().id(1L).build())
                .storeType(buildStoreType())
                .zones(buildZone())
                .build();
    }

}