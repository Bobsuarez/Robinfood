package com.robinfood.app.controllers.transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.app.commandhandler.IInvokerCommand;
import com.robinfood.app.controllers.BaseTestController;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.constants.PermissionConstants;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.extensions.ObjectExtensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.robinfood.core.constants.APIConstants.TRANSACTION_V1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest extends BaseTestController {

    private final String modules = "orch_order";
    private final TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

    private MockMvc mockMvc;

    @Mock
    private IInvokerCommand mockInvokerCommand;

    @InjectMocks
    private TransactionController controller;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_CreateOrder_Completes_Successfully() throws Exception {

        final String token = getJWT(modules, PermissionConstants.CREATE_TRANSACTION);

        doNothing().when(mockInvokerCommand).group(anyString(), any());

        mockMvc.perform(post(TRANSACTION_V1)
                .content(ObjectExtensions.toJson(transactionRequest))
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void test_CreateOrder_When_Origin_Header_Is_Missing() throws Exception {

        final String token = getJWT(modules, PermissionConstants.CREATE_TRANSACTION);

        mockMvc.perform(post(TRANSACTION_V1)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_CreateOrder_When_Request_Body_Is_Missing() throws Exception {

        final String token = getJWT(modules, PermissionConstants.CREATE_TRANSACTION);

        mockMvc.perform(post(TRANSACTION_V1)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_OrderCreation_Exception_Failure() throws Exception {

        final String token = getJWT(modules, PermissionConstants.CREATE_TRANSACTION);

        mockMvc.perform(post(TRANSACTION_V1)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
