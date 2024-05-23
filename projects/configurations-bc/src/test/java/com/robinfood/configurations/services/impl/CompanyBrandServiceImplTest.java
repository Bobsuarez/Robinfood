package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.repositories.jpa.CompanyBrandRepository;

import java.util.Collections;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CompanyBrandServiceImplTest {

    @Mock
    private CompanyBrandRepository companyBrandRepository;

    @InjectMocks
    CompanyBrandServiceImpl companyBrandsService;

    private String TEST = "TEST";

    CompanyBrand companyBrand = CompanyBrand.builder()
            .id(1L)
            .name(TEST)
            .logo(TEST)
            .build();

    @Test
    void testFindAll_CompanyBrands() {
        when(companyBrandRepository.findAll()).thenReturn(List.of(companyBrand));
        assertAll(() -> companyBrandsService.findAll());
    }


    @Test
    void test_FindById_Should_CompanyBrand_When_CompanyBrandDoesExists() {
        CompanyBrand companyBrand = CompanyBrand.builder()
            .id(1L)
            .name("test")
            .logo("test")
            .build();


        when(companyBrandRepository.findById(anyLong())).thenReturn(
            java.util.Optional.ofNullable(companyBrand));

        assertAll(() -> companyBrandsService.findById(1L));
        verify(companyBrandRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_FindById_Should_ThrowEntityNotFoundException_When_CompanyBrandDoesNotExists() {

        when(companyBrandRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any()));

        assertThatThrownBy(() -> companyBrandsService.findById(1L))
            .isInstanceOf(EntityNotFoundException.class);
        verify(companyBrandRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_list_Should_ListCompanyCountry_When_Correct_Request() {
        when(companyBrandRepository.findByCompanyIdAndNameAndDeletedAtIsNull(
                anyLong(), anyString(), anyLong(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 10), 10));

        assertAll(() -> companyBrandsService.list(1L, "name", 1L, PageRequest.of(0, 10)));

        verify(companyBrandRepository, times(1))
                .findByCompanyIdAndNameAndDeletedAtIsNull(anyLong(), anyString(), anyLong(), any(Pageable.class));

    }

    @Test
    public void testGetByCompanyAndBrand() throws JsonProcessingException {
        // Given
        Long companyId = 1L;
        Long brandId = 1L;
        CompanyBrand expectedCompanyBrandId = new CompanyBrand();
        CompanyBrand companyBrand = new CompanyBrand();

        // When
        when(companyBrandRepository.findByCompanyIdAndMenuBrandId(brandId)).thenReturn(companyBrand);

        CompanyBrand result = companyBrandsService.getByCompanyIdAndMenuBrandId(brandId);

        // Then
        assertEquals(expectedCompanyBrandId, result);
        verify(companyBrandRepository, times(1)).findByCompanyIdAndMenuBrandId(brandId);
    }

}
