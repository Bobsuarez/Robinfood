package com.robinfood.configurations.controllers.v1;

import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.LIST_COMPANY_BRANDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyBrandDTO;
import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Country;
import com.robinfood.configurations.models.Headquarter;
import com.robinfood.configurations.models.Holding;
import com.robinfood.configurations.models.State;
import com.robinfood.configurations.samples.CompanySample;
import com.robinfood.configurations.services.CompanyBrandService;
import com.robinfood.configurations.services.CompanyService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CompanyController.class)
@TestPropertySource(properties = {
        "jwt.token.mod=configurations_bc"
})
class CompanyControllerTest extends BaseControllerTest {

    private static final String ENCODING = "utf-8";
    private static final String MODULES = "configurations_bc";
    private static final String JSON = "{\"sort\":\"name\"}";

    @MockBean
    private CompanyService companyService;

    @MockBean
    private CompanyBrandService companyBrandService;

    @SpyBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_findCompanyById_success() throws Exception {

        State state = State.builder().name("Atlantico").build();

        Country country = Country.builder()
                .id(1L)
                .name("Colombia")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .stateList(List.of(state)).build();

        Headquarter headquarter = Headquarter.builder()
                .id(3L)
                .phone("4567")
                .email("muy@muy.com")
                .address("carreer 13 # 13 ")
                .cityName("COL")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Holding holding = Holding.builder()
                .identification("MFM1906048G1")
                .name("TEST")
                .logo("TEST")
                .build();

        Company company = Company.builder()
                .name("MUY MEXICO")
                .holding(holding)
                .headquarter(headquarter)
                .country(country)
                .build();

        when(companyService.findById(anyLong())).thenReturn(company);
        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(get("/v1/companies/1")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(content().string(Matchers.containsString("id")))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_findCompanyById_Success_With_Headquarter_Null() throws Exception {

        Company company = Company.builder()
                .name("MUY MEXICO")
                .holding(Holding.builder().identification("MFM1906048G1").build())
                .build();

        when(companyService.findById(anyLong())).thenReturn(company);
        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(get("/v1/companies/1")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(content().string(Matchers.containsString("id")))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_findCompanyById_AudienceNotValid() throws Exception {
        String token = getJWT("test_error", "test_error", "internal");
        mockMvc.perform(get("/v1/companies/1")
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
    void test_listCompanyBrands_Should_Return200_When_ValidInput() throws Exception {
        CompanyBrand companyBrand = getCompanyBrand();

        List<CompanyBrand> companyBrandList = List.of(companyBrand);
        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + LIST_COMPANY_BRANDS, "service");

        //Mock
        when(companyBrandService.list(anyLong(), anyString(), anyLong(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(companyBrandList, PageRequest.of(0, 10), 10));

        //Call to code to test
        executeGetRequest(token, "/v1/companies/1/brands?name=pixi&store_id=1&page=0&sorted=false");
        executeGetRequest(token, "/v1/companies/1/brands?store_id=1&page=0&sort=id,asc");
        executeGetRequest(token, "/v1/companies/1/brands?name=pi&store_id=1&page=0&sort=id,asc");
        executeGetRequest(token, "/v1/companies/1/brands?name=pixi&store_id=1&page=0&sort=id,asc");
        executeGetRequest(token, "/v1/companies/1/brands?name=pixi&store_id=1&page=0&sort=id,desc");
        executeGetRequest(token, "/v1/companies/1/brands?name=pixi&store_id=1&page=0&sort=name,desc");
        executeGetRequest(token, "/v1/companies/1/brands?name=pixi&store_id=1&page=0&sort=name,asc");
        executeGetRequest(token, "/v1/companies/1/brands?name=pixi&store_id=1&page=0&sort=");
        executeGetRequest(token, "/v1/companies/1/brands?name=pixi&store_id=1&page=0&sort=");
        executeGetRequest(token, "/v1/companies/1/brands?name=pixi&store_id=1&page=0&unsorted=true&sorted=true");

    }

    @Test
    void test_listCompanyBrands_Should_ReturnUnauthorized_When_BearerIsNotValid()
            throws Exception {
        String token = getJWT(MODULES, "INVALID_PERMISSION",
                "service");

        //Call to code to test
        MvcResult mvcResult = mockMvc.perform(get("/v1/company-brands")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer" + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();

        ApiResponseDTO<List<CompanyBrandDTO>> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsString(),
                        new TypeReference<>() {
                        });

        assertNotNull(result);
        assertEquals(401, result.getCode());
        assertNotNull(result.getMessage());
        assertEquals("Unauthorized", result.getMessage());
        assertNull(result.getData());

    }

    @Test
    void test_when_companyList_Should_Request_OK() throws Exception {

        when(companyService.findByAll(anyLong()))
                .thenReturn(List.of(CompanySample.getCompany()));

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/companies/")
                .queryParam("status", "1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void test_when_companyList_Should_Request_NotFound() throws Exception {

        when(companyService.findByAll(anyLong()))
                .thenReturn(new ArrayList<>());

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/companies/")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    private CompanyBrand getCompanyBrand() {

        Country country = new Country();
        country.setId(1L);
        country.setName("Country name");

        Company company = Company.builder()
                .id(1L)
                .country(country)
                .identification("identification")
                .build();

        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("pixi");

        return CompanyBrand.builder()
                .company(company)
                .id(1L)
                .name("brand name")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .brands(brand)
                .logo("logo.jpg")
                .build();
    }

    private void executeGetRequest(String token, String path) throws Exception {

        mockMvc.perform(get(path)
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

}
