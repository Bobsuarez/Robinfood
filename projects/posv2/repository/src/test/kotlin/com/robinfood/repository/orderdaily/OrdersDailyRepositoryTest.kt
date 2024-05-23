package com.robinfood.repository.orderdaily

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.orderdaily.OrderDailyEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.toOrderDailyDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.repository.mocks.OrderDailyEntitiesMocks
import kotlinx.coroutines.runBlocking
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
import java.util.Collections

@ExtendWith(MockitoExtension::class)
class OrdersDailyRepositoryTest {

    @Mock
    private lateinit var orderCreationQueriesAPI: OrderCreationQueriesAPI

    @InjectMocks
    private lateinit var ordersDailyRepository: OrdersDailyRepository

    private val orderDailyEntitiesMocks = OrderDailyEntitiesMocks()
    private val orderDailyEntities = listOf(orderDailyEntitiesMocks.orderDailyEntity)
    private val orderDailyDTOs = orderDailyEntities.mapNotNull { orderDailyEntity -> orderDailyEntity.toOrderDailyDTO() }

    private val token = "token"
    private val timeZone = "America/Bogota"
    private val storeId: Long = 1L





    private val apiResponseEntity = APIResponseEntity(
        200,
        orderDailyEntities,
        "CO",
        "",
        "Order daily returned"
    )

    private val apiResponseErrorEntity = APIResponseEntity(
        500,
        orderDailyEntities,
        "CO",
        "Error",
        "Order daily could not be returned"
    )

    private val apiResponseEntityWithOrderDailyINull = APIResponseEntity<List<OrderDailyEntity>>(
        200,
        null,
        "CO",
        "",
        "Order daily returned"
    )

    private val apiResponseEntityWithOrderDailyEmpty = APIResponseEntity<List<OrderDailyEntity>>(
        200,
        Collections.emptyList(),
        "CO",
        "",
        "Order daily returned"
    )

    @Test
    fun `test get orders daily when service returns successfully`() {
        runBlocking {
            Mockito.`when`(orderCreationQueriesAPI.getOrdersDaily(
                token,
                timeZone,
                storeId
            )).thenReturn(Response.success(apiResponseEntity))
            val result = ordersDailyRepository.getAllOrdersDaily(
                token,
                timeZone,
                storeId
            )
            Assertions.assertEquals(Result.Success(orderDailyDTOs), result)
        }
    }

    @Test
    fun `test get order daily when service returns with error`() {
        runBlocking {
            Mockito.`when`(
                orderCreationQueriesAPI.getOrdersDaily(
                    token,
                    timeZone,
                    storeId
                )
            ).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                apiResponseErrorEntity.toJson()
            )))
            val result = ordersDailyRepository.getAllOrdersDaily(
                token,
                timeZone,
                storeId
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get orders daily when service returns successfully but the orders are null`() {
        runBlocking {
            Mockito.`when`(
                orderCreationQueriesAPI.getOrdersDaily(
                    token,
                    timeZone,
                    storeId
                )
            ).thenReturn(Response.success(apiResponseEntityWithOrderDailyINull))
            val result = ordersDailyRepository.getAllOrdersDaily(
                token,
                timeZone,
                storeId
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get orders detail when service returns successfully but the orders are empty`() {
        runBlocking {
            Mockito.`when`(
                orderCreationQueriesAPI.getOrdersDaily(
                    token,
                    timeZone,
                    storeId
                )
            ).thenReturn(Response.success(apiResponseEntityWithOrderDailyEmpty))
            val result = ordersDailyRepository.getAllOrdersDaily(
                token,
                timeZone,
                storeId
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }
}