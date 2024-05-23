package com.robinfood.repository.orderdetail

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.OrderDetailEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.toOrderDetailDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.repository.mocks.OrderDetailEntitiesMocks
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response
import java.util.*

@ExtendWith(MockitoExtension::class)
class OrderDetailRepositoryTest {

    @Mock
    private lateinit var orderCreationQueriesAPI: OrderCreationQueriesAPI

    @InjectMocks
    private lateinit var orderDetailRepository: OrderDetailRepository

    private val countryId: Long = 1L
    private val flowId: Long = 1L
    private val orderIds: List<Long> = listOf(1)
    private val token = "token"

    private val orderDetailEntitiesMocks = OrderDetailEntitiesMocks()

    private val orderDetailEntities = listOf(orderDetailEntitiesMocks.orderDetailEntity)

    private val orderDetailIdNullEntities = listOf(orderDetailEntitiesMocks.orderDetailIdNullEntity)

    private val orderDetailDTOs = orderDetailEntities.mapNotNull { orderDetailEntity -> orderDetailEntity.toOrderDetailDTO() }

    private val apiResponseErrorEntity = APIResponseEntity(
            500,
            orderDetailEntities,
            "CO",
            "Error",
            "Order history could not be returned"
    )

    private val apiResponseEntity = APIResponseEntity(
            200,
            orderDetailEntities,
            "CO",
            "",
            "Order history returned"
    )

    private val apiResponseIdNullEntity = APIResponseEntity(
        200,
        orderDetailIdNullEntities,
        "CO",
        "",
        "Order history returned"
    )

    private val apiResponseEntityWithOrderDetailNull = APIResponseEntity<List<OrderDetailEntity>>(
            200,
            null,
            "CO",
            "",
            "Order history returned"
    )

    private val apiResponseEntityWithOrderDetailEmpty = APIResponseEntity<List<OrderDetailEntity>>(
            200,
            Collections.emptyList(),
            "CO",
            "",
            "Order history returned"
    )

    @Test
    fun `test get orders detail when service returns successfully`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrdersDetail(
                    token,
                    countryId,
                    flowId,
                    orderIds
            )).thenReturn(Response.success(apiResponseEntity))
            val result = orderDetailRepository.getOrdersDetail(
                    token,
                    countryId,
                    flowId,
                    orderIds
            )
            assertEquals(Result.Success(orderDetailDTOs), result)
        }
    }

    @Test
    fun `test get orders detail when service id null returns successfully`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrdersDetail(
                token,
                countryId,
                flowId,
                orderIds
            )).thenReturn(Response.success(apiResponseIdNullEntity))

            val result = orderDetailRepository.getOrdersDetail(
                token,
                countryId,
                flowId,
                orderIds
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get orders detail when service returns successfully but the orders are null`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrdersDetail(
                    token,
                    countryId,
                    flowId,
                    orderIds
            )).thenReturn(Response.success(apiResponseEntityWithOrderDetailNull))
            val result = orderDetailRepository.getOrdersDetail(
                    token,
                    countryId,
                    flowId,
                    orderIds
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get orders detail when service returns successfully but the orders are empty`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrdersDetail(
                    token,
                    countryId,
                    flowId,
                    orderIds
            )).thenReturn(Response.success(apiResponseEntityWithOrderDetailEmpty))
            val result = orderDetailRepository.getOrdersDetail(
                    token,
                    countryId,
                    flowId,
                    orderIds
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get order history when service returns with error`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrdersDetail(
                    token,
                    countryId,
                    flowId,
                    orderIds
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiResponseErrorEntity.toJson()
            )))
            val result = orderDetailRepository.getOrdersDetail(
                    token,
                    countryId,
                    flowId,
                    orderIds
            )
            assertTrue(result is Result.Error)
        }
    }
}