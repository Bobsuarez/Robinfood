package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreBrand;
import com.robinfood.configurations.repositories.jpa.StoreBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreBrandsServiceImplTest {

    @Mock
    private StoreBrandRepository storeBrandRepository;

    @InjectMocks
    private StoreBrandsServiceImpl storeBrandsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate_Success() throws JsonProcessingException, BusinessRuleException {
        // Given
        StoreBrand receivedStoreBrand = new StoreBrand();
        receivedStoreBrand.setId(1L);
        receivedStoreBrand.setBrandCompany(new CompanyBrand());
        receivedStoreBrand.getBrandCompany().setId(1L);
        receivedStoreBrand.setStore(new Store());
        receivedStoreBrand.getStore().setId(1L);

        // When
        when(storeBrandRepository.countByBrandIdAndStoreId(1L, 1L)).thenReturn(0);
        when(storeBrandRepository.save(receivedStoreBrand)).thenReturn(receivedStoreBrand);

        StoreBrand createdStoreBrand = storeBrandsService.create(receivedStoreBrand);

        // Then
        assertEquals(receivedStoreBrand, createdStoreBrand);
        verify(storeBrandRepository, times(1)).save(receivedStoreBrand);
    }

    @Test
    public void testCreate_AlreadyExists() throws JsonProcessingException, BusinessRuleException {
        // Given
        StoreBrand receivedStoreBrand = new StoreBrand();
        receivedStoreBrand.setBrandCompany(new CompanyBrand());
        receivedStoreBrand.getBrandCompany().setId(1L);
        receivedStoreBrand.setStore(new Store());
        receivedStoreBrand.getStore().setId(1L);

        // When
        when(storeBrandRepository.countByBrandIdAndStoreId(1L, 1L)).thenReturn(1);

        StoreBrand createdStoreBrand = storeBrandsService.create(receivedStoreBrand);

        // Then
        assertEquals(null, createdStoreBrand);
        verify(storeBrandRepository, never()).save(receivedStoreBrand);
    }
}
