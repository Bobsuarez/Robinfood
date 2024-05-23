package com.robinfood.app.controllers.orders

import com.robinfood.app.POSApplication
import com.robinfood.app.mocks.OrderDailyDTOsMocks
import com.robinfood.app.mocks.OrderDetailDTOsMocks
import com.robinfood.app.usecases.deleteorder.IDeleteOrderUseCase
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase
import com.robinfood.app.usecases.orderdaily.IGetOrdersDailyUseCase
import com.robinfood.app.usecases.orderdetail.IGetOrdersDetailUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.ORDERS
import com.robinfood.core.constants.APIConstants.ORDERS_DAILY
import com.robinfood.core.constants.APIConstants.ORDER_DETAIL
import com.robinfood.core.constants.APIConstants.ORDER_HISTORY
import com.robinfood.core.dtos.HistoryPaginatedItemDTO
import com.robinfood.core.dtos.OrderStatusDTO
import com.robinfood.core.dtos.OrderUserDTO
import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.PropertyDTO
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.OrderDailyResponseDTO
import com.robinfood.core.dtos.OrderDetailWithFlagsDTO
import com.robinfood.core.dtos.flags.FlagsDTO
import com.robinfood.core.dtos.transactionrequest.BrandDTO
import com.robinfood.core.dtos.transactionrequest.OriginDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(classes = [POSApplication::class])
@TestPropertySource(properties = [
    "jwt-token-prefix=Bearer ",
    "jwt-token-secret=secretForTesting",
    "jwt-token-aud=internal",
    "jwt-token-mod=posv2"
])
class OrdersControllerTest {

