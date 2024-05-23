package com.robinfood.configurations.utils;

import com.robinfood.configurations.dto.v1.models.ResolutionDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.models.BrandCompanyChannel;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Holding;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.models.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SalePointUtilTest {

    private static final String TEST = "TEST";
    private static final Long TEST_LONG = 1L;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.MIN;

    private CompanyBrand companyBrand = CompanyBrand.builder()
            .name(TEST).logo(TEST)
            .brands(Brand.builder().id(1L).name(TEST).build())
            .company(Company.builder().id(1L).name(TEST).build())
            .build();
    private List<CompanyBrand> companyBrands = new ArrayList<>();

    public SalePointUtilTest() {
        companyBrands.add(companyBrand);
    }

    @Test
    public void test_GetPosResolutions_Should_Return_Resolutions_When_ObjectIsPassed() {
        Resolution resolution = Resolution.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .status(1)
            .startingNumber(TEST)
            .finalNumber(TEST)
            .name(TEST)
            .startDate(null)
            .endDate(null)
            .prefix(TEST)
            .invoiceText(TEST)
            .serial(TEST)
            .invoiceNumberResolutions(TEST)
            .document(TEST)
            .pos(Pos.builder()
                .id(TEST_LONG)
                .build())
            .build();

        List<ResolutionDTO> current = SalePointUtil.getPosResolutions(
            Collections.singletonList(resolution));
        Assertions.assertNotNull(current);
    }

    @Test
    public void test_GetPosResolutions_Should_ReturnEmptyList_When_ObjectListIsEmpty() {
        Resolution resolution = Resolution.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(CURRENT_DATE)
            .status(1)
            .startingNumber(TEST)
            .finalNumber(TEST)
            .name(TEST)
            .startDate(null)
            .endDate(null)
            .prefix(TEST)
            .invoiceText(TEST)
            .serial(TEST)
            .invoiceNumberResolutions(TEST)
            .document(TEST)
            .pos(Pos.builder()
                .id(TEST_LONG)
                .build())
            .build();

        List<ResolutionDTO> current = SalePointUtil.getPosResolutions(
            Collections.singletonList(resolution));
        Assertions.assertNotNull(current);
    }

    @Test
    public void test_GetLogoFromBrandCompanyCountry_Should_ReturnLogo() {

        String expected = "TEST_LOGO";

        Company company = Company.builder()
            .name(TEST)
            .holding(Holding.builder().id(1L).identification("123").logo(expected).build())
            .build();

        Brand brand = Brand.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .build();

        BrandCompanyChannel brandCompanyCountry = BrandCompanyChannel.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .icon(TEST)
            .banner(TEST)
            .color(TEST)
            .configUrl(TEST)
            .build();

        Company companyCountry = Company.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .build();

        Store store = Store.builder()
            .name(TEST)
            .address(TEST)
            .location(TEST)
            .phone(TEST)
            .email(TEST)
            .internalName(TEST)
            .identifier(TEST)
            .city(null)
            .company(company)
            .build();
        String current = SalePointUtil.getLogoFromBrandCompanyCountry(store, companyBrands, TEST_LONG);

        Assertions.assertEquals(current, expected);
    }

    @Test
    public void test_GetLogoFromBrandCompanyCountry_Should_ReturnLogoFromCompany() {

        String expected = "TEST";

        Company company = Company.builder()
            .id(TEST_LONG)
            .headquarter(null)
            .holding(Holding.builder().id(1L).identification("123").logo(expected).build())
            .build();

        Brand brand = Brand.builder()
            .id(2L)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .build();

        BrandCompanyChannel brandCompanyCountry = BrandCompanyChannel.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(CURRENT_DATE)
            .icon(TEST)
            .banner(TEST)
            .color(TEST)
            .configUrl(TEST)
            .build();

        Store store = Store.builder()
            .name(TEST)
            .address(TEST)
            .location(TEST)
            .phone(TEST)
            .email(TEST)
            .internalName(TEST)
            .identifier(TEST)
            .city(null)
            .company(company)
            .build();
        String current = SalePointUtil.getLogoFromBrandCompanyCountry(store, companyBrands, TEST_LONG);

        Assertions.assertEquals(current, expected);
    }

    @Test
    public void test_GetLogoFromBrandCompanyCountry_Should_ReturnLogo_When_BrandIdNotExists() {

        String expected = "TEST_LOGO";

        Company company = Company.builder()
            .id(1L)
            .name(TEST)
            .headquarter(null)
            .holding(Holding.builder().id(1L).identification("123").logo(expected).build())
            .build();

        Brand brand = Brand.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .build();

        BrandCompanyChannel brandCompanyCountry = BrandCompanyChannel.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .icon(TEST)
            .configUrl(TEST)
            .color(TEST)
            .banner(TEST)
            .build();

        Store store = Store.builder()
            .name(TEST)
            .address(TEST)
            .location(TEST)
            .phone(TEST)
            .email(TEST)
            .internalName(TEST)
            .identifier(TEST)
            .city(null)
            .company(company)
            .build();
        String current = SalePointUtil.getLogoFromBrandCompanyCountry(store, companyBrands,2L);

        Assertions.assertEquals(current, expected);
    }

    @Test
    void test_getPosResolutionsActive_Should_Return_List_When_OK() {
        Resolution resolution = Resolution.builder()
                .id(TEST_LONG)
                .createdAt(CURRENT_DATE)
                .updatedAt(CURRENT_DATE)
                .deletedAt(null)
                .status(2)
                .startingNumber(TEST)
                .finalNumber(TEST)
                .name(TEST)
                .startDate(null)
                .endDate(null)
                .prefix(TEST)
                .invoiceText(TEST)
                .serial(TEST)
                .invoiceNumberResolutions(TEST)
                .document(TEST)
                .pos(Pos.builder().id(TEST_LONG).build())
                .deletedAt(null)
                .build();
        List<ResolutionDTO> current = SalePointUtil.getPosResolutionsActive(Collections.singletonList(resolution));
        Assertions.assertNotNull(current);
    }

    @Test
    void test_getPosResolutionsActive_Should_Return_List_When_DeletedIsNotNull() {
        Resolution resolution = Resolution.builder()
                .id(TEST_LONG)
                .createdAt(CURRENT_DATE)
                .updatedAt(CURRENT_DATE)
                .deletedAt(null)
                .status(1)
                .startingNumber(TEST)
                .finalNumber(TEST)
                .name(TEST)
                .startDate(null)
                .endDate(null)
                .prefix(TEST)
                .invoiceText(TEST)
                .serial(TEST)
                .invoiceNumberResolutions(TEST)
                .document(TEST)
                .pos(Pos.builder().id(TEST_LONG).build())
                .deletedAt(null)
                .build();
        List<ResolutionDTO> current = SalePointUtil.getPosResolutionsActive(Collections.singletonList(resolution));
        Assertions.assertNotNull(current);
    }

    @Test
    public void test_getPosResolutionsActive_Should_Return_List_When_Not_OK() {
        Resolution resolution = Resolution.builder()
                .id(TEST_LONG)
                .createdAt(CURRENT_DATE)
                .updatedAt(CURRENT_DATE)
                .deletedAt(CURRENT_DATE)
                .status(1)
                .startingNumber(TEST)
                .finalNumber(TEST)
                .name(TEST)
                .startDate(null)
                .endDate(null)
                .prefix(TEST)
                .invoiceText(TEST)
                .serial(TEST)
                .invoiceNumberResolutions(TEST)
                .document(TEST)
                .pos(Pos.builder().id(TEST_LONG).build())
                .deletedAt(LocalDateTime.now())
                .build();
        List<ResolutionDTO> current = SalePointUtil.getPosResolutionsActive(Collections.singletonList(resolution));
        Assertions.assertNotNull(current);
    }


}