package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.Headquarter;
import com.robinfood.configurations.models.Holding;
import com.robinfood.configurations.repositories.jpa.CompanyRepository;
import com.robinfood.configurations.samples.CompanySample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    void test_FindCompanyById_Success() {

        Headquarter headquarter = Headquarter.builder()
                .id(3L)
                .phone("4567")
                .email("muy@muy.com")
                .address("carreer 13 # 13 ")
                .cityName("COL")
                .build();

        Company company = Company.builder()
                .name("MUY MEXICO")
                .holding(Holding.builder().identification("MFM1906048G1").build())
                .headquarter(headquarter)
                .build();

        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        assertAll(() -> companyService.findById(anyLong()));

    }

    @Test
    void test_FindCompanyById_IsEmpty() {
        when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            companyService.findById(1L);
        });
        verify(companyRepository, times(1)).findById(anyLong());
        String expectedMessage = String.format("Company id not found");
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_When_FindAllCompany_Should_CompanyData() {
        when(companyRepository.findAllByStatus(anyLong())).thenReturn(List.of(CompanySample.getCompany()));
        List<Company> companyList = companyService.findByAll(1L);
        assertEquals(1,companyList.size());
    }

}
