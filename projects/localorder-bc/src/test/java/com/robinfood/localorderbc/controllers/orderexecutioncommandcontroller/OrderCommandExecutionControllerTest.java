package com.robinfood.localorderbc.controllers.orderexecutioncommandcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;
import com.robinfood.localorderbc.mocks.dtos.request.OrderCommandExecutionDTOMock;
import com.robinfood.localorderbc.mocks.dtos.request.OrderCommandExecutionRequestDTOMock;
import com.robinfood.localorderbc.usecases.orderexecutioncommandusecase.ICommandExecutionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.StandardCharsets;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.robinfood.localorderbc.configs.constants.APIConstants.COMMAND_EXECUTION;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class OrderCommandExecutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICommandExecutionUseCase commandExecutionUseCase;

    final OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTOMock =
            new OrderCommandExecutionRequestDTOMock().orderCommandExecutionRequestDTO;

    final OrderCommandExecutionDTO orderCommandExecutionResultDTO =
            new OrderCommandExecutionDTOMock().orderCommandExecutionResultDTO;


    @Test
    void Order_Command_Execution_Controller_Success() throws Exception {

        when(commandExecutionUseCase.invoke(any()))
                .thenReturn(orderCommandExecutionResultDTO);

        var bodyResource = mockMvc.perform(
                        post(COMMAND_EXECUTION)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderCommandExecutionRequestDTOMock))
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertNotNull(bodyResource);
    }

}
