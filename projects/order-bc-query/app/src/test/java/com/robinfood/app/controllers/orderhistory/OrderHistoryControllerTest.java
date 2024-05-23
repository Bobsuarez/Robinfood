package com.robinfood.app.controllers.orderhistory;

import com.robinfood.app.config.TestConfig;
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.response.EntityDTO;
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

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.GlobalConstants.TIMEZONE_BY_DEVICE_DEFAULT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class OrderHistoryControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIl0sInBlciI6WyJwb3NfY3JlYXRlX29yZGVyIiwib3JkZXJzX3JlamVjdF9vcmRlciJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.x1l16dphRwaY4F1kSCxbgk3FCY_j8fjhoXNoMZJ82kcw4zCfhomDZFKXhesw3F8lmg_H6eROnrWcbFjp1PvB-w";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGetOrderHistoryUseCase getOrderHistoryUseCase;

    @Test
    void test_getOrderHistoryByStore_should_ReturnBadRequest_when_DoesNotReceiveTheNecessaryData() throws Exception {

        when(getOrderHistoryUseCase.invoke(any(OrderHistoryRequestDTO.class)))
                .thenReturn(EntityDTO.<OrderHistoryItemDTO>builder().build());

        final AbstractApiResponseBuilderDTO<EntityDTO<OrderHistoryItemDTO>> apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(EntityDTO.<OrderHistoryItemDTO>builder().build());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/1/history")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_getOrderHistoryByStore_should_ReturnOk_when_DoesReceiveTheNecessaryData() throws Exception {

        when(getOrderHistoryUseCase.invoke(any(OrderHistoryRequestDTO.class)))
                .thenReturn(EntityDTO.<OrderHistoryItemDTO>builder().build());

        final AbstractApiResponseBuilderDTO<EntityDTO<OrderHistoryItemDTO>> apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(EntityDTO.<OrderHistoryItemDTO>builder().build());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/1/history")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .queryParam("localDateEnd", "2023-01-16")
                        .queryParam("localDateStart", "2023-01-12")
                        .header("timeZone", TIMEZONE_BY_DEVICE_DEFAULT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());
    }

}