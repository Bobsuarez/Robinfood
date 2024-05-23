package com.robinfood.app.controllers.orders;

import com.robinfood.app.OrderCreationQueriesApplication;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderDetailDiscountDTO;
import com.robinfood.core.dtos.OrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailProductDiscountDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.dtos.OrderDetailProductTaxDTO;
import com.robinfood.core.dtos.OrderDetailUserDTO;
import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.mocks.dto.OrderDetailServiceDTOMock;
import com.robinfood.core.mocks.dto.OrderFiscalIdentifierDTOMock;
import com.robinfood.app.usecases.getdailysalessummary.IGetDailySalesSummaryByStoreIdAndDateUseCase;
import com.robinfood.app.usecases.getorderdetail.IGetOrderDetailUseCase;
import com.robinfood.app.usecases.getorderdetailprint.IGetOrderDetailPrintUseCase;
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase;
import com.robinfood.app.usecases.getordertotaldailysales.IGetOrderTotalDailySalesUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.ORDERS_V1;
import static com.robinfood.core.constants.APIConstants.ORDER_DETAIL;
import static com.robinfood.core.constants.APIConstants.ORDER_DETAIL_PRINT;
import static com.robinfood.core.constants.APIConstants.ORDER_GET_DAILY_SALE_SUMMARY;
import static com.robinfood.core.constants.APIConstants.ORDER_HISTORY;
import static com.robinfood.core.constants.APIConstants.ORDER_REPORT_TOTAL_DAILY_SALES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = OrderCreationQueriesApplication.class)
@TestPropertySource(properties = {
        "jwt-token-prefix=Bearer ",
        "jwt.token.secret=gyXNfCp4Qx4Gn9OV7qK7uwRUX4f4bgrEEPvjdeLQFciHwtCKrc5rCm1kuNECIFP6",
        "jwt-token-aud=internal",
        "jwt-token-mod=order_creation_queries"
})
public class OrdersControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJwcmludC1sb2NhbC1zZXJ2aWNlIiwiYXVkIjoiaW50ZXJuYWwiLCJtb2QiOlsibWVudV9iYyIsIm1lbnVfYmFzZSIsIk1FTlVfRElTQ09VTlRTX1NFUlZJQ0VTIiwicG9zdjIiLCJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIiwib3JkZXJfY3JlYXRpb24iLCJvcmRlcl9iYyJdLCJwZXIiOlsibWVudV9iYXNlX3NlcnZpY2VzIiwibWVudV9iY19zZXJ2aWNlcyIsImFkX29yZGVyX2xpc3Rfb3JkZXJzIiwiYWRfb3JkZXJfbGlzdF9kZXRhaWwiLCJhZF9vcmRlcl9saXN0X2hpc3RvcnkiLCJhZF9vcmRlcl9saXN0X3ByaW50X29yZGVycyIsImFkX29yZGVyX2NyZWF0ZV90cmFuc2FjdGlvbiIsIm9yX29yZGVyX2RhaWx5X3NhbGVzX3N1bW1hcnkiLCJvcl9vcmRlcl9kYWlseV9zYWxlc19wYXltZW50X21ldGhvZHMiLCJvcl9vcmRlcl9kYWlseV9zYWxlc19wYXltZW50X21ldGhvZF9ncmFwaGljcyJdLCJqdGkiOiI2ZGU4Y2ZkNC1hYzIxLTQ5NTgtOTBmNC02NjZlM2M3Y2VmZmMiLCJleHAiOjE2NDY0NjgwMDMsImlhdCI6MTY0NjQyNDgwMywibmJmIjowLCJ1c2VyIjp7ImNvbXBhbmllcyI6W3sibmFtZSI6Ik1VWSIsInRpbWV6b25lIjoiQW1lcmljYS9Cb2dvdGEiLCJpZCI6MX0seyJuYW1lIjoiTVVZIE1FWElDTyIsInRpbWV6b25lIjoiQW1lcmljYS9Cb2dvdGEiLCJpZCI6Mn0seyJuYW1lIjoiTVVZIEJSQVNJTCIsInRpbWV6b25lIjoiQW1lcmljYS9TYW9fUGF1bG8iLCJpZCI6NX1dLCJ1c2VyX2lkIjo0LCJwaG9uZSI6IjMwNTgxNDI2NTMiLCJhbGxvd19hZG1pbiI6dHJ1ZSwiZGVmYXVsdF9jb21wYW55X2lkIjo1LCJsZWdhY3lfaWQiOjI2LCJsYXN0X25hbWUiOiJTaWx2YSIsImZpcnN0X25hbWUiOiJTZXJnaW8iLCJlbWFpbCI6InNzaWx2YUBtdXkuY29tLmNvIiwiY291bnRyeV9pZCI6MX0sInN1YiI6NCwiY29tcGFueV9pZCI6NX0.53cVBXL8PuMWcFLrCHJ6fyXCR64RlvxbSO39rxysXdVdFG-EA3hWxcGg2W897a0t6unFv8VjDmXAV0ExCwtH4A";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGetOrderHistoryUseCase mockGetOrderHistoryUseCase;

    @MockBean
    private IGetOrderDetailUseCase mockGetOrderDetailUseCase;

    @MockBean
    private IGetOrderDetailPrintUseCase mockGetOrderDetailPrintUseCase;

    @MockBean
    private IGetDailySalesSummaryByStoreIdAndDateUseCase mockGetDailySalesSummaryByStoreIdAndDateUseCase;

    @MockBean
    private IGetOrderTotalDailySalesUseCase mockGetOrderTotalDailySalesUseCase;

    private final Integer currentPage = 2;
    private final Integer perPage = 10;

    /**
     * Order detail mocks
     */
    private final List<Long> orderIds = Collections.singletonList(1L);
    private final List<String> orderUids = Collections.singletonList("uids");

    private final List<OrderDetailPaymentMethodDTO> orderDetailPaymentMethodDTOS = Collections.singletonList(
        new OrderDetailPaymentMethodDTO(
            0.0,
            1L,
            4L,
            8900.0,
            0.0,
            8900.0
        )
    );

    private final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOS = Collections.singletonList(
        new OrderDetailProductGroupPortionDTO(
            false,
            null,
            BigDecimal.ZERO,
            1L,
            "frijol",
            1L,
            0.0,
            1,
            1,
            "sku",
            1L,
            1.0
        )
    );

    private final List<OrderDetailProductGroupDTO> orderDetailProductGroupDTOS = Collections.singletonList(
        new OrderDetailProductGroupDTO(
            1L,
            "ingredientes",
            orderDetailProductGroupPortionDTOS,
            new ArrayList<>(),
            "sku"
        )
    );

    private final List<OrderDetailProductTaxDTO> orderDetailProductTaxDTOS = Collections.singletonList(
        new OrderDetailProductTaxDTO(
            1L,
            1L,
            0.0,
            1L,
            "IMPOCONSUMO",
            0.0
        )
    );

    private final List<OrderDetailProductDiscountDTO> orderDetailProductDiscountDTOS = Collections.singletonList(
        new OrderDetailProductDiscountDTO(
            1L,
            1L,
            0.0
        )
    );

    private final List<OrderDetailProductDTO> orderDetailProductDTOS = Collections.singletonList(
            new OrderDetailProductDTO(
                    1L,
                    "Muy paisa",
                    1L,
                    8900.0,
                    1L,
                    "muy",
                    1L,
                    "Category",
                    BigDecimal.valueOf(1L),
                    BigDecimal.valueOf(0),
                    1L,
                    BigDecimal.valueOf(0),
                    orderDetailProductDiscountDTOS,
                    1L,
                    orderDetailProductGroupDTOS,
                    1L,
                    "http://localhost:8080/image.png",
                    1L,
                    1L,
                    "paisa",
                    1,
                    1L,
                    "muy",
                    "sku",
                    orderDetailProductTaxDTOS,
                    BigDecimal.ZERO,
                    0.0
            )
    );

    private final OrderDetailUserDTO orderDetailUserDTO = OrderDetailUserDTO.builder()
            .email("bruno@email")
            .firstName("bruno")
            .id(1L)
            .lastName("jason")
            .mobile("300")
            .build();

    private final List<OrderDetailDiscountDTO> orderDetailDiscountDTOS = Collections.singletonList(
        new OrderDetailDiscountDTO(
            1L,
            1L,
            0.0
        )
    );

    private static final OrderThirdPartyDTO orderThirdPartyDTO = OrderThirdPartyDTO.builder()
            .documentNumber("22675323")
            .documentType(1L)
            .email("pedro@gmail.com")
            .fullName("Pedro Jose")
            .phone("3113673398")
            .build();

    private static final DataElectronicBillingDTO dataElectronicBillingDTO = DataElectronicBillingDTO.builder()
            .broadcastDateTime("2024-04-18T11:27:08.5472815-05:00")
            .cufe("cufe test")
            .documentType("document type test")
            .nitTransmitter("nit test")
            .number("number test")
            .prefix("prefix test")
            .qr("qr test")
            .build();

    private static final ElectronicBillDTO electronicBillDTO = ElectronicBillDTO.builder()
            .orderThirdParty(orderThirdPartyDTO)
            .orderElectronicBilling(dataElectronicBillingDTO)
            .build();

    private final List<OrderDetailDTO> orderDetailDTOS = Collections.singletonList(
            new OrderDetailDTO(
                    new OrderFiscalIdentifierDTOMock().getDataDefault(),
                    1L,
                    "muy",
                    BigDecimal.ZERO,
                    "cop",
                    List.of(),
                    1L,
                    0.0,
                    orderDetailDiscountDTOS,
                    1L,
                    BigDecimal.ZERO,
                    electronicBillDTO,
                    1L,
                    BigDecimal.ZERO,
                    "",
                    "Notes",
                    1L,
                    "pos",
                    "2005",
                    "3483eghf",
                    null,
                    "test 1",
                    true,
                    "0000",
                    "2022-01-01",
                    "10:00:00",
                    orderDetailPaymentMethodDTOS,
                    1L,
                    "FBI",
                    false,
                    orderDetailProductDTOS,
                    OrderDetailServiceDTOMock.getDefaultList(),
                    1L,
                    "Pedido",
                    1L,
                    "muy 79",
                    8900.0,
                    0.0,
                    8900.0,
                    1L,
                    "1234",
                    null,
                    orderDetailUserDTO,
                    Collections.singletonList(orderDetailUserDTO)
            )
    );

    private final OrderDailySaleSummaryDTO orderDailySaleSummaryDTO = OrderDailySaleSummaryDTO.builder()
        .ordersNumber(12)
        .salesSummary(8900.0)
        .build();

    @Test
    public void test_GetOrderDetail_Should_Return_Ok() throws Exception {
        when(mockGetOrderDetailUseCase.invoke(
                1L,
                1L,
                orderIds,
                orderUids,
                List.of()
        )).thenReturn(new Result.Success<>(orderDetailDTOS));
        mockMvc.perform(MockMvcRequestBuilders
                .get(ORDERS_V1 + ORDER_DETAIL)
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .queryParam("countryId", "1")
                .queryParam("flowId", "1")
                .queryParam("orderIds", "1")
                .queryParam("orderUids", "uids")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>(orderDetailDTOS))));
    }

    @Test
    void test_GetOrderDetailPrint_Should_Return_Ok() throws Exception {
        when(mockGetOrderDetailPrintUseCase.invoke(
                orderIds,
                orderUids,
                List.of()
        )).thenReturn(new Result.Success<>(orderDetailDTOS));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(ORDERS_V1 + ORDER_DETAIL_PRINT)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .queryParam("orderIds", "1")
                        .queryParam("orderUids", "uids")
                        .queryParam("orderUuid", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>(orderDetailDTOS))));
    }

    @Test
    public void test_GetOrderDetail_Should_Return_Error() throws Exception {
        when(mockGetOrderDetailUseCase.invoke(
                1L,
                1L,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));
        mockMvc.perform(MockMvcRequestBuilders
                .get(ORDERS_V1 + ORDER_DETAIL)
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .queryParam("countryId", "1")
                .queryParam("flowId", "1")
                .queryParam("orderIds", "1")
                .queryParam("orderUids", "uids")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>("Some error"))));
    }

    @Test
    public void test_GetDailySalesSummary_Should_Return_OK() throws Exception {
        when(mockGetDailySalesSummaryByStoreIdAndDateUseCase.invoke(anyLong(), any()))
            .thenReturn(new Result.Success<>(orderDailySaleSummaryDTO));

        mockMvc.perform(MockMvcRequestBuilders
                .get(ORDERS_V1 + ORDER_GET_DAILY_SALE_SUMMARY.replace("{storeId}", "1"))
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .queryParam("createdAt", "2022-03-17")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>(orderDailySaleSummaryDTO))));
    }

    @Test
    public void test_GetDailySalesSummary_Should_Return_error() throws Exception {
        when(mockGetDailySalesSummaryByStoreIdAndDateUseCase.invoke(anyLong(), any()))
            .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders
                .get(ORDERS_V1 + ORDER_GET_DAILY_SALE_SUMMARY.replace("{storeId}", "1"))
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .queryParam("createdAt", "2022-03-17")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>("Some error"))));
    }

    @Test
    public void test_GetOrderTotalDailySales_OK() throws Exception {

        OrderTotalDailySalesDTO orderTotalDailySales =
            new OrderTotalDailySalesDTO("10.0",   1, "Prueba", 10);
        List<OrderTotalDailySalesDTO> orderTotalDailySalesList = new ArrayList<>();
        orderTotalDailySalesList.add(orderTotalDailySales);

        when(mockGetOrderTotalDailySalesUseCase.invoke(1, LocalDate.parse("2022-05-05")))
            .thenReturn(new Result.Success<>(orderTotalDailySalesList));

        mockMvc.perform(MockMvcRequestBuilders
                .get(ORDERS_V1 + ORDER_REPORT_TOTAL_DAILY_SALES.replace("{storeId}", "1"))
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .queryParam("createdAt", "2022-05-05")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>(orderTotalDailySalesList))));

    }

    @Test
    public void test_GetOrderTotalDailySales_Error() throws Exception {

        OrderTotalDailySalesDTO orderTotalDailySales =
            new OrderTotalDailySalesDTO("10.0", 1, "Prueba", 10);

        List<OrderTotalDailySalesDTO> orderTotalDailySalesList = new ArrayList<>();

        orderTotalDailySalesList.add(orderTotalDailySales);

        when(mockGetOrderTotalDailySalesUseCase.invoke(1, LocalDate.parse("2022-05-05")))
            .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders
                .get(ORDERS_V1 + ORDER_REPORT_TOTAL_DAILY_SALES.replace("{storeId}", "1"))
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .queryParam("createdAt", "2022-05-05")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(ObjectExtensions.toJson(new ApiResponseDTO<>("Some error"))));

    }


}
