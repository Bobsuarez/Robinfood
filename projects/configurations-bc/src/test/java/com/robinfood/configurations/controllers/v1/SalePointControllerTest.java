package com.robinfood.configurations.controllers.v1;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Country;
import com.robinfood.configurations.models.Headquarter;
import com.robinfood.configurations.models.Holding;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.models.State;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.services.CompanyBrandService;
import com.robinfood.configurations.services.PosService;
import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = SalePointController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=configurations_bc"
})
public class SalePointControllerTest extends BaseControllerTest {

    private static final String TEST = "TEST";
    private static final Long TEST_LONG = 1L;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.MIN;
    private static final String ENCODING = "utf-8";
    private static final String PATH_WITH_BRAND_ID = "/v1/sale-points?pos_id=1&brand_id=1";
    private static final String PATH = "/v1/sale-points?pos_id=1";
    private static final String MODULES = "configurations_bc";
    private static final String JSON = "{\"sort\":\"name\"}";

    @MockBean
    private PosService posService;

    @MockBean
    private CompanyBrandService companyBrandService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_FindSalePoint_Should_Return401_When_AudienceIsNotValid() throws Exception {
        String token = getJWT("test_error", "test_error", "internal");
        mockMvc.perform(get(PATH_WITH_BRAND_ID)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").value("Unauthorized"))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.metaData").isEmpty());
    }

    @Test
    public void testFindSalePoint_Should_ReturnSalePointInfo_When_ParamsReceivedReturn200()
        throws Exception {
        City city = buildCity();
        city.setState(buildState());
        Resolution resolution = buildResolution();
        Headquarter headquarter = buildHeadquarter();
        Company company = buildCompany(headquarter);
        company.setHolding(Holding.builder().id(1L).logo("XXXXXX").identification("123").build());
        Brand brand = buildBrand();
        CompanyBrand brandCompanyCountry = buildBrandCompany(brand);
        //Company company = buildCompanyCountry(company, brandCompanyCountry);
        Store store = buildStore(company, city);
        Pos pos = buildPos(resolution, store);
        when(posService.findById(anyLong())).thenReturn(pos);

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(get(PATH_WITH_BRAND_ID)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.message").value("Sale Point retrieved successfully"));
    }

    @Test
    public void test_FindSalePoint_Should_ReturnSalePointInfo_When_ParamsReceivedPathWithoutBrandReturn200()
        throws Exception {
        City city = buildCity();
        city.setState(buildState());
        Resolution resolution = buildResolution();
        Headquarter headquarter = buildHeadquarter();
        Company company = buildCompany(headquarter);
        company.setHolding(Holding.builder().id(1L).logo("XXXXXX").identification("123").build());
        Brand brand = buildBrand();
        CompanyBrand brandCompanyCountry = buildBrandCompany(brand);
        Company companyCountry = buildCompanyCountry(company, brandCompanyCountry);
        Store store = buildStore(companyCountry, city);
        store.setCompany(company);
        Pos pos = buildPos(resolution, store);
        when(posService.findById(anyLong())).thenReturn(pos);

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(get(PATH)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.message").value("Sale Point retrieved successfully"));
    }

    @Test
    public void test_FindSalePoint_Should_ReturnSalePointInfo_When_ParamsReceivedPathWithoutBrandLogoNullReturn200()
        throws Exception {
        City city = buildCity();
        city.setState(buildState());
        Resolution resolution = buildResolution();
        Headquarter headquarter = buildHeadquarter();
        Company company = buildCompany(headquarter);
        company.setHolding(Holding.builder().id(1L).logo("XXXXXX").identification("123").build());
        Brand brand = buildBrand();
        CompanyBrand brandCompanyCountry = CompanyBrand.builder()
            .name(TEST)
            .logo(null)
            .brands(brand)
            .company(null)
            .build();
        Company companyCountry = buildCompanyCountry(company, brandCompanyCountry);
        Store store = buildStore(companyCountry, city);
        store.setCompany(company);
        Pos pos = buildPos(resolution, store);
        when(posService.findById(anyLong())).thenReturn(pos);

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(get(PATH_WITH_BRAND_ID)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.message").value("Sale Point retrieved successfully"));
    }

    private Headquarter buildHeadquarter() {
        return Headquarter.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .phone(TEST)
            .email(TEST)
            .address(TEST)
            .cityName(TEST)
            .company(null)
            .build();
    }

    private Company buildCompany(Headquarter headquarter) {
        return Company.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .headquarter(headquarter)
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
            .state(null)
            .build();
    }

    private Company buildCompanyCountry(Company company, CompanyBrand brandCompany) {
        return Company.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .country(null)
            .build();
    }

    private Brand buildBrand() {
        return Brand.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .build();
    }

    private CompanyBrand buildBrandCompany(Brand brand) {
        return CompanyBrand.builder()
                .name(TEST)
                .logo(TEST)
            .build();
    }

    private Store buildStore(Company companyCountry, City city) {
        return Store.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .address(TEST)
            .city(city)
            .company(companyCountry)
            .build();
    }

    private Resolution buildResolution() {
        return Resolution.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .status(1)
            .startingNumber(TEST)
            .finalNumber(TEST)
            .name(TEST)
            .startDate(CURRENT_DATE)
            .endDate(CURRENT_DATE)
            .prefix(TEST)
            .invoiceText(TEST)
            .serial(TEST)
            .invoiceNumberResolutions(TEST)
            .document(TEST)
            .pos(null)
            .build();
    }

    private Pos buildPos(Resolution resolution, Store store) {
        return Pos.builder()
            .id(TEST_LONG)
            .createdAt(CURRENT_DATE)
            .updatedAt(CURRENT_DATE)
            .deletedAt(null)
            .name(TEST)
            .code(TEST)
            .store(store)
            .resolutionList(resolution != null ? Collections.singletonList(resolution) : null)
            .build();
    }

    private State buildState(){
        return State.builder().id(2l)
                .name("")
                .createdAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .country(buildCountry())
                .build();
    }

    private Country buildCountry(){
        return Country.builder()
                .id(1l)
                .name("")
                .createdAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}