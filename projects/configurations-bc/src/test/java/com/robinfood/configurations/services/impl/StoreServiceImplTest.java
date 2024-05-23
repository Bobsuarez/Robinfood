package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreIdentifierType;
import com.robinfood.configurations.models.StoreType;
import com.robinfood.configurations.models.Zone;
import com.robinfood.configurations.repositories.jpa.CityRepository;
import com.robinfood.configurations.repositories.jpa.CompanyRepository;
import com.robinfood.configurations.repositories.jpa.StoreIdentifierTypeRepository;
import com.robinfood.configurations.repositories.jpa.StoreRepository;
import com.robinfood.configurations.repositories.jpa.StoreTypeRepository;
import com.robinfood.configurations.repositories.jpa.ZoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

    private static final String TEST = "TEST";
    private static final Long TEST_LONG = 1L;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();

    @InjectMocks
    private StoreServiceImpl storeService;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private StoreTypeRepository storeTypeRepository;

    @Mock
    private AuditLogServiceImpl auditLogService;

    @Mock
    private StoreIdentifierTypeRepository storeIdentifierTypeRepository;

    @Mock
    private CompanyRepository companyRepository;

    private City cityModel;
    private Store storeModel;

    @BeforeEach
    public void setUp() {
        buildCity();
        buildStore();
    }

    private void buildStore() {
        storeModel = Store.builder()
            .name(TEST)
            .address(TEST)
            .location(TEST)
            .phone(TEST)
            .email(TEST)
            .internalName(TEST)
            .identifier(TEST)
            .city(cityModel)
            .company(Company.builder().id(1L).build())
            .zones(new Zone(1L))
            .storeType(new StoreType(1L))
            .storeIdentifierType(new StoreIdentifierType(1L))
            .uuid(UUID.randomUUID().toString())
            .currencyType(TEST)
            .currencySymbol(TEST)
            .taxRegime(TEST)
            .build();
        storeModel.setId(TEST_LONG);
        storeModel.setCreatedAt(CURRENT_DATE);
        storeModel.setUpdatedAt(CURRENT_DATE);
        storeModel.setDeletedAt(null);
    }

    private void buildCity() {
        cityModel = City.builder()
            .name(TEST)
            .timezone(TEST)
            .state(null)
            .build();
        cityModel.setId(TEST_LONG);
        cityModel.setCreatedAt(CURRENT_DATE);
        cityModel.setUpdatedAt(CURRENT_DATE);
        cityModel.setDeletedAt(null);
    }

    @Test
    void test_FindStoreById_Should_ReturnStore() {
        when(storeRepository.findById(anyLong()))
            .thenReturn(Optional.ofNullable(storeModel));
        assertAll(() -> storeService.findById(TEST_LONG));
    }

    @Test
    void test_FindPosById_Should_ReturnException_When_PosIsEmpty() {
        when(storeRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            storeService.findById(TEST_LONG);
        });

        verify(storeRepository, times(1)).findById(anyLong());
        String expectedMessage = String.format("Store with id %d not found.", TEST_LONG);
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_CreateStore_Should_ReturnStore_When_ReceiveDataValid()
        throws JsonProcessingException {

        when(storeRepository.existsByNameAndDeletedAtIsNull(anyString()))
            .thenReturn(false);
        when(storeRepository.existsByInternalNameAndDeletedAtIsNull(anyString()))
            .thenReturn(false);
        when(companyRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Company()));
        when(cityRepository.findById(anyLong()))
            .thenReturn(Optional.of(new City(1L)));
        when(zoneRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Zone(1L)));
        when(storeTypeRepository.findById(anyLong()))
            .thenReturn(Optional.of(new StoreType(1L)));
        when(storeIdentifierTypeRepository.findById(anyLong()))
            .thenReturn(Optional.of(new StoreIdentifierType(1L)));
        when(storeRepository.save(any(Store.class)))
            .thenReturn(storeModel);
        doNothing().when(auditLogService).createAuditLog(any());

        assertAll(() -> storeService.create(storeModel));

        verify(storeRepository, times(1))
            .existsByInternalNameAndDeletedAtIsNull(anyString());
        verify(storeRepository, times(1))
            .existsByNameAndDeletedAtIsNull(anyString());
        verify(companyRepository, times(1))
            .findById(anyLong());
        verify(cityRepository, times(1))
            .findById(anyLong());
        verify(zoneRepository, times(1))
            .findById(anyLong());
        verify(storeTypeRepository, times(1))
            .findById(anyLong());
        verify(storeTypeRepository, times(1))
            .findById(anyLong());
        verify(auditLogService, times(1)).createAuditLog(any());

    }

    @Test
    void test_CreateStore_Should_ThrowBusinessRuleException_When_AlreadyExistingName() {

        when(storeRepository
            .existsByNameAndDeletedAtIsNull(anyString())).thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> storeService.create(storeModel));
        verify(storeRepository, times(1))
            .existsByNameAndDeletedAtIsNull(anyString());
    }

    @Test
    void test_CreateStore_Should_ThrowBusinessRuleException_When_AlreadyExistingInternalName() {

        when(storeRepository
            .existsByInternalNameAndDeletedAtIsNull(anyString())).thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> storeService.create(storeModel));
        verify(storeRepository, times(1))
            .existsByInternalNameAndDeletedAtIsNull(anyString());
    }

    @Test
    void test_CreateStore_Should_ThrowBusinessRuleException_When_CompanyCountryDosNotExist() {

        when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessRuleException.class, () -> storeService.create(storeModel));
        verify(companyRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_CreateStore_Should_ThrowBusinessRuleException_When_CountryDosNotExist() {

        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(new Company()));
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessRuleException.class, () -> storeService.create(storeModel));
        verify(cityRepository, times(1)).findById(anyLong());
        verify(companyRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_CreateStore_Should_ThrowBusinessRuleException_When_ZoneDosNotExist() {

        when(companyRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Company()));
        when(cityRepository.findById(anyLong()))
            .thenReturn(Optional.of(new City(1L)));
        when(zoneRepository
            .findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessRuleException.class, () -> storeService.create(storeModel));
        verify(zoneRepository, times(1)).findById(anyLong());
        verify(companyRepository, times(1)).findById(anyLong());
        verify(cityRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_CreateStore_Should_ThrowBusinessRuleException_When_StoreTypeDosNotExist() {

        when(storeRepository.existsByInternalNameAndDeletedAtIsNull(anyString()))
            .thenReturn(false);
        when(companyRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Company()));
        when(cityRepository.findById(anyLong()))
            .thenReturn(Optional.of(new City(1L)));
        when(zoneRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Zone(1L)));
        when(storeTypeRepository
            .findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessRuleException.class, () -> storeService.create(storeModel));
        verify(storeTypeRepository, times(1)).findById(anyLong());
        verify(companyRepository, times(1)).findById(anyLong());
        verify(cityRepository, times(1)).findById(anyLong());
        verify(zoneRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_CreateStore_Should_ThrowBusinessRuleException_When_StoreIdentifierTypeDosNotExist() {

        when(storeRepository.existsByInternalNameAndDeletedAtIsNull(anyString()))
            .thenReturn(false);
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(new Company()));
        when(cityRepository.findById(anyLong()))
            .thenReturn(Optional.of(new City(1L)));
        when(zoneRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Zone(1L)));
        when(storeTypeRepository
            .findById(anyLong())).thenReturn(Optional.of(new StoreType(1L)));
        when(storeIdentifierTypeRepository
            .findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessRuleException.class, () -> storeService.create(storeModel));
        verify(storeTypeRepository, times(1)).findById(anyLong());
        verify(storeIdentifierTypeRepository, times(1)).findById(anyLong());
        verify(companyRepository, times(1)).findById(anyLong());
        verify(cityRepository, times(1)).findById(anyLong());
        verify(zoneRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_UpdateStore_Should_ThrowBusinessRuleException_When_AlreadyExistingUuid() {

        Store store = Store.builder().name(null).internalName(null).build();

        when(storeRepository.findById(anyLong()))
            .thenReturn(Optional.of(storeModel));
        when(storeRepository
            .existsByUuidAndDeletedAtIsNull(anyString())).thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> storeService.update(1L, store));
        verify(storeRepository, times(1))
            .findById(anyLong());
        verify(storeRepository, times(1))
            .existsByUuidAndDeletedAtIsNull(anyString());
    }

    @Test
    void test_ListStore_Should_ListStore_When_Correct_Request() {
        when(storeRepository.findByNameAndCompanyCountryIdDeletedAtIsNull(anyString(), anyLong(), any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 10), 10));

        assertAll(() -> storeService.list( "test", 1L, PageRequest.of(0, 10)));
        verify(storeRepository, times(1))
            .findByNameAndCompanyCountryIdDeletedAtIsNull(anyString(), anyLong(), any(Pageable.class));
    }

    @Test
    void test_CountByFilter_Should_ReturnSize_When_Invoked() {
        when(storeRepository.countByNameAndCompanyCountryIdDeletedAtIsNull(anyString(), any()))
            .thenReturn(10);

        int storesCount = storeService.countByFilter( "test", null);

        assertEquals(10, storesCount);
        verify(storeRepository, times(1))
            .countByNameAndCompanyCountryIdDeletedAtIsNull(anyString(), any());
    }

    @Test
    void test_Delete_Should_DeleteStore_When_ValidIdIsGiven() throws JsonProcessingException {
        // Given
        Store store = storeModel;
        store.setId(1L);
        Long id = 1L;

        // When
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));
        doNothing().when(auditLogService).deleteAuditLog(any());

        assertAll(() -> storeService.delete(id));

        // Then
        verify(auditLogService, times(1)).deleteAuditLog(any());
        verify(storeRepository, times(1)).delete(any(Store.class));
    }

    @Test
    void test_Delete_Should_ThrowException_When_StoreIsNotFound() {
        // Given
        Long id = 1L;

        // When
        when(storeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(EntityNotFoundException.class, () -> storeService.delete(id));

        // Then
        verify(storeRepository, times(0)).delete(any(Store.class));
    }

    @Test
    void test_UpdateStore_Should_ReturnStore_When_ReceiveDataValid()
        throws JsonProcessingException {

        Store store = Store.builder().name(TEST.concat(TEST))
            .address(TEST)
            .location(TEST)
            .phone(TEST)
            .email(TEST)
            .internalName(TEST.concat(TEST))
            .identifier(TEST)
            .city(cityModel)
            .company(Company.builder().id(1L).build())
            .zones(new Zone(1L))
            .storeType(new StoreType(1L))
            .storeIdentifierType(new StoreIdentifierType(1L))
            .uuid(UUID.randomUUID().toString().concat(TEST))
            .currencyType(TEST)
            .currencySymbol(TEST)
            .taxRegime(TEST).build();

        when(storeRepository.existsByNameAndDeletedAtIsNull(anyString()))
            .thenReturn(false);
        when(storeRepository.existsByInternalNameAndDeletedAtIsNull(anyString()))
            .thenReturn(false);
        when(storeRepository
            .existsByUuidAndDeletedAtIsNull(anyString())).thenReturn(false);
        when(storeRepository.findById(anyLong()))
            .thenReturn(Optional.of(storeModel));
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(new Company()));
        when(cityRepository.findById(anyLong()))
            .thenReturn(Optional.of(new City(1L)));
        when(zoneRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Zone(1L)));
        when(storeTypeRepository.findById(anyLong()))
            .thenReturn(Optional.of(new StoreType(1L)));
        when(storeIdentifierTypeRepository.findById(anyLong()))
            .thenReturn(Optional.of(new StoreIdentifierType(1L)));
        when(storeRepository.save(any(Store.class)))
            .thenReturn(storeModel);
        doNothing().when(auditLogService).updateAuditLog(any(), any());

        assertAll(() -> storeService.update(1L, store));

        verify(storeRepository, times(1))
            .existsByInternalNameAndDeletedAtIsNull(anyString());
        verify(storeRepository, times(1))
            .existsByNameAndDeletedAtIsNull(anyString());
        verify(companyRepository, times(1))
            .findById(anyLong());
        verify(storeRepository, times(1))
            .findById(anyLong());
        verify(cityRepository, times(1))
            .findById(anyLong());
        verify(zoneRepository, times(1))
            .findById(anyLong());
        verify(storeTypeRepository, times(1))
            .findById(anyLong());
        verify(storeTypeRepository, times(1))
            .findById(anyLong());
        verify(auditLogService, times(1)).updateAuditLog(any(),any());

    }

    @Test
    void test_UpdateStore_Should_ReturnStore_When_ReceiveData()
        throws JsonProcessingException {

        when(storeRepository.findById(anyLong()))
            .thenReturn(Optional.of(storeModel));
        when(companyRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Company()));
        when(cityRepository.findById(anyLong()))
            .thenReturn(Optional.of(new City(1L)));
        when(zoneRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Zone(1L)));
        when(storeTypeRepository.findById(anyLong()))
            .thenReturn(Optional.of(new StoreType(1L)));
        when(storeIdentifierTypeRepository.findById(anyLong()))
            .thenReturn(Optional.of(new StoreIdentifierType(1L)));
        when(storeRepository.save(any(Store.class)))
            .thenReturn(storeModel);
        doNothing().when(auditLogService).updateAuditLog(any(), any());

        assertAll(() -> storeService.update(1L, storeModel));

        verify(companyRepository, times(1))
            .findById(anyLong());
        verify(storeRepository, times(1))
            .findById(anyLong());
        verify(cityRepository, times(1))
            .findById(anyLong());
        verify(zoneRepository, times(1))
            .findById(anyLong());
        verify(storeTypeRepository, times(1))
            .findById(anyLong());
        verify(storeTypeRepository, times(1))
            .findById(anyLong());
        verify(auditLogService, times(1)).updateAuditLog(any(),any());

    }

    @Test
    void test_UpdateStore_Should_ThrowEntityNotFoundException_When_StoreNotExists() {
        Store store = Store.builder().build();

        when(storeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any()));

        assertThatThrownBy(() -> storeService.update(1L, store)).isInstanceOf(
            EntityNotFoundException.class);
        verify(storeRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_FindAllStores_Should_ReturnStores() {
        when(storeRepository.findAll())
            .thenReturn(
                List.of(storeModel)
            );

        assertAll(() -> storeService.findStores(null));
    }

    @Test
    void test_FindAllStoresNameEmpty_Should_ReturnStores() {
        when(storeRepository.findAll())
            .thenReturn(
                List.of(storeModel)
            );

        assertAll(() -> storeService.findStores(""));
    }

    @Test
    void test_FindAllStoresWithName_Should_ReturnStores() {
        when(storeRepository.findByNameContains("TES"))
            .thenReturn(
                List.of(storeModel)
            );

        assertAll(() -> storeService.findStores("TES"));
    }

    @Test
    public void testGetCompanyId() {
        // Given
        Long storeId = 1L;
        Integer companyId = 100;


        // When
        when(storeRepository.getCompanyIdByStoreId(storeId)).thenReturn(companyId);

        int result = storeService.getCompanyId(storeId);

        // Then
        assertEquals(companyId.intValue(), result);
        verify(storeRepository, times(1)).getCompanyIdByStoreId(storeId);
    }

}