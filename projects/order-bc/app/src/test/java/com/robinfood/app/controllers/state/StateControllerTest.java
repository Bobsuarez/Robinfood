package com.robinfood.app.controllers.state;

import com.robinfood.app.config.TestConfig;
import com.robinfood.app.usecases.changestatusorders.IChangeOrderStateUseCase;
import com.robinfood.app.usecases.validatechangestate.IValidateChangeStateUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
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

import static com.robinfood.core.constants.APIConstants.STATE_V1;
import static com.robinfood.core.extensions.ObjectExtensions.toJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IChangeOrderStateUseCase mockChangeOrderStateApiUseCase;

    @MockBean
    private IValidateChangeStateUseCase validateChangeStateUseCase;
    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9" +
            ".eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIl0sInBlciI6WyJwb3NfY3JlYXRlX29yZGVyIiwib3JkZXJzX3JlamVjdF9vcmRlciJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.x1l16dphRwaY4F1kSCxbgk3FCY_j8fjhoXNoMZJ82kcw4zCfhomDZFKXhesw3F8lmg_H6eROnrWcbFjp1PvB-w";
    private final String TIMEZONE_COL = "America/Bogota";

    private final WriteChangeStatusDTO writeChangeStatusDTO = WriteChangeStatusDTO.builder().orderId(1L).build();

    private final ChangeOrderStatusDTO changeOrderStatusDTO = ChangeOrderStatusDTO
            .builder().orderId(1L).userId(1L).transactionId(1L).statusCode("prueba").build();

    @Test
    void test_When_Change_Order_State_Then_Return_Write_Json_Queue() throws Exception {

        when(mockChangeOrderStateApiUseCase.invoke(any()))
                .thenReturn(writeChangeStatusDTO);

        when(validateChangeStateUseCase.invoke(changeOrderStatusDTO)).thenReturn(Boolean.TRUE);

        final AbstractApiResponseBuilderDTO<WriteChangeStatusDTO> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(writeChangeStatusDTO);


        mockMvc.perform(MockMvcRequestBuilders
                        .post(STATE_V1)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(changeOrderStatusDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_When_Change_Order_State_Failded_Then_Return_Exception() throws Exception {

        when(mockChangeOrderStateApiUseCase.invoke(any()))
                .thenReturn(writeChangeStatusDTO);

        final AbstractApiResponseBuilderDTO<WriteChangeStatusDTO> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(writeChangeStatusDTO);

        changeOrderStatusDTO.setStatusCode("");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(STATE_V1)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(changeOrderStatusDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
