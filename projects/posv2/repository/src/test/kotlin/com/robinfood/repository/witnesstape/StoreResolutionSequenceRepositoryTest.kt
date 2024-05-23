package com.robinfood.repository.witnesstape

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.witnesstape.StoreOrdersEntity
import com.robinfood.core.entities.posresolutionsequence.PosResolutionSequenceEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.witnesstape.toStoreResolutionEntityToPosResolutionDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.witnesstape.StoreOrderRequestDTOMocks
import com.robinfood.repository.mocks.witnesstape.StoreResolutionSequenceEntityMocks
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
class StoreResolutionSequenceRepositoryTest {

    @Mock
    private lateinit var orderCreationQueriesAPI: OrderCreationQueriesAPI

    @InjectMocks
    private lateinit var storeResolutionSequenceRepository: StoreResolutionSequenceRepository

    private val storeResolutionSequenceEntities = StoreResolutionSequenceEntityMocks().storeResolutionSequenceEntity
    private val storeResolutionSequenceIdNullEntity = StoreResolutionSequenceEntityMocks()
        .storeResolutionSequenceIdNullEntity

    private val storeOrderRequestDTOMocks = StoreOrderRequestDTOMocks().storeOrderRequestDTOMocks

    private val storeResolutionSequenceDTOs =
        storeResolutionSequenceEntities.mapNotNull { storeResolutionSequenceEntity ->
            storeResolutionSequenceEntity.toStoreResolutionEntityToPosResolutionDTO()
        }

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val apiResponseStoreResolutionSequenceEntities = APIResponseEntity(
        200,
        storeResolutionSequenceEntities,
        "CO",
        "orders by store successfully",
        "OK"
    )

    private val apiResponseStoreResolutionSequenceEntitiesNull = APIResponseEntity<List<PosResolutionSequenceEntity>>(
        200,
        null,
        "CO",
        "Store Resolutions successfully",
        "OK"
    )

    private val apiResponseStoreResolutionSequenceEntitiesError = APIResponseEntity(
        500,
        storeResolutionSequenceEntities,
        "CO",
        "Store Resolutions Error",
        "OK"
    )

    private val apiResponseStoreResolutionSequenceEntitiesEmpty =
        APIResponseEntity<List<PosResolutionSequenceEntity>>(
            200,
            Collections.emptyList(),
            "CO",
            "Store Resolutions successfully Empty",
            "OK"
        )

    private val apiResponseStoreResolutionSequenceEntitiesIdNull = APIResponseEntity(
        200,
        storeResolutionSequenceIdNullEntity,
        "CO",
        "Store Resolutions successfully id Null",
        "OK"
    )

    @Test
    fun `test get store resolution sequence repositorywhen service returns successfully`() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getStoreResolutionSequence(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseStoreResolutionSequenceEntities))

            val result = storeResolutionSequenceRepository.getStoreResolutionSequence(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertEquals(Result.Success(storeResolutionSequenceDTOs), result)
        }
    }

    @Test
    fun test_getStoreResolutionSequence_when_repository_returns_Error() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getStoreResolutionSequence(
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
                        apiResponseStoreResolutionSequenceEntitiesError.toJson()
                    )
                )
            )

            val result = storeResolutionSequenceRepository.getStoreResolutionSequence(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getStoreResolutionSequence_when_repository_returns_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getStoreResolutionSequence(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseStoreResolutionSequenceEntitiesNull))

            val result = storeResolutionSequenceRepository.getStoreResolutionSequence(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getStoreResolutionSequence_when_repository_returns_Empty() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getStoreResolutionSequence(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseStoreResolutionSequenceEntitiesEmpty))

            val result = storeResolutionSequenceRepository.getStoreResolutionSequence(
                storeOrderRequestDTOMocks,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<StoreOrdersEntity>()), result)
        }
    }

    @Test
    fun test_getStoreResolutionSequence_when_repository_returns_Id_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getStoreResolutionSequence(
                    token,
                    storeOrderRequestDTOMocks.timeZone,
                    storeOrderRequestDTOMocks.storeId,
                    storeOrderRequestDTOMocks.localDateStart,
                    storeOrderRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponseStoreResolutionSequenceEntitiesIdNull))

            val result = storeResolutionSequenceRepository.getStoreResolutionSequence(
                storeOrderRequestDTOMocks,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<StoreOrdersEntity>()), result)
        }
    }
}