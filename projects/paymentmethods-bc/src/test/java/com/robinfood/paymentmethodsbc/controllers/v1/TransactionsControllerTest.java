package com.robinfood.paymentmethodsbc.controllers.v1;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.paymentmethodsbc.Application;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionStatusUpdateRequestDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.sample.TransactionEntityDTOSample;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.security.LambdaTokenUtil;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
import com.robinfood.paymentmethodsbc.services.TransactionService;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = true)
@ExtendWith(MockitoExtension.class)
public class TransactionsControllerTest {
    private static final String END_POINT_TEST = "/api/v1/transactions";

    @Autowired
    private SSOTokenUtil ssoTokenUtil;

    @Autowired
    private LambdaTokenUtil lambdaTokenUtil;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGenerateTransactionShouldBe201WhenSendAllParameters()
        throws Exception {
        when(
            transactionService.createPaymentInitialTransaction(
                any(PaymentRequestDTO.class)
            )
        )
            .thenReturn(new PaymentInitResponseDTO());

        this.mockMvc.perform(
                post(END_POINT_TEST)
                    .header("Authorization", "Bearer " + generateValidToken())
                    .content(
                        asJsonString(
                            TransactionSamples.getTransactionRequestDTO()
                        )
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(201));
    }

    @Test
    public void testGenerateTransactionShouldBeErrorWhenInvalidToken()
        throws Exception {
        this.mockMvc.perform(
                post(END_POINT_TEST)
                    .header("Authorization", "Bearer " + generateInvalidToken())
                    .content(
                        asJsonString(
                            TransactionSamples.getTransactionRequestDTO()
                        )
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().is4xxClientError())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(401)));
    }

