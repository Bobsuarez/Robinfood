package com.robinfood.changestatusbc.controllers.changestatus;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;
import com.robinfood.changestatusbc.usecases.changestatus.IChangeOrderStateUseCase;
import com.robinfood.changestatusbc.usecases.validatechangestate.IValidateChangeStateUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.robinfood.changestatusbc.configs.constants.APIConstants.CHANGE_STATUS_ENDPOINT;
import static com.robinfood.changestatusbc.configs.constants.APIConstants.V1;
import static com.robinfood.changestatusbc.utilities.ObjectMapperSingleton.objectToJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IChangeOrderStateUseCase mockChangeOrderStateApiUseCase;

    @MockBean
    private IValidateChangeStateUseCase validateChangeStateUseCase;

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiIyYzhjNDYzNS0yM2I3LTQ1NDktYjBjMC1mZjMwNzQ2NDM0MmQiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg3NDYyOTkwLCJleHAiOjE5ODc0NjM1OTB9.eoynYjIgNp5AdMrU-7_agSD-j5W5EddTlAjrpk0ymFuy8Dib5vMrC0VVeVvjhgOL5DTgUfnSpLDPlcuv3YT08Q";

    private final ChangeOrderStatusDTO changeOrderStatusDTO = ChangeOrderStatusDTO
            .builder().orderId(1L).userId(1L).transactionId(1L).statusCode("prueba").build();

    private final WriteChangeStatusDTO writeChangeStatusDTO = WriteChangeStatusDTO.builder().orderId(1L).build();

    private final String URL = "https://prueba.com/";

    @Test
    void test_changeStatusOrder_When_Change_Order_State_Then_Return_Status_Change_Successful() throws Exception {

        when(mockChangeOrderStateApiUseCase.invoke(any()))
                .thenReturn(writeChangeStatusDTO);

        when(validateChangeStateUseCase.invoke(changeOrderStatusDTO)).thenReturn(Boolean.TRUE);

        mockMvc.perform(MockMvcRequestBuilders
                        .post( URL + V1 + CHANGE_STATUS_ENDPOINT)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(changeOrderStatusDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
