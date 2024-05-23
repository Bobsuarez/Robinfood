package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.OrderCategoryDTO
import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.dailyreportvoucher.OrderCategoryEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.dailyreportvoucher.toOrderCategoryDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.dailyreportvoucher.DailyReportVoucherRequestDTOMocks
import com.robinfood.repository.mocks.dailyreportvoucher.OrderCategoryEntitiesMocks
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
class OrderGroupCategoriesRepositoryTest {

    @Mock
    private lateinit var orderCreationQueriesAPI: OrderCreationQueriesAPI

    @InjectMocks
    private lateinit var orderGroupCategoriesRepository: OrderGroupCategoriesRepository

    private val orderCategoryEntitiesMocks = OrderCategoryEntitiesMocks().orderCategoryEntityMocks
    private val orderCategoryEntitiesIdNullMocks = OrderCategoryEntitiesMocks().orderCategoryEntityIdNullMocks

    private val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTOMocks()
        .dailyReportVoucherRequestDTOMocks

    private val orderGroupCategoriesDTOs = orderCategoryEntitiesMocks.mapNotNull { orderGroupCategoryEntity ->
        orderGroupCategoryEntity.toOrderCategoryDTO()
    }

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val apiResponseOrderCategoryEntities = APIResponseEntity<List<OrderCategoryEntity>>(
        200,
        orderCategoryEntitiesMocks,
        "CO",
        "orders groups categories by date successfully",
        "OK"
    )

    private val apiResponseOrderCategoryNullEntities = APIResponseEntity<List<OrderCategoryEntity>>(
        200,
        null,
        "CO",
        "orders groups categories by date successfully",
        "OK"
    )

    private val apiResponseOrderCategoryErrorEntities = APIResponseEntity<List<OrderCategoryEntity>>(
        500,
        orderCategoryEntitiesMocks,
        "CO",
        "orders groups categories by date successfully",
        "OK"
    )

    private val apiResponseOrderCategoryEmptyEntities = APIResponseEntity<List<OrderCategoryEntity>>(
        200,
        Collections.emptyList(),
        "CO",
        "orders groups categories by date successfully",
        "OK"
    )

    private val apiResponseOrderCategoryIdNullEntities = APIResponseEntity<List<OrderCategoryEntity>>(
        200,
        orderCategoryEntitiesIdNullMocks,
        "CO",
        "orders groups categories by date successfully",
        "OK"
    )

    @Test
    fun `test get orders groups categories when service returns successfully`() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupCategories(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseOrderCategoryEntities))
            val result = orderGroupCategoriesRepository.getOrderGroupCategories(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertEquals(Result.Success(orderGroupCategoriesDTOs), result)
        }
    }

    @Test
    fun test_getOrderGroupCategories_when_repository_returns_Error() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupCategories(
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
                        apiResponseOrderCategoryErrorEntities.toJson()
                    )
                )
            )
            val result = orderGroupCategoriesRepository.getOrderGroupCategories(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getOrderGroupCategories_when_repository_returns_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupCategories(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseOrderCategoryNullEntities))
            val result = orderGroupCategoriesRepository.getOrderGroupCategories(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getOrderGroupCategories_when_repository_returns_Empty() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupCategories(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseOrderCategoryEmptyEntities))

            val result = orderGroupCategoriesRepository.getOrderGroupCategories(
                dailyReportVoucherRequestDTOMocks,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<OrderCategoryDTO>()), result)
        }
    }

    @Test
    fun test_getOrderGroupCategories_when_repository_returns_Id_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrderGroupCategories(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseOrderCategoryIdNullEntities))

            val result = orderGroupCategoriesRepository.getOrderGroupCategories(
                dailyReportVoucherRequestDTOMocks,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<OrderCategoryDTO>()), result)
        }
    }
}