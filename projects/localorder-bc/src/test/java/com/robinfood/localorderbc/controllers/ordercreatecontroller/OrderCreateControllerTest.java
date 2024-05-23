package com.robinfood.localorderbc.controllers.ordercreatecontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localorderbc.dtos.OrderDTO;
import com.robinfood.localorderbc.usecases.ordercreateusecase.IOrderCreateUseCase;
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

import static com.robinfood.localorderbc.configs.constants.APIConstants.UPDATE_ORDER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.robinfood.localorderbc.configs.constants.APIConstants.ORDER_V1;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class OrderCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IOrderCreateUseCase orderCreateUseCase;

    private OrderDTO orderDTO = OrderDTO
            .builder()
            .id(1L)
            .originId(1L)
            .originName("origin")
            .brandId(1L)
            .brandName("brand")
            .data("{}")
            .printed(true)
            .statusId(1L)
            .statusName("status")
            .uid("uid")
            .build();

    @Test
    void Order_Create_Success() throws Exception {

        when(orderCreateUseCase.invoke(any(OrderDTO.class)))
                .thenReturn(orderDTO);

        var bodyResource = mockMvc.perform(
                        post(ORDER_V1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderDTO))
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertNotNull(bodyResource);
    }


    @Test
    void Order_Update_Success() throws Exception {

        when(orderCreateUseCase.update(anyLong(), anyLong()))
                .thenReturn(orderDTO);

        var bodyResource = mockMvc.perform(
                        post(ORDER_V1+UPDATE_ORDER)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderDTO))
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertNotNull(bodyResource);
    }

}
