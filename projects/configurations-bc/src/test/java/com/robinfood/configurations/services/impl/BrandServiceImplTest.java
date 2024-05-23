package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.repositories.jpa.BrandRepository;
import com.robinfood.configurations.repositories.jpa.CompanyBrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CompanyBrandRepository companyBrandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void testFindByBrandIdAndCompanyCountryId() {
        when(
                companyBrandRepository.findByBrandsIdAndCompanyId(1L, 1L)
        ).thenReturn(Optional.of(CompanyBrand.builder()
                .name("TEST")
                .logo("TEST")
                .build()));

        assertAll(() ->
                brandService.getByBrandIdAndCompanyId(1L, 1L));
    }

    @Test
    void testFindByBrandIdAndCompanyCountryId_fail() {
        when(
                companyBrandRepository.findByBrandsIdAndCompanyId(2L, 1L)
        ).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
                brandService.getByBrandIdAndCompanyId(2L, 1L));
    }

    @Test
    void testFindByAll() {

        when(brandRepository.findAllPaginated(any(Pageable.class), anyBoolean()))
                .thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 10), 10));

        assertAll(() -> brandService.list(PageRequest.of(0, 10), Boolean.TRUE));
        verify(brandRepository, times(1))
                .findAllPaginated(any(Pageable.class), anyBoolean());
    }

    @Test
    void testCountBrands(){

        long expectedCount = 5;
        when(brandRepository.count()).thenReturn(expectedCount);

        long actualCount = brandService.count();
        assertEquals(expectedCount, actualCount);
    }

}
