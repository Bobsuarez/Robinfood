package com.robinfood.app.controllers.orders.orderdetailfiltercontroller;

import com.robinfood.app.config.TestConfig;
import com.robinfood.app.datamocks.dto.core.OrderDTOMock;
import com.robinfood.app.usecases.orderdetailfilter.IOrderDetailFilterUseCase;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.dtos.response.PaginationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class OrderDetailFilterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrderDetailFilterUseCase mockOrderDetailFilterUseCase;

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIl0sInBlciI6WyJwb3NfY3JlYXRlX29yZGVyIiwib3JkZXJzX3JlamVjdF9vcmRlciJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.x1l16dphRwaY4F1kSCxbgk3FCY_j8fjhoXNoMZJ82kcw4zCfhomDZFKXhesw3F8lmg_H6eROnrWcbFjp1PvB-w";
    private final String TIMEZONE_COL = "America/Bogota";

    @Test
    void Test_invoke_Should_RespondToOrdersAccordingToFilters_When_MakeTheRequestToTheEndpoint() throws Exception {

        Page<OrderDTO> orderDTO = getPageResponseOrders();

        when(mockOrderDetailFilterUseCase.invoke(
                anyInt(),
                anyString(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt(),
                anyLong(),
                anyString()
        )).thenReturn(orderDTO);

        final AbstractApiResponseBuilderDTO<EntityDTO<OrderDTO>> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(EntityDTO.<OrderDTO>builder()
                .items(orderDTO.getContent())
                .pagination(
                        PaginationDTO.builder()
                                .perPage(10)
                                .total(1L)
                                .lastPage(1)
                                .page(1)
                                .build()
                )
                .build());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/filter")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .queryParam("localDateStart", "2022-09-20")
                        .queryParam("localDateEnd", "2022-11-20")
                        .queryParam("storeId", "2")
                        .queryParam("filterText", "filter")
                        .queryParam("currentPage", "1")
                        .queryParam("perPage", "10")
                        .header("timeZone", TIMEZONE_COL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void Test_invoke_Should_BadRequest_When_MakeRequestToEndpointWithoutRequiredParameters() throws Exception {

        final AbstractApiResponseBuilderDTO<EntityDTO<OrderDTO>> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(EntityDTO.<OrderDTO>builder().build());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/filter")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .queryParam("localDateStart", "2022-09-20")
                        .header("timeZone", TIMEZONE_COL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private Page<OrderDTO> getPageResponseOrders() {

        Pageable pageable = PageRequest.of(0, 10);

        return new PageImpl<>(
                List.of(OrderDTOMock.build()),
                pageable,
                1
        );
    }

}