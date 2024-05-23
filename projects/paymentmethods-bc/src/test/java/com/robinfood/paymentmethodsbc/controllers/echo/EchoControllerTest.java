package com.robinfood.paymentmethodsbc.controllers.echo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.robinfood.paymentmethodsbc.Application;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
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
public class EchoControllerTest {
    @MockBean
    private CipherUtility cipherUtility;

    @Autowired
    private SSOTokenUtil ssoTokenUtil;

    @Autowired
    private MockMvc mockMvc;

    /**
     * @throws Exception
     */
    @Test
    public void testRequestShouldEcho() throws Exception {
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
     * @return String
     */
    public String generateDummyValidToken() {
        return ssoTokenUtil.exampleToken(true);
    }
}
