package com.robinfood.localorderbc.controllers.getorderexecutioncommandcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.mocks.dtos.request.OrderCommandExecutionDTOMock;
import com.robinfood.localorderbc.usecases.getorderexecutioncommandusecase.IGetOrderCommandExecutionUseCase;
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
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.robinfood.localorderbc.configs.constants.APIConstants.COMMAND_EXECUTION;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class GetOrderCommandExecutionControllerTest {

    @MockBean
    private IGetOrderCommandExecutionUseCase getOrderCommandExecutionUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    final List<OrderCommandExecutionDTO> orderCommandExecutionDTOList =
            new OrderCommandExecutionDTOMock().orderCommandExecutionDTOList;

    @Test
    void Order_Command_Execution_List_Controller_Success() throws Exception {

        when(getOrderCommandExecutionUseCase.invoke())
                .thenReturn(orderCommandExecutionDTOList);

        var bodyResource = mockMvc.perform(
                        get(COMMAND_EXECUTION)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertNotNull(bodyResource);
    }


}