    @Test
    public void testUpdateTransactionStatusShouldBe200() throws Exception {
        final String url = END_POINT_TEST + "/abc/status";

        when(
            transactionService.updateTransactionStatus(
                any(TransactionStatusUpdateRequestDTO.class)
            )
        ).thenReturn("[accepted]");

        this.mockMvc.perform(
                post(url)
                    .header(
                        "Authorization",
                        "Bearer " + generateValidServiceToken()
                    )
                    .content(
                        asJsonString(
                            TransactionSamples.getTransactionStatusUpdateRequestDTO()
                        )
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

    @Test
    public void testUpdateTransactionStatusShouldBeErrorWhenInvalidToken()
        throws Exception {
        when(
            transactionService.updateTransactionStatus(
                any(TransactionStatusUpdateRequestDTO.class)
            )
        ).thenReturn("[accepted]");

        this.mockMvc.perform(
                post(END_POINT_TEST + "/abc/status")
                    .header(
                        "Authorization",
                        "Bearer " + generateInvalidServiceToken()
                    )
                    .content(
                        asJsonString(
                            TransactionSamples.getTransactionStatusUpdateRequestDTO()
                        )
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(401));
    }

    @Test
    public void testRefundBeOk() throws Exception {
        when(
            transactionService.doRefund(
                any(RefundRequestDTO.class)
            )
        )
            .thenReturn(TransactionSamples.getRefundResponseDTO());

        this.mockMvc.perform(
                put(END_POINT_TEST + "/refund")
                    .header("Authorization", "Bearer " + generateValidToken())
                    .content(
                        asJsonString(TransactionSamples.getRefundRequestDTO())
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

    @Test
    public void testGetTransactionsByEntityInfoShouldBeOkWhitUuid() throws Exception {
        when(transactionService.getTransactionsByEntityInfo(any(),any() ,any() ))
            .thenReturn(TransactionEntityDTOSample.getListTransactionEntityDTO());

        mockMvc.perform(get(END_POINT_TEST.concat("?").concat("uuid=abcd") )
                            .header("Authorization", "Bearer " + generateValidToken())
                            .accept(MediaType.APPLICATION_JSON)

               )
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.code").value("200"))
               .andExpect(jsonPath("$.message").value("SOLICITUD EXITOSA"))
               .andExpect(jsonPath("$.data[0].uuid").value("095fceb0-c36c-4d98-8d74-38e568dde06d"))
               .andExpect(jsonPath("$.data[0].status.id").value(1L));
    }

    @Test
    public void testGetTransactionsByEntityInfoShouldBeOkWhitEntitiSourceId() throws Exception {
        when(transactionService.getTransactionsByEntityInfo(any(),any() ,any() ))
            .thenReturn(TransactionEntityDTOSample.getListTransactionEntityDTO());

        mockMvc.perform(get(END_POINT_TEST.concat("?").concat("uuid=abcd&entity_source_id=123") )
                            .header("Authorization", "Bearer " + generateValidToken())
                            .accept(MediaType.APPLICATION_JSON)

               )
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.code").value("200"))
               .andExpect(jsonPath("$.message").value("SOLICITUD EXITOSA"))
               .andExpect(jsonPath("$.data[0].uuid").value("095fceb0-c36c-4d98-8d74-38e568dde06d"))
               .andExpect(jsonPath("$.data[0].status.id").value(1L));
    }

    @Test
    public void testGetTransactionsByEntityInfoShouldBeOkWhitOnlyEntitiReference() throws Exception {
        when(transactionService.getTransactionsByEntityInfo(any(),any() ,any() ))
            .thenReturn(TransactionEntityDTOSample.getListTransactionEntityDTO());

        mockMvc.perform(get(END_POINT_TEST.concat("?").concat("entity_reference=abcd") )
                            .header("Authorization", "Bearer " + generateValidToken())
                            .accept(MediaType.APPLICATION_JSON)

               )
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.code").value("200"))
               .andExpect(jsonPath("$.message").value("SOLICITUD EXITOSA"))
               .andExpect(jsonPath("$.data[0].uuid").value("095fceb0-c36c-4d98-8d74-38e568dde06d"))
               .andExpect(jsonPath("$.data[0].status.id").value(1L));
    }


    @Test
    public void testGetTransactionsByEntityInfoShouldBeOkWhitEntitiSourceIdNotNull() throws Exception {
        when(transactionService.getTransactionsByEntityInfo(any(),any() ,any() ))
            .thenReturn(TransactionEntityDTOSample.getListTransactionEntityDTO());

        mockMvc.perform(get(END_POINT_TEST.concat("?").concat("entity_source_id=123") )
                            .header("Authorization", "Bearer " + generateValidToken())
                            .accept(MediaType.APPLICATION_JSON)

               )
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.code").value("200"))
               .andExpect(jsonPath("$.message").value("SOLICITUD EXITOSA"))
               .andExpect(jsonPath("$.data[0].uuid").value("095fceb0-c36c-4d98-8d74-38e568dde06d"))
               .andExpect(jsonPath("$.data[0].status.id").value(1L));
    }


    @Test
    public void testGetTransactionsByEntityInfoShouldBeOkWhitEntitiReference() throws Exception {
        when(transactionService.getTransactionsByEntityInfo(any(),any() ,any() ))
            .thenReturn(TransactionEntityDTOSample.getListTransactionEntityDTO());

        mockMvc.perform(get(END_POINT_TEST.concat("?").concat("entity_reference=abcde&uuid=abcd&entity_source_id=123") )
                            .header("Authorization", "Bearer " + generateValidToken())
                            .accept(MediaType.APPLICATION_JSON)

               )
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.code").value("200"))
               .andExpect(jsonPath("$.message").value("SOLICITUD EXITOSA"))
               .andExpect(jsonPath("$.data[0].uuid").value("095fceb0-c36c-4d98-8d74-38e568dde06d"))
               .andExpect(jsonPath("$.data[0].status.id").value(1L));
    }

    @Test
    public void testGetTransactionsByEntityInfoShouldBeErrorWhitOutParameters() throws Exception {


        mockMvc.perform(get(END_POINT_TEST)
                            .header("Authorization", "Bearer " + generateValidToken())
                            .accept(MediaType.APPLICATION_JSON)

               )
               .andExpect(status().is4xxClientError())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.code").value("400"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateValidToken() {
        return ssoTokenUtil.exampleToken(true);
    }

    public String generateInvalidToken() {
        return ssoTokenUtil.exampleInvalidToken();
    }

    /**
     * Genera un token de servicio con proposito de pruebas internas
     *
     * @return {@linkplain String} token de servicio válido
     */
    public String generateValidServiceToken() {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("aud", "internal");
        claims.put("iss", "web.v1");
        return lambdaTokenUtil.doGenerateToken(claims);
    }

    /**
     * Genera un token de servicio con errores de estructura
     *
     * @return {@linkplain String} token de servicio invalido
     */
    public String generateInvalidServiceToken() {
        final Map<String, Object> claims = new HashMap<>();
        return lambdaTokenUtil.doGenerateToken(claims);
    }
}
