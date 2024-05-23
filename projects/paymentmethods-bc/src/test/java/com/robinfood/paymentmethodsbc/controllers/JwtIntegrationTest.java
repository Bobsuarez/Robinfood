package com.robinfood.paymentmethodsbc.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.robinfood.paymentmethodsbc.Application;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class JwtIntegrationTest {
    @Autowired
    private SSOTokenUtil ssoTokenUtil;

    @Autowired
    private MockMvc mockMvc;

    /**
     * @throws Exception
     */
    @Test
    public void testRequestShouldReturnSuccessfullWhenSendValidJWT()
        throws Exception {
        String endpointTest = "/echo?message=testing";
        String accesToken = generateDummyValidToken();
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
     * @throws Exception
     */
    @Test
    public void testRequestShouldReturnUnauthorizedWhenSendInValidJWT()
        throws Exception {
        String endpointTest = "/echo?message=testing";
        String accesToken = generateDummyNotValidToken();

        mockMvc
            .perform(
                get(endpointTest)
                    .header("Authorization", "Bearer " + accesToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isUnauthorized());
    }

    /**
     * @throws Exception
     */
    @Test
    public void testRequestShouldReturnUnauthorizedWhenNotSendJWT()
        throws Exception {
        String endpointTest = "/echo?message=testing";
        mockMvc
            .perform(
                get(endpointTest)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isUnauthorized());
    }

    /**
     * @return String
     */
    public String generateDummyValidToken() {
        return ssoTokenUtil.exampleToken(true);
    }

    /**
     * @return String
     */
    public String generateDummyNotValidToken() {
        return ssoTokenUtil.exampleInvalidToken();
    }
}
