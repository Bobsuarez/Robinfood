package com.robinfood.configurations.controllers.v1;

import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.samples.CitySample;
import com.robinfood.configurations.services.CityService;
import java.util.List;
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

import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.LIST_TIME_ZONES;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CityController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=configurations_bc"
})
class CityControllerTest extends BaseControllerTest {

    @MockBean
    private CityService cityService;

    @Autowired
    private MockMvc mockMvc;

    private static final String MODULES = "configurations_bc";
    private static final String ENCODING = "utf-8";

    @Test
    void testFindCitiesByCountryShouldReturn401WhenAudienceIsNotValid() throws Exception {
        String token = getJWT("test_error", "test_error", "internal");

        mockMvc.perform(get("/v1/countries/1/cities")
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
    void testFindCitiesByCountryShouldBeOk() throws Exception {
        when(
            cityService.findCitiesByCountry(1L)
        ).thenReturn(CitySample.getCities());

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(get("/v1/countries/1/cities")
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
    void test_ListTimeZoneByCompanyCountryId_Should_Return200_WhenReceiveCompanyCountryId()
        throws Exception {
        String token = getJWT(MODULES, CONFIGURATIONS_PREFIX + LIST_TIME_ZONES, "service");

        when(cityService.listTimeZonesByCompanyCountryId(anyLong()))
            .thenReturn(List.of("America/Bogota"));

        mockMvc.perform(get("/v1/countries/cities/time-zones?company_country_id=1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").value("List time zones retrieved successfully"))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }


}
