package com.robinfood.localorderbc.controllers.getorderdetailcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localorderbc.usecases.findallorderbystatusidusecase.IFindAllOrdersByStatusIdUseCase;
import com.robinfood.localorderbc.usecases.getorderdatailbyidusecase.IGetOrderDetailByIdUseCase;
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

import static com.robinfood.localorderbc.configs.constants.APIConstants.GET_ORDERS_BY_STATUS_ID;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.robinfood.localorderbc.configs.constants.APIConstants.ORDER_V1;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class GetOrderDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IGetOrderDetailByIdUseCase getOrderDetailByIdUseCase;
    @MockBean
    private IFindAllOrdersByStatusIdUseCase iFindAllOrdersByStatusIdUseCase;

    @Test
    void Get_Order_Success() throws Exception {

        when(getOrderDetailByIdUseCase.invoke(anyLong()))
                .thenReturn("{}");

        var bodyResource = mockMvc.perform(
                        get(ORDER_V1 + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertNotNull(bodyResource);
    }

    @Test
    void Get_All_Order_By_Status_Success() throws Exception {

        when(iFindAllOrdersByStatusIdUseCase.invoke(anyLong()))
                .thenReturn(null);

        var bodyResource = mockMvc.perform(
                        get(ORDER_V1+GET_ORDERS_BY_STATUS_ID + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertNotNull(bodyResource);
    }

}