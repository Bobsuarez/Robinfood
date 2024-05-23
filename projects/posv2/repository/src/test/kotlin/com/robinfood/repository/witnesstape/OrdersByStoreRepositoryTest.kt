package com.robinfood.repository.witnesstape

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.witnesstape.StoreOrdersEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.witnesstape.toStoreOrdersDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.witnesstape.StoreOrderRequestDTOMocks
import com.robinfood.repository.mocks.witnesstape.StoreOrdersEntityMocks
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
class OrdersByStoreRepositoryTest {

    @Mock
    private lateinit var orderCreationQueriesAPI: OrderCreationQueriesAPI

    @InjectMocks
    private lateinit var ordersByStoreRepository: OrdersByStoreRepository

    private val storeOrdersEntitiesMocks = StoreOrdersEntityMocks().storeOrdersEntities
    private val storeOrdersEntitiesIdNullMocks = StoreOrdersEntityMocks().storeOrdersEntitiesIdNull

    private val storeOrderRequestDTOMocks = StoreOrderRequestDTOMocks().storeOrderRequestDTOMocks

    private val storeOrdersDTOs = storeOrdersEntitiesMocks.mapNotNull { storeOrdersEntity ->
        storeOrdersEntity.toStoreOrdersDTO()
    }

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val apiResponseOrdersByStoreEntities = APIResponseEntity(
        200,
        storeOrdersEntitiesMocks,
        "CO",
        "orders by store successfully",
        "OK"
    )

    private val apiResponseOrdersByStoreEntitiesNull = APIResponseEntity<List<StoreOrdersEntity>>(
        200,
        null,
        "CO",
        "orders by store successfully",
        "OK"
    )

    private val apiResponseOrdersByStoreError = APIResponseEntity(
        500,
        storeOrdersEntitiesMocks,
        "CO",
        "orders groups payment by date successfully",
        "OK"
    )

    private val apiResponseOrdersByStoreEmptyEntities = APIResponseEntity<List<StoreOrdersEntity>>(
        200,
        Collections.emptyList(),
        "CO",
        "orders groups payment date successfully",
        "OK"
    )

    private val apiResponseOrdersByStoreIdNullEntities = APIResponseEntity(
        200,
        storeOrdersEntitiesIdNullMocks,
        "CO",
        "orders groups payment date successfully id Null",
        "OK"
    )

    @Test
    fun `test get Orders By Store Repository when service returns successfully`() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrdersByStore(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseOrdersByStoreEntities))

            val result = ordersByStoreRepository.getOrdersByStore(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertEquals(Result.Success(storeOrdersDTOs), result)
        }
    }

    @Test
    fun test_getOrdersByStore_when_repository_returns_Error() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrdersByStore(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(
                Response.error(
                    500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        apiResponseOrdersByStoreError.toJson()
                    )
                )
            )

            val result = ordersByStoreRepository.getOrdersByStore(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getOrdersByStore_when_repository_returns_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrdersByStore(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseOrdersByStoreEntitiesNull))

            val result = ordersByStoreRepository.getOrdersByStore(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getOrdersByStore_when_repository_returns_Empty() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrdersByStore(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseOrdersByStoreEmptyEntities))

            val result = ordersByStoreRepository.getOrdersByStore(
                storeOrderRequestDTOMocks,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<StoreOrdersEntity>()), result)
        }
    }

    @Test
    fun test_getOrdersByStore_when_repository_returns_Id_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getOrdersByStore(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseOrdersByStoreIdNullEntities))

            val result = ordersByStoreRepository.getOrdersByStore(
                storeOrderRequestDTOMocks,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<StoreOrdersEntity>()), result)
        }
    }
}