    companion object {
        private const val BEARER_AUTH = "Bearer "
        private const val TOKEN =
            "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"
        private const val TIME_ZONE = "America/Bogota"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var mockDeleteOrderUseCase: IDeleteOrderUseCase

    @MockBean
    private lateinit var mockGetOrderHistoryUseCase: IGetOrderHistoryUseCase

    @MockBean
    private lateinit var mockGetOrdersDetailUseCase: IGetOrdersDetailUseCase

    @MockBean
    private lateinit var mockGetOrdersDailyUseCase: IGetOrdersDailyUseCase

    private val orderId: Long = 1234

    private val perPage = 10
    private val page = 2
    private val lastPage = 32

    private val countryId = 1L
    private val flowId = 1L
    private val storeId = 1L
    private val platformId = 1L

    private val orderDetailDTOsMocks = OrderDetailDTOsMocks()
    private val orderDailyDTOsMocks = OrderDailyDTOsMocks()

    private val orderDetailDTO = orderDetailDTOsMocks.orderDetailDto

    private val orderDailyDTO = orderDailyDTOsMocks.orderDailyDTO

    private val orderIds = listOf<Long>(1)

    private val historyPaginatedItemDTOs: List<HistoryPaginatedItemDTO> = listOf(
            HistoryPaginatedItemDTO(
                    brand = BrandDTO(123L, "Muy"),
                    createdAt = "2022-11-03T15:33:56",
                    deliveryTypeId = 123,
                    id = 123L,
                    orderInvoiceNumber = "00002",
                    orderNumber = "456789",
                    origin = OriginDTO(123L, "Rappi"),
                    total = "99000",
                    status = OrderStatusDTO(123L, "En preparacion"),
                    user = OrderUserDTO(
                            "email@something.com", "firstname",
                            1234L, "lastname",
                            1L, "123456789"
                    )
            ),
            HistoryPaginatedItemDTO(
                    brand = BrandDTO(345L, "Muy"),
                    createdAt = "2022-11-03T15:33:56",
                    deliveryTypeId = 345,
                    id = 345L,
                    orderInvoiceNumber = "00002",
                    orderNumber = "456789",
                    origin = OriginDTO(345L, "Rappi"),
                    total = "99000",
                    status = OrderStatusDTO(345L, "En preparacion"),
                    user = OrderUserDTO(
                            "email@something.com", "firstname",
                            67890L, "lastname",
                            4L, "123456789"
                    )
            )
    )

    private val historyDTO = HistoryDTO(
            historyPaginatedItemDTOs,
            PropertyDTO(
                    perPage,
                    page,
                    lastPage,
                    historyPaginatedItemDTOs.size
            )
    )

    @Test
    @Throws(Exception::class)
    fun `test delete order should return ok because order was found`() {
        val url = "$API_V1$ORDERS/$orderId"
        `when`(mockDeleteOrderUseCase.invoke(orderId)).thenReturn(true)
        mockMvc.perform(MockMvcRequestBuilders
            .delete(url)
            .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun `test delete order should return not found because order was not found`() {
        val url = "$API_V1$ORDERS/$orderId"
        `when`(mockDeleteOrderUseCase.invoke(orderId)).thenReturn(false)
        mockMvc.perform(MockMvcRequestBuilders
            .delete(url)
            .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
    }

    private fun <T> anyObject(): T {
        return Mockito.anyObject<T>()
    }

    @Test
    @Throws(Exception::class)
    fun test_GetOrderHistory_Should_Return_Ok() {
        runBlocking {

            `when`(mockGetOrderHistoryUseCase.invoke(
                anyString(),
                    anyObject()
            )).thenReturn(Result.Success(historyDTO))

            val url = "$API_V1$ORDERS/27$ORDER_HISTORY"
            val result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .header("TimeZone", "America/Bogota")
                .param("localDateStart","2022-11-10")
                .param("localDateEnd","2022-12-10")
                .param("isDelivery","true")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()

            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk)
                .andExpect(content().json(ApiResponseDTO(historyDTO).toJson()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetOrderHistory_Should_Return_Failure() {
        runBlocking {
            `when`(mockGetOrderHistoryUseCase.invoke(
                    anyString(),
                    anyObject()
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            val url = "$API_V1$ORDERS/27$ORDER_HISTORY"
            val result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .header("TimeZone", "America/Bogota")
                .param("localDateStart","2022-11-10")
                .param("localDateEnd","2022-12-10")
                .param("isDelivery","true")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()

            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isBadRequest)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetOrderHistory_Should_IsDelivery_Return_False() {

        runBlocking {
            `when`(mockGetOrderHistoryUseCase.invoke(
                    anyString(),
                    anyObject()
            )).thenReturn(Result.Success(historyDTO))

            val url = "$API_V1$ORDERS/27$ORDER_HISTORY"
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get(url)
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .header("TimeZone", "America/Bogota")
                    .param("localDateStart","2022-11-10")
                    .param("localDateEnd","2022-12-10")
                    .param("isDelivery","false")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()

            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isOk)
                    .andExpect(content().json(ApiResponseDTO(historyDTO).toJson()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetOrdersDetail_Should_Return_Ok() {
        runBlocking {
            `when`(mockGetOrdersDetailUseCase.invoke(
                BEARER_AUTH + TOKEN,
                orderIds,
                countryId,
                flowId,
                storeId,
                platformId
            )).thenReturn(Result.Success(OrderDetailWithFlagsDTO(listOf(orderDetailDTO), FlagsDTO())))
            val result = mockMvc.perform(MockMvcRequestBuilders
                .get("$API_V1$ORDERS$ORDER_DETAIL")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .queryParam("orderIds", "1")
                .queryParam("country_id", "1")
                .queryParam("flow_id", "1")
                .queryParam("store_id", "1")
                .queryParam("platform_id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk)
                .andExpect(content().json(ApiResponseDTO(listOf(orderDetailDTO)).toJson()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetOrdersDetail_Should_Return_OrderIds_Empty_Ok() {
        val ordersIds: List<Long> = listOf()
        runBlocking {
            `when`(mockGetOrdersDetailUseCase.invoke(
                    BEARER_AUTH + TOKEN,
                    ordersIds,
                    countryId,
                    flowId,
                    storeId,
                    platformId
            )).thenReturn(Result.Success(OrderDetailWithFlagsDTO(listOf(orderDetailDTO), FlagsDTO())))
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("$API_V1$ORDERS$ORDER_DETAIL")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("orderIds", "")
                    .queryParam("country_id", "1")
                    .queryParam("flow_id", "1")
                    .queryParam("store_id", "1")
                    .queryParam("platform_id", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().is4xxClientError)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetOrdersDetail_Should_Return_Failure() {
        runBlocking {
            `when`(mockGetOrdersDetailUseCase.invoke(
                BEARER_AUTH + TOKEN,
                orderIds,
                countryId,
                flowId,
                storeId,
                platformId
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            val result = mockMvc.perform(MockMvcRequestBuilders
                .get("$API_V1$ORDERS$ORDER_DETAIL")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .queryParam("orderIds", "1")
                .queryParam("country_id", "1")
                .queryParam("flow_id", "1")
                .queryParam("store_id", "1")
                .queryParam("platform_id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()

            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isBadRequest)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetOrdersDaily_Should_Return_Ok() {

        runBlocking {
            `when`(mockGetOrdersDailyUseCase.invoke(
                BEARER_AUTH + TOKEN,
                TIME_ZONE,
                storeId
            )).thenReturn(Result.Success(OrderDailyResponseDTO(listOf(orderDailyDTO))))
            val result = mockMvc.perform(MockMvcRequestBuilders
                .get("$API_V1$ORDERS$ORDERS_DAILY")
                .header(HttpHeaders.AUTHORIZATION,   BEARER_AUTH + TOKEN)
                .header("timeZone", TIME_ZONE)
                .queryParam("storeId", storeId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk)
                .andExpect(content().json(ApiResponseDTO(OrderDailyResponseDTO(listOf(orderDailyDTO))).toJson()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetOrdersDaily_Should_Return_Failure() {
        runBlocking {
            `when`(mockGetOrdersDailyUseCase.invoke(
                BEARER_AUTH + TOKEN,
                TIME_ZONE,
                27L
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            val result = mockMvc.perform(MockMvcRequestBuilders
                .get("$API_V1$ORDERS$ORDERS_DAILY")
                .header(HttpHeaders.AUTHORIZATION,   BEARER_AUTH + TOKEN)
                .header("timeZone", TIME_ZONE)
                .queryParam("storeId", "27")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()

            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isBadRequest)
        }
    }
}