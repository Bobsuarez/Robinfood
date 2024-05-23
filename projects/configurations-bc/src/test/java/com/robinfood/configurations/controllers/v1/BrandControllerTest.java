package com.robinfood.configurations.controllers.v1;

import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.services.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BrandController.class)
@TestPropertySource(properties = {
        "jwt.token.mod=configurations_bc"
})
class BrandControllerTest extends BaseControllerTest {

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private BrandService brandService;

    @Autowired
    private MockMvc mockMvc;

    private static final String MODULES = "configurations_bc";
    private static final String ENCODING = "utf-8";

    private final List<Brand> getBrands = List.of(
            Brand.builder()
                    .id(1L)
                    .name("TEST")
                    .enabled(Boolean.TRUE)
                    .build()
    );

    @Test
    void testGetByBrands() throws Exception {

        when(brandService.list(any(), any())).thenReturn(new PageImpl<>(
                getBrands, PageRequest.of(1, 10), 10));

        when(brandService.count()).thenReturn(0L);

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

        executeGetRequest(token,"/v1/brands?size=10&enabled=true&page=0&sort=id,desc");
        executeGetRequest(token,"/v1/brands?size=10&enabled=false&page=0&sort=name,asc");
        executeGetRequest(token,"/v1/brands?size=10&enabled=true&page=0&sort=name,desc");
        executeGetRequest(token,"/v1/brands?page=0&sort=id,desc");
        executeGetRequest(token,"/v1/brands?page=0&unsorted=true&sorted=true&empty=true");
        executeGetRequest(token,"/v1/brands?page=0&unsorted=false&sorted=true&empty=true");
    }

   @Test
    void testFindAllShouldReturn200WhenRequestParamSizeZero() throws Exception {

       when(brandService.list(any(), any())).thenReturn(new PageImpl<>(
               getBrands, PageRequest.of(1, 10), 10));

       when(brandService.count()).thenReturn(1L);

       String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");

       executeGetRequest(token,"/v1/brands?page=0&sort=id,desc");
       executeGetRequest(token,"/v1/brands?page=0&sort=NS");
       executeGetRequest(token,"/v1/brands?page=0");
   }

    private void executeGetRequest(String token, String path) throws Exception {
        mockMvc.perform(get(path)
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
