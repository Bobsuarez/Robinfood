package com.robinfood.localorderbc.controllers.changestatecontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.usecases.changestatususecase.IChangeStateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static com.robinfood.localorderbc.configs.constants.APIConstants.CHANGE_STATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ChangeStateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IChangeStateUseCase changeStateUseCase;

    ChangeStateDTO changeStateDTO = ChangeStateDTO.builder()
            .brandId("1")
            .notes("note")
            .orderId("12345")
            .userId(1L)
            .statusCode("ORDER_READY_TO_DELIVERED")
            .origin("AUTOGESTION")
            .build();

    @Test
    void Order_Change_State_Success() throws Exception {

        when(changeStateUseCase.invoke(any(ChangeStateDTO.class)))
                .thenReturn(changeStateDTO);

        var bodyResource = mockMvc.perform(
                        post(CHANGE_STATE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(changeStateDTO))
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertNotNull(bodyResource);
    }
}
