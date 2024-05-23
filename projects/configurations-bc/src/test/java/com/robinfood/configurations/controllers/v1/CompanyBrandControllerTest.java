package com.robinfood.configurations.controllers.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Country;
import com.robinfood.configurations.models.StoreBrand;
import com.robinfood.configurations.services.CompanyBrandService;
import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CompanyBrandController.class)
@TestPropertySource(properties = {"jwt.token.mod=configurations_bc"})
class CompanyBrandControllerTest extends BaseControllerTest {

    private static final String MODULES = "configurations_bc";

    @MockBean
    private CompanyBrandService companyBrandService;

    @SpyBean
    private ModelMapper modelMapper;

    @InjectMocks
    private CompanyBrandController companyBrandController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_FindById_Should_Return200Code_When_ServiceIsCalled() throws Exception {

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        when(companyBrandService.findById(anyLong())).thenReturn(getCompanyBrand());

        this.mockMvc.perform(get("/v1/company-brands/1").header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200)).andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty()).andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_FindByMenuBrandId_Should_Return200Code_When_ServiceIsCalled() throws Exception {

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        when(companyBrandService.getByCompanyIdAndMenuBrandId(anyLong())).thenReturn(
            getCompanyBrand());

        this.mockMvc.perform(
                get("/v1/company-brands/menu-brand/1").header(HttpHeaders.AUTHORIZATION, token)
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200)).andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty()).andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_FindByMenuBrandId_Should_Return404Code_When_EntityNotFound() throws Exception {
        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        when(companyBrandService.getByCompanyIdAndMenuBrandId(anyLong())).thenReturn(null);

        this.mockMvc.perform(
                get("/v1/company-brands/menu-brand/1").header(HttpHeaders.AUTHORIZATION, token)
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.data").isEmpty()).andExpect(jsonPath("$.error").isNotEmpty());
    }

    private CompanyBrand getCompanyBrand() {

        Country country = new Country();
        country.setId(1L);
        country.setName("Country name");

        Company company = Company.builder().id(1L).country(country).identification("identification")
            .build();

        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("pixi");

        return CompanyBrand.builder().company(company).id(1L).name("brand name")
            .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).brands(brand)
            .logo("logo.jpg").build();
    }

}
