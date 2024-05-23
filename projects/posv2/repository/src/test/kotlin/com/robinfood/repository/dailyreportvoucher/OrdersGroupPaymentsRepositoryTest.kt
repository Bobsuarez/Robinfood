package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.OrderCategoryDTO
import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.dailyreportvoucher.OrderGroupPaymentEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.dailyreportvoucher.toOrderGroupPaymentDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.dailyreportvoucher.DailyReportVoucherRequestDTOMocks
import com.robinfood.repository.mocks.dailyreportvoucher.OrderGroupPaymentEntitiesMocks
import java.util.Collections
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class OrdersGroupPaymentsRepositoryTest {

    @Mock
    private lateinit var orderCreationQueriesAPI: OrderCreationQueriesAPI

    @InjectMocks
    private lateinit var ordersGroupPaymentsRepository: OrdersGroupPaymentsRepository

    private val ordersGroupPaymentEntitiesMocks = OrderGroupPaymentEntitiesMocks().orderGroupPaymentEntities
    private val ordersGroupPaymentEntitiesIdNullMocks = OrderGroupPaymentEntitiesMocks().orderGroupPaymentIdNullEntities

    private val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTOMocks()
        .dailyReportVoucherRequestDTOMocks

    private val ordersGroupPaymentDTOs = ordersGroupPaymentEntitiesMocks.mapNotNull { orderGroupPaymentEntity ->
        orderGroupPaymentEntity.toOrderGroupPaymentDTO()
    }

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val apiResponseGroupPaymentEntities = APIResponseEntity<List<OrderGroupPaymentEntity>>(
        200,
        ordersGroupPaymentEntitiesMocks,
        "CO",
        "orders groups payment by date successfully",
        "OK"
    )

    private val apiResponseGroupPaymentEntitiesNull = APIResponseEntity<List<OrderGroupPaymentEntity>>(
        200,
        null,
        "CO",
        "orders groups payment by date successfully",
        "OK"
    )

    private val apiResponseGroupPaymentErrorEntities = APIResponseEntity<List<OrderGroupPaymentEntity>>(
        500,
        ordersGroupPaymentEntitiesMocks,
        "CO",
        "orders groups payment by date successfully",
        "OK"
    )

    private val apiResponseGroupPaymentEmptyEntities = APIResponseEntity<List<OrderGroupPaymentEntity>>(
        200,
        Collections.emptyList(),
        "CO",
        "orders groups payment by date successfully",
        "OK"
    )

    private val apiResponseGroupPaymentIdNullEntities = APIResponseEntity<List<OrderGroupPaymentEntity>>(
        200,
        ordersGroupPaymentEntitiesIdNullMocks,
        "CO",
        "orders groups payment by date successfully",
        "OK"
    )

    @Test
    fun `test get orders groups payment when service returns successfully`() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupPayments(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseGroupPaymentEntities))
            val result = ordersGroupPaymentsRepository.getOrderGroupPayments(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertEquals(Result.Success(ordersGroupPaymentDTOs), result)
        }
    }

    @Test
    fun test_getOrderGroupPayments_when_repository_returns_Error() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupPayments(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(
                Response.error(
                    500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        apiResponseGroupPaymentErrorEntities.toJson()
                    )
                )
            )
            val result = ordersGroupPaymentsRepository.getOrderGroupPayments(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getOrderGroupPayments_when_repository_returns_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupPayments(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseGroupPaymentEntitiesNull))
            val result = ordersGroupPaymentsRepository.getOrderGroupPayments(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getOrderGroupPayments_when_repository_returns_Empty() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupPayments(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseGroupPaymentEmptyEntities))

            val result = ordersGroupPaymentsRepository.getOrderGroupPayments(
                dailyReportVoucherRequestDTOMocks,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<OrderCategoryDTO>()), result)
        }
    }

    @Test
    fun test_getOrderGroupPayments_when_repository_returns_Id_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupPayments(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseGroupPaymentIdNullEntities))

            val result = ordersGroupPaymentsRepository.getOrderGroupPayments(
                dailyReportVoucherRequestDTOMocks,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<OrderCategoryDTO>()), result)
        }
    }
}