package com.robinfood.app.controllers.users;

import com.robinfood.app.config.TestConfig;
import com.robinfood.app.usecases.getuseractiveorder.IGetUserActiveOrderUseCase;
import com.robinfood.app.usecases.getuserorderdetail.IGetUserOrderDetailByUIdUseCase;
import com.robinfood.app.usecases.getuserorderhistory.IGetUserOrderHistoryUseCase;
import com.robinfood.app.usecases.hasuserappliedconsumptiontoday.IHasUserAppliedConsumptionTodayUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.dtos.response.PaginationDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderStatusDTO;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.repository.orderfoodcoins.IOrderFoodCoinRepository;
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
import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.USERS_V1;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UsersControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIl0sInBlciI6WyJwb3NfY3JlYXRlX29yZGVyIiwib3JkZXJzX3JlamVjdF9vcmRlciJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.x1l16dphRwaY4F1kSCxbgk3FCY_j8fjhoXNoMZJ82kcw4zCfhomDZFKXhesw3F8lmg_H6eROnrWcbFjp1PvB-w";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IHasUserAppliedConsumptionTodayUseCase mockHasUserAppliedConsumptionTodayUseCase;
    @MockBean
    private IGetUserOrderHistoryUseCase getOrderHistoryByUserIdUseCase;
    @MockBean
    private IGetUserOrderDetailByUIdUseCase getUserOrderDetailByUIdUseCase;
    @MockBean
    private IGetUserActiveOrderUseCase getUserActiveOrderUseCase;

    @MockBean
    private IOrderFoodCoinRepository mockOrderFoodCoinRepository;

    private final LocalDate currentTime = LocalDate.of(2021, 6, 3);
    private final Long userId = 1L;

    @Test
    void test_HasUserAppliedConsumptionTodayUseCase_Returns_Correctly() throws Exception {
        when(mockHasUserAppliedConsumptionTodayUseCase.invoke(currentTime, userId)).thenReturn(true);
        final AbstractApiResponseBuilderDTO<Boolean> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(true);
        mockMvc.perform(MockMvcRequestBuilders
                .get(USERS_V1 + "/1/has-applied-consumption-today")
                .queryParam("createdAt", "2021-06-03")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_GetOrderHistoryByUser_Returns_Correctly() throws Exception {
        when(getOrderHistoryByUserIdUseCase.invoke(1, 10, userId))
            .thenReturn(getPageResponseOrders());

        final AbstractApiResponseBuilderDTO<EntityDTO<ResponseOrderDTO>> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getResponseEntityDTO());

        mockMvc.perform(MockMvcRequestBuilders
                .get(USERS_V1 + "/1/orders")
                .queryParam("currentPage", "1")
                .queryParam("perPage", "10")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_GetOrderHistoryByUserAndStatusIds_Returns_Correctly() throws Exception {
        when(getOrderHistoryByUserIdUseCase.invoke(1, 10, userId))
            .thenReturn(getPageResponseOrders());

        final AbstractApiResponseBuilderDTO<EntityDTO<ResponseOrderDTO>> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getResponseEntityDTO());

        mockMvc.perform(MockMvcRequestBuilders
                .get(USERS_V1 + "/1/orders")
                .queryParam("currentPage", "1")
                .queryParam("perPage", "10")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_GetOrderDetailByUserAndOrder_Returns_Correctly() throws Exception {
        when(getUserOrderDetailByUIdUseCase.invoke("12345abcde", userId))
            .thenReturn(getResponseOrderDetail());

        final AbstractApiResponseBuilderDTO<ResponseOrderDetailDTO> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getResponseOrderDetail());

        mockMvc.perform(MockMvcRequestBuilders
                .get(USERS_V1 + "/1/orders/12345abcde")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_GetOrderDetailByUserAndOrder_NotFound_Returns_Failure() throws Exception {
        when(getUserOrderDetailByUIdUseCase.invoke("12345abcde", userId))
            .thenThrow(ResourceNotFoundException.class);

        final AbstractApiResponseBuilderDTO<ResponseOrderDetailDTO> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getResponseOrderDetail());

        mockMvc.perform(MockMvcRequestBuilders
                .get(USERS_V1 + "/1/orders/12345abcde")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNotFound());
    }

    @Test
    void test_GetActiveOrdersByUser_Returns_Correctly() throws Exception {
        when(getUserActiveOrderUseCase.invoke(userId))
            .thenReturn(getResponseOrders());

        final AbstractApiResponseBuilderDTO<EntityDTO<ResponseOrderDTO>> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getResponseEntityDTO());

        mockMvc.perform(MockMvcRequestBuilders
                .get(USERS_V1 + "/1/orders/active")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_GetActiveOrdersByUserAndStatusIds_Returns_Correctly() throws Exception {
        when(getUserActiveOrderUseCase.invoke(userId))
            .thenReturn(getResponseOrders());

        final AbstractApiResponseBuilderDTO<EntityDTO<ResponseOrderDTO>> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(getResponseEntityDTO());

        mockMvc.perform(MockMvcRequestBuilders
                .get(USERS_V1 + "/1/orders/active")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    private List<ResponseOrderDTO> getResponseOrders() {
        return List.of(
            ResponseOrderDTO.builder()
                .id(1L)
                .uid("12345abcde")
                .paid(true)
                .flowId(1L)
                .platformId(1L)
                .status(
                    ResponseOrderStatusDTO.builder()
                        .id(3L)
                        .name("En proceso")
                        .build()
                )
                .datetime(LocalDateTime.now())
                .build()
        );
    }

    private Page<ResponseOrderDTO> getPageResponseOrders() {

        Pageable pageable = PageRequest.of(0, 10);

        return new PageImpl<>(
            getResponseOrders(),
            pageable,
            1
        );
    }

    private EntityDTO<ResponseOrderDTO> getResponseEntityDTO() {
        Page<ResponseOrderDTO> historyDTOPage = getPageResponseOrders();

        return EntityDTO.<ResponseOrderDTO>builder()
            .items(historyDTOPage.getContent())
            .pagination(
                PaginationDTO.builder()
                    .perPage(10)
                    .total(1L)
                    .lastPage(1)
                    .page(1)
                    .build()
            )
            .build();
    }

    private ResponseOrderDetailDTO getResponseOrderDetail() {
        return ResponseOrderDetailDTO.builder()
            .id(1L)
            .uid("12345abcde")
            .flowId(1L)
            .paid(true)
            .build();
    }

}