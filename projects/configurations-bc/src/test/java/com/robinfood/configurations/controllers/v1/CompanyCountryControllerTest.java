package com.robinfood.configurations.controllers.v1;

import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Country;
import com.robinfood.configurations.services.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CompanyCountryController.class)
@TestPropertySource(properties = {
        "jwt.token.mod=configurations_bc"
})
class CompanyCountryControllerTest extends BaseControllerTest{

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private BrandService brandService;

    @Autowired
    private MockMvc mockMvc;

    private static final String MODULES = "configurations_bc";
    private static final String ENCODING = "utf-8";

    @Test
    void testGetByBrandIdAndCompanyCountryId() throws Exception {

        Company company = Company.builder()
                .country(Country.builder().id(1L).build())
                .name("TEST")
                .id(1L)
                .build();

        when(brandService.getByBrandIdAndCompanyId(1L, 1L))
                .thenReturn(CompanyBrand.builder()
                        .name("TEST")
                        .logo("TEST")
                        .company(company)
                        .build());

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(get("/v1/company-countries/1/brands/1")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

}
