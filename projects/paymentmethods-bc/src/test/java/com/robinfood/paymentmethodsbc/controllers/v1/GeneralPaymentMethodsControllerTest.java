package com.robinfood.paymentmethodsbc.controllers.v1;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.robinfood.paymentmethodsbc.Application;
import com.robinfood.paymentmethodsbc.sample.GeneralPaymentMethodDetailsDTOSample;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
import com.robinfood.paymentmethodsbc.services.GeneralPaymentMethodService;
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
public class GeneralPaymentMethodsControllerTest {
    @Autowired
    private SSOTokenUtil ssoTokenUtil;

    @MockBean
    private GeneralPaymentMethodService paymentMethodService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_getPaymentMethodsByStoreChannelFlowAndTerminal_ShouldOk()
        throws Exception {
        String endpointTest = "/api/v1/paymentmethods?storeId=1&channelId=1&flowId=1&terminalUuid=abc";
        String accesToken = generateDummyValidToken();

        when(
            paymentMethodService.getPaymentMethodsByStoreChannelFlowAndTerminal(
                anyLong(),
                anyLong(),
                anyLong(),
                anyString()
            )
        )
            .thenReturn(GeneralPaymentMethodDetailsDTOSample.getGeneralPaymentMethodDetailsDTOList());

        mockMvc
            .perform(
                get(endpointTest)
                    .header("Authorization", "Bearer " + accesToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());

        verify(paymentMethodService, times(1))
            .getPaymentMethodsByStoreChannelFlowAndTerminal(
                anyLong(),
                anyLong(),
                anyLong(),
                anyString()
            );
    }

    @Test
    public void test_getPaymentMethodsByStoreChannelFlowAndTerminal_ShouldUnauthorized()
        throws Exception {
        String endpointTest = "/api/v1/paymentmethods?storeId=1&channelId=1&flowId=1&terminalUuid=abc";

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
}
