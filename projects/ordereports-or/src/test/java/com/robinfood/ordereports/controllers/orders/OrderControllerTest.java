package com.robinfood.ordereports.controllers.orders;

import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;
import com.robinfood.ordereports.usecases.getorderdetail.IGetOrderDetailUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.robinfood.ordereports.constants.APIConstants.TRANSACTION;
import static com.robinfood.ordereports.constants.APIConstants.TRANSACTION_UUID;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjQ5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIl0sInBlciI6WyJvcmNoX21lbnVfc2VydmljZXMiLCJjaGVja1VzZXJEYXRhIiwiY2hlY2tfdXNlcl9kYXRhIiwidGF4ZXMiLCJzeW5jT3JkZXIiLCJDUkVBVEVfVFJBTlNBQ1RJT04iLCJjaGVja0NvbXBhbnlEYXRhIiwiY2hlY2tfY29tcGFueV9kYXRhIiwiVEFYRVNfQkNfU0VSVklDRSIsIlRBWEVTX0JDX0xJU1RfSVRFTV9UQVgiLCJsb3lhbHR5X3RyYW5zYWN0aW9uX2NyZWRpdHMiLCJvcmRlcl9zeW5jIiwiT1JERVJfU1lOQyIsImJjX3NnaV9vcmRlci1yZWFkLWZpbmFuY2VfY2F0ZWdvcmllcyJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.ywegG46JRe1oXfk9Z3vjqxVF2IYQ6Io1qnOl-kZBcDIFtcv-fUZZ8l_DvHxmJ2-y7vbF4bgXZ4Gpin8CDemUAw";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IGetOrderDetailUseCase getOrderDetailUseCase;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void test_GetOrderDetail_When_Is_Ok() throws Exception {
        OrderDetailDTO mockOrderDetail = new OrderDetailDTO();

        when(getOrderDetailUseCase.invoke(anyString())).thenReturn(mockOrderDetail);

        mockMvc.perform(get(TRANSACTION+"/ABC-123")
                        .param("orderId", "1")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").value(mockOrderDetail));
    }

    /*

    @Test
    public void test_GetOrderDetail_When_Throws_Exception() throws Exception {
        when(getOrderDetailUseCase.invoke(anyLong())).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get(ORDERS_V1 + ORDER_DETAIL)
                        .param("orderId", "1")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Unexpected error"));
    }

     */

}
