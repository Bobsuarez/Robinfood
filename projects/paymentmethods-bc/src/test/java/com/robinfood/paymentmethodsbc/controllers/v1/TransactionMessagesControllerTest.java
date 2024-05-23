package com.robinfood.paymentmethodsbc.controllers.v1;

import com.robinfood.paymentmethodsbc.Application;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
import com.robinfood.paymentmethodsbc.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.robinfood.paymentmethodsbc.sample.JmsEntityRefundRequestDTOSample.getJmsEntityRefundRequestDTO;
import static com.robinfood.paymentmethodsbc.sample.JmsUpdateTransactionStatusDTOSample.getJmsUpdateTransactionStatusDTO;
import static com.robinfood.paymentmethodsbc.sample.PaymentRequestDTOSample.getPaymentRequestDTO;
import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class TransactionMessagesControllerTest {
    @Autowired
    private SSOTokenUtil ssoTokenUtil;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    public String generateDummyValidToken() {
        return ssoTokenUtil.exampleToken(true);
    }

    @Test
    void updateTransactionShouldBeOk() throws Exception {
        String endpointTest = "/api/v1/transactions/messages/update";
        String accesToken = generateDummyValidToken();

        doNothing().when(transactionService).updateTransactionStatusJMS(any(), anyString());

        mockMvc
            .perform(
                post(endpointTest)
                    .header("Authorization", "Bearer " + accesToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(convertToJson(getJmsUpdateTransactionStatusDTO()))
            )
            .andExpect(status().isOk());

        verify(transactionService, times(1))
            .updateTransactionStatusJMS(
                any(),
                anyString()
            );
    }

    @Test
    void createTransactionShouldBeOk() throws Exception {
        String endpointTest = "/api/v1/transactions/messages/create";
        String accesToken = generateDummyValidToken();

        doNothing().when(transactionService).doPayment(any());

        mockMvc
            .perform(
                post(endpointTest)
                    .header("Authorization", "Bearer " + accesToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(convertToJson(getPaymentRequestDTO()))
            )
            .andExpect(status().isOk());

        verify(transactionService, times(1))
            .doPayment(any());
    }

    @Test
    void refundTransactionShouldBeOk() throws Exception {
        String endpointTest = "/api/v1/transactions/messages/refund";
        String accesToken = generateDummyValidToken();

        mockMvc
            .perform(
                post(endpointTest)
                    .header("Authorization", "Bearer " + accesToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(convertToJson(getJmsEntityRefundRequestDTO()))
            )
            .andExpect(status().isOk());
    }
}
