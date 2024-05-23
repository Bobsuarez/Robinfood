package com.robinfood.configurations.controllers.v1;

import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.samples.HeadquarterSample;
import com.robinfood.configurations.services.HeadquartersService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = HeadquartersController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=configurations_bc"
})
public class HeadquartersControllerTest extends BaseControllerTest {

    @MockBean
    private HeadquartersService service;

    @Autowired
    private MockMvc mockMvc;

    private static final String MODULES = "configurations_bc";
    private static final String ENCODING = "utf-8";

    @Test
    void testGetHeadquartersByCompanyCountryId() throws Exception {
        when(service.getByCompanyCountryId(1L)
        ).thenReturn(HeadquarterSample.getHeadquarter());

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(get("/v1/companies/company-countries/1/headquarters")
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
}
