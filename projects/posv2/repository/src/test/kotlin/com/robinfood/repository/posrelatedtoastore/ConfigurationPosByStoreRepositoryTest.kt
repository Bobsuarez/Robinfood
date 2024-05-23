package com.robinfood.repository.posrelatedtoastore

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.posrelatedtoastore.StorePosEntity
import com.robinfood.core.entities.witnesstape.StoreOrdersEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.posrelatedtoastore.toStorePosDTO
import com.robinfood.network.api.StoreOrAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.posrelatedtoastore.StorePosEntityMock
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
class ConfigurationPosByStoreRepositoryTest {

    @Mock
    private lateinit var storeOrAPI: StoreOrAPI

    @InjectMocks
    private lateinit var configurationPosByStoreRepository: ConfigurationPosByStoreRepository

    private val storePosEntities = StorePosEntityMock().storePosEntities

    val storePosDTOs = storePosEntities.mapNotNull { storePosEntity ->
        storePosEntity.toStorePosDTO()
    }

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val apiResponsePosByStoreEntities = APIResponseEntity(
        200,
        storePosEntities,
        "CO",
        "Pos by store successfully",
        "OK"
    )

    private val apiResponsePosByStoreEntitiesNull = APIResponseEntity<List<StorePosEntity>>(
        200,
        null,
        "CO",
        "Pos by store successfully",
        "OK"
    )

    private val apiResponsePosByStoreEntitiesError = APIResponseEntity(
        500,
        storePosEntities,
        "CO",
        "Pos by store Error",
        "OK"
    )

    private val apiResponsePosByStoreEntitiesEmpty =
        APIResponseEntity<List<StorePosEntity>>(
            200,
            Collections.emptyList(),
            "CO",
            "Store Resolutions successfully Empty",
            "OK"
        )

    private val storeId = 1L

    @Test
    fun `test get pos by store repository when service returns successfully`() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                storeOrAPI.getPosByStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(Response.success(apiResponsePosByStoreEntities))

            val result = configurationPosByStoreRepository.getPosRelatedToAStore(
                storeId,
                token
            )
            Assertions.assertEquals(Result.Success(storePosDTOs), result)
        }
    }

    @Test
    fun test_getPosRelatedToAStore_when_repository_returns_Error() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                storeOrAPI.getPosByStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(
                Response.error(
                    500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        apiResponsePosByStoreEntitiesError.toJson()
                    )
                )
            )

            val result = configurationPosByStoreRepository.getPosRelatedToAStore(
                storeId,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getPosRelatedToAStore_when_repository_returns_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                storeOrAPI.getPosByStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(Response.success(apiResponsePosByStoreEntitiesNull))

            val result = configurationPosByStoreRepository.getPosRelatedToAStore(
                storeId,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getPosRelatedToAStore_when_repository_returns_Empty() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                storeOrAPI.getPosByStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(Response.success(apiResponsePosByStoreEntitiesEmpty))

            val result = configurationPosByStoreRepository.getPosRelatedToAStore(
                storeId,
                token
            )

            Assertions.assertEquals(Result.Success(Collections.emptyList<StoreOrdersEntity>()), result)
        }
    }
}