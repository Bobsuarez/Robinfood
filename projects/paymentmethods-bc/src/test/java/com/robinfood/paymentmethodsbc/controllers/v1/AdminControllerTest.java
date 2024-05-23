package com.robinfood.paymentmethodsbc.controllers.v1;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.robinfood.paymentmethodsbc.Application;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
import com.robinfood.paymentmethodsbc.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class AdminControllerTest {
    @Autowired
    private SSOTokenUtil ssoTokenUtil;

    @MockBean
    private AdminService adminService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * @throws Exception
     */
    @Test
    public void testRequestShouldOk() throws Exception {
        String endpointTest = "/api/v1/admin/cache/reset";
        String accesToken = generateDummyValidToken();

        when(adminService.cacheReset()).thenReturn("OK");

        mockMvc
            .perform(
                get(endpointTest)
                    .header("Authorization", "Bearer " + accesToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
    }

    /**
     * @return String
     */
    public String generateDummyValidToken() {
        return ssoTokenUtil.exampleToken(true);
    }
}
