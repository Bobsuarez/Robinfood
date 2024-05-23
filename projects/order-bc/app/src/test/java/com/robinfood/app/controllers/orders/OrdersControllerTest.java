package com.robinfood.app.controllers.orders;

import com.robinfood.app.config.TestConfig;
import com.robinfood.app.datamocks.dto.core.GetDataElectronicBillingDTOMock;
import com.robinfood.app.datamocks.dto.core.UserDataDTOMock;
import com.robinfood.app.datamocks.dto.input.OrderFiscalIdentifierDTODataMock;
import com.robinfood.app.datamocks.dto.core.GetOrderThirdPartyDTOMock;
import com.robinfood.app.usecases.createordercoupon.ICreateOrderCouponUseCase;
import com.robinfood.app.usecases.createordertransaction.ICreateOrderTransactionUseCase;
import com.robinfood.app.usecases.exitstransactionuuidandorderuids.IExitsTransactionUuidAndOrderUuidsUseCase;
import com.robinfood.app.usecases.getdailysalessummary.IGetDailySalesSummaryByStoreIdAndDateUseCase;
import com.robinfood.app.usecases.getdetailorderbyuids.IGetOrderDetailByUidsUseCase;
import com.robinfood.app.usecases.getorderdetailbyidsanduids.IGetOrderDetailByIdsAndsUidsUseCase;
import com.robinfood.app.usecases.getorderdetailorder.IGetOrderDetailOrderUseCase;
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase;
import com.robinfood.app.usecases.getordersbytransaction.IGetOrdersByTransactionIdUseCase;
import com.robinfood.app.usecases.getordertotaldailysalesbyparams.IGetOrdersTotalDailySalesByParamsUseCase;
import com.robinfood.app.usecases.getstate.IGetStateOrderWithCodeUseCase;
import com.robinfood.app.usecases.getstateorders.IGetStateOrderUseCase;
import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductDTO;
import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.GetOrderTotalDailySalesDTO;
import com.robinfood.core.dtos.HistoryDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.dtos.PropertyDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.response.order.ResponseExistsTransactionUuidOrderUidDTO;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.robinfood.core.constants.APIConstants.EXIST_TRANSACTION_UUID_ORDER_UID;
import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.ORDER_DETAIL_PRINT;
import static com.robinfood.core.constants.APIConstants.ORDER_HISTORY;
import static com.robinfood.core.extensions.ObjectExtensions.toJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class OrdersControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIl0sInBlciI6WyJwb3NfY3JlYXRlX29yZGVyIiwib3JkZXJzX3JlamVjdF9vcmRlciJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.x1l16dphRwaY4F1kSCxbgk3FCY_j8fjhoXNoMZJ82kcw4zCfhomDZFKXhesw3F8lmg_H6eROnrWcbFjp1PvB-w";
    private final String TIMEZONE_COL = "America/Bogota";
    private final List<Long> idsList = new ArrayList<>(Collections.singletonList(
            1L
    ));
    private final List<String> uidsList = new ArrayList<>(Collections.singletonList(
            "1234"
    ));
    private final List<GetOrderDetailDiscountDTO> getOrderDetailDiscountDTOSByOrderId = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailDiscountDTO(
                            1L,
                            1L,
                            null,
                            1L,
                            5000.0
                    )
            )
    );
    private final List<GetOrderDetailPaymentMethodDTO> getOrderDetailPaymentMethodDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailPaymentMethodDTO(
                            0.0,
                            1L,
                            8900.0,
                            1L,
                            1L,
                            0.0,
                            8900.0
                    )
            )
    );

    private final List<GetOrderDetailFinalProductDTO> getOrderDetailFinalProductDTOS = List.of(
            GetOrderDetailFinalProductDTO.builder()
                    .articleId(1L)
                    .basePrice(new BigDecimal("8900.0"))
                    .brandId(1L)
                    .brandName("muy")
                    .categoryId(1L)
                    .categoryName("Category")
                    .id(1L)
                    .image("image.png")
                    .brandMenuId(1L)
                    .name("paisa")
                    .quantity(1)
                    .unitPrice(new BigDecimal("8900.0"))
                    .sizeId(1L)
                    .sizeName("muy")
                    .discount(BigDecimal.valueOf(8900.0))
                    .deduction(BigDecimal.ZERO)
                    .orderId(1L)
                    .co2Total(BigDecimal.ZERO)
                    .total(0.0)
                    .build()
    );
    private final UserDataDTO userDataDTO = UserDataDTOMock.getDataDefault();

    private final OrderThirdPartyDTO orderThirdPartyDTO = GetOrderThirdPartyDTOMock.getDataDefault();

    private final DataElectronicBillingDTO dataElectronicBillingDTO = GetDataElectronicBillingDTOMock.getDataDefault();

    private final ElectronicBillDTO electronicBillDTO = ElectronicBillDTO.builder()
            .orderElectronicBilling(dataElectronicBillingDTO)
            .orderThirdParty(orderThirdPartyDTO).build();
    
    private final List<GetOrderDetailDTO> getOrderDetailDTOS = new ArrayList<>(
        Collections.singletonList(
            new GetOrderDetailDTO(
                    1L,
                    "muy",
                    1L,
                    BigDecimal.ZERO,
                    null,
                    "cop",
                    0.0,
                    getOrderDetailDiscountDTOSByOrderId,
                    BigDecimal.ZERO,
                    1L,
                    electronicBillDTO,
                    1L,
                    BigDecimal.ZERO,
                    1L,
                    "",
                    "Notes",
                    LocalDate.parse("2022-06-15"),
                    1L,
                    "pos",
                    "2005",
                    "3483eghf",
                    "asdasd",
                    "User",
                    true,
                     "0000",
                     new OrderFiscalIdentifierDTODataMock().getDataDefault(),
                     getOrderDetailPaymentMethodDTOS,
                    1L,
                    false,
                     getOrderDetailFinalProductDTOS,
                    1L,
                    "Pedido",
                    1L,
                    "muy 79",
                    8900.0,
                    0.0,
                    8900.0,
                    1L,
                    "1234",
                    "asdasd",
                    userDataDTO
            )
        ));

    private final Page<OrderHistoryItemDTO> orderHistoryItemDTOS = new PageImpl<>(Arrays.asList(
            new OrderHistoryItemDTO(
                    "MUY",
                    "today",
                    1L,
                    1L,
                    "1234",
                    "Rappi",
                    1L,
                    7900.0
            ),
            new OrderHistoryItemDTO(
                    "El Original",
                    "yesterday",
                    2L,
                    2L,
                    "5678",
                    "Domicilios.com",
                    2L,
                    900.0
            )
    ));
    private final HistoryDTO<OrderHistoryItemDTO> history = new HistoryDTO<>(
            orderHistoryItemDTOS.getContent(),
            new PropertyDTO(
                    1,
                    7,
                    orderHistoryItemDTOS.getTotalPages(),
                    (int) orderHistoryItemDTOS.getTotalElements()
            )
    );

    private final List<GetOrderTotalDailySalesDTO> orderTotalDailySalesDTO = new ArrayList<>(

    );
    private final List<OrderDTO> orderDTOS = new ArrayList<>(Arrays.asList(
            new OrderDTO(
                    1L,
                    1L,
                    "Brand Name",
                    1L,
                    null,
                    "CO",
                    1L,
                    0.0,
                    1L,
                    null,
                    null,
                    1,
                    null,
                    "476383",
                    "234234",
                    1L,
                    "Origin Name",
                    Boolean.TRUE,
                    "Pickup Time",
                    1L,
                    Boolean.TRUE,
                    1L,
                    1L,
                    "Store Name",
                    0.0,
                    0.0,
                    1L,
                    BigDecimal.valueOf(0.0),
                    0.0,
                    "uid",
                    UUID.randomUUID().toString(),
                    1L,
                    1L
            )
    ));

    private final OrderDailySaleSummaryDTO orderDailySaleSummaryDTO = OrderDailySaleSummaryDTO.builder()
            .salesSummary(BigDecimal.TEN.doubleValue())
            .ordersNumber(BigDecimal.ONE.intValue())
            .build();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGetOrderDetailByUidsUseCase mockGetOrderDetailByUidsUseCase;

    @MockBean
    private IGetOrderDetailOrderUseCase mockGetOrderDetailOrderUseCase;

    @MockBean
    private IGetOrderHistoryUseCase mockGetOrderHistoryUseCase;

    @MockBean
    private ICreateOrderTransactionUseCase mockCreateOrderTransactionUseCase;

    @MockBean
    private IGetOrderDetailByIdsAndsUidsUseCase mockGetOrderDetailByIdsAndsUidsUseCase;

    @MockBean
    private IGetStateOrderUseCase mockGetStatusOrder;

    @MockBean
    private IGetStateOrderWithCodeUseCase mockGetStatus;

    @MockBean
    private IGetOrdersByTransactionIdUseCase mockGetOrdersByTransactionIdUseCase;

    @MockBean
    private IOrderFoodCoinRepository mockGetOrderfoodCOin;

    @MockBean
    private IGetDailySalesSummaryByStoreIdAndDateUseCase mockGetDailySalesSummaryByStoreIdAndDateUseCas;

    @MockBean
    private IGetOrdersTotalDailySalesByParamsUseCase mockGetOrdersTotalDailySalesByParamsUseCase;

    @MockBean
    private ICreateOrderCouponUseCase mockCraCreateOrderCouponUseCase;

    @MockBean
    private IExitsTransactionUuidAndOrderUuidsUseCase mockExitsTransactionUuidAndOrderUidsUseCase;

    private final OrderStateDTO orderStateDTO = new OrderStateDTO();

    @Test
    void test_Get_OrderDetail_By_OrdersIds() throws Exception {

        when(mockGetOrderDetailByIdsAndsUidsUseCase.invoke(any(), any(), any()))
                .thenReturn(getOrderDetailDTOS);

        final AbstractApiResponseBuilderDTO<List<GetOrderDetailDTO>> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(getOrderDetailDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1)
                        .queryParam("orderIds", "1")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Get_OrderDetailPrint_By_OrdersIds() throws Exception {

        when(mockGetOrderDetailByIdsAndsUidsUseCase.invoke(any(), any(), any()))
                .thenReturn(getOrderDetailDTOS);

        final AbstractApiResponseBuilderDTO<List<GetOrderDetailDTO>> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(getOrderDetailDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + ORDER_DETAIL_PRINT)
                        .queryParam("orderIds", "1")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Get_OrderDetail_By_OrdersUids() throws Exception {

        when(mockGetOrderDetailByIdsAndsUidsUseCase.invoke(any(), any(), any()))
                .thenReturn(getOrderDetailDTOS);

        final AbstractApiResponseBuilderDTO<List<GetOrderDetailDTO>> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(getOrderDetailDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1)
                        .queryParam("orderUids", "1234")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Get_OrderDetailPrint_By_OrdersUids() throws Exception {

        when(mockGetOrderDetailByIdsAndsUidsUseCase.invoke(any(), any(), any()))
                .thenReturn(getOrderDetailDTOS);

        final AbstractApiResponseBuilderDTO<List<GetOrderDetailDTO>> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(getOrderDetailDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + ORDER_DETAIL_PRINT)
                        .queryParam("orderUids", "1234")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Get_OrderDetail_Empty_OrdersId_And_OrderUids() throws Exception {

        when(mockGetOrderDetailByUidsUseCase.invoke(uidsList))
                .thenReturn(getOrderDetailDTOS);

        final AbstractApiResponseBuilderDTO<List<GetOrderDetailDTO>> apiResponseDTOBuilder =
                new BadRequestAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build("Require parameters orderIds, orderUids or orderUuid");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Get_OrderDetail_Print_Empty_OrdersId_And_OrderUids() throws Exception {

        when(mockGetOrderDetailByUidsUseCase.invoke(uidsList))
                .thenReturn(getOrderDetailDTOS);

        final AbstractApiResponseBuilderDTO<List<GetOrderDetailDTO>> apiResponseDTOBuilder =
                new BadRequestAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build("Require parameters orderIds, orderUids or orderUuid");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + ORDER_DETAIL_PRINT)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_GetOrderHistory_When_Current_Page_Is_Negative() throws Exception {

        final AbstractApiResponseBuilderDTO<HistoryDTO<OrderHistoryItemDTO>> apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build("Current page parameter cannot be negative or zero");
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + ORDER_HISTORY)
                        .header("timeZone", TIMEZONE_COL)
                        .queryParam("storeId", "27")
                        .queryParam("currentPage", "-1")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_GetOrderHistory_When_Per_Page_Is_Negative() throws Exception {

        final AbstractApiResponseBuilderDTO<HistoryDTO<OrderHistoryItemDTO>> apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build("Per page parameter cannot be negative or zero");
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + ORDER_HISTORY)
                        .header("timeZone", TIMEZONE_COL)
                        .queryParam("storeId", "27")
                        .queryParam("perPage", "-1")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_GetOrderHistory_Returns_Correctly() throws Exception {

        final Long storeId = 27L;
        when(mockGetOrderHistoryUseCase.invoke(
                TIMEZONE_COL,
                LocalDate.now(),
                1,
                false,
                false,
                false,
                null,
                false,
                7,
                storeId
        )).thenReturn(history);
        final AbstractApiResponseBuilderDTO<HistoryDTO<OrderHistoryItemDTO>> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(history);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + ORDER_HISTORY)
                        .header("timeZone", TIMEZONE_COL)
                        .queryParam("storeId", "27")
                        .queryParam("isPaid", "false")
                        .queryParam("createdAt", LocalDate.now().toString())
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Get_State() throws Exception {

        orderStateDTO.setName("Pendiente");
        orderStateDTO.setId(1L);
        when(mockGetStatusOrder.invoke(1l))
                .thenReturn(orderStateDTO);

        final AbstractApiResponseBuilderDTO<OrderStateDTO> apiResponseDTOBuilder =
                new BadRequestAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(orderStateDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/state/" + 1)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test_Get_State_Negative_exception() throws Exception {

        orderStateDTO.setName("Pendiente");
        orderStateDTO.setId(1L);
        when(mockGetStatusOrder.invoke(1l))
                .thenReturn(orderStateDTO);

        final AbstractApiResponseBuilderDTO<OrderStateDTO> apiResponseDTOBuilder =
                new BadRequestAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(orderStateDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/state/" + -1)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void test_Get_State_Parent() throws Exception {

        orderStateDTO.setName("Pendiente");
        orderStateDTO.setId(1L);
        when(mockGetStatus.invoke("a"))
                .thenReturn(orderStateDTO);

        final AbstractApiResponseBuilderDTO<OrderStateDTO> apiResponseDTOBuilder =
                new BadRequestAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(orderStateDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/state/code/" + "a")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test_When_Get_Orders_By_Transaction_Happy_Path() throws Exception {
        when(mockGetOrdersByTransactionIdUseCase.invoke(any()))
                .thenReturn(orderDTOS);

        final AbstractApiResponseBuilderDTO<List<OrderDTO>> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(orderDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/3/order-by-transaction-id")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }


    @Test
    void test_Get_order_daily_sales_summary_ok() throws Exception {
        when(mockGetDailySalesSummaryByStoreIdAndDateUseCas.invoke(any(), any()))
                .thenReturn(orderDailySaleSummaryDTO);

        final AbstractApiResponseBuilderDTO<OrderDailySaleSummaryDTO> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(orderDailySaleSummaryDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/stores/20/report/daily-sale-summary")
                        .param("createdAt", LocalDate.now().toString())
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()));
    }

    @Test
    void test_When_Get_Total_Daily_Sales_Ok() throws Exception {
        when(mockGetOrdersTotalDailySalesByParamsUseCase.invoke(27L, LocalDate.now()))
                .thenReturn(orderTotalDailySalesDTO);

        final AbstractApiResponseBuilderDTO<List<GetOrderTotalDailySalesDTO>> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(orderTotalDailySalesDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + "/stores/27/report/total-daily-sales?createdAt=2022-05-05")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Exists_TransactionUuid_OrderUid_When_TransactionUuid_is_Empty() throws Exception {

        ResponseExistsTransactionUuidOrderUidDTO mockResponseExistsTransactionUuidOrderUidDTO =
                ResponseExistsTransactionUuidOrderUidDTO.builder()
                        .exits(true)
                        .message("Success")
                        .build();

        when(mockExitsTransactionUuidAndOrderUidsUseCase.invoke(any(), any()))
                .thenReturn(mockResponseExistsTransactionUuidOrderUidDTO);

        final AbstractApiResponseBuilderDTO<ResponseExistsTransactionUuidOrderUidDTO> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(mockResponseExistsTransactionUuidOrderUidDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + EXIST_TRANSACTION_UUID_ORDER_UID)
                        .queryParam("orderUuids", "1234")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Exists_TransactionUuid_OrderUid_When_OrderUid_is_Empty() throws Exception {

        ResponseExistsTransactionUuidOrderUidDTO mockResponseExistsTransactionUuidOrderUidDTO =
                ResponseExistsTransactionUuidOrderUidDTO.builder()
                        .exits(true)
                        .message("Success")
                        .build();

        when(mockExitsTransactionUuidAndOrderUidsUseCase.invoke(any(), any()))
                .thenReturn(mockResponseExistsTransactionUuidOrderUidDTO);

        final AbstractApiResponseBuilderDTO<ResponseExistsTransactionUuidOrderUidDTO> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(mockResponseExistsTransactionUuidOrderUidDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + EXIST_TRANSACTION_UUID_ORDER_UID)
                        .queryParam("transactionUuid", "1234")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }

    @Test
    void test_Exists_TransactionUuid_OrderUid_When_Params_is_Empty() throws Exception {

        AbstractApiResponseBuilderDTO<ResponseExistsTransactionUuidOrderUidDTO> apiResponseDTOBuilder;

        apiResponseDTOBuilder = new BadRequestAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build("Require parameters transactionUuid or order uids");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ORDERS_V1 + EXIST_TRANSACTION_UUID_ORDER_UID)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(
                        content().json(toJson(apiResponseDTOBuilder.getApiResponseDTO())));
    }
}
