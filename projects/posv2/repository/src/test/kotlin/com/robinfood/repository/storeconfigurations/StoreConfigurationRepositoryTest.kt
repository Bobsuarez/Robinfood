package com.robinfood.repository.storeconfigurations

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.store.StoreConfigurationsEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.store.toStoreConfigurationDTO
import com.robinfood.network.api.StoreOrAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.dailyreportvoucher.StoreConfigurationsEntitiesMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
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

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class StoreConfigurationRepositoryTest {

    @Mock
    private lateinit var mockStoreOrAPI: StoreOrAPI

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    @InjectMocks
    private lateinit var storeConfigurationRepository: StoreConfigurationRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val storeConfigurationsEntitiesMock = StoreConfigurationsEntitiesMock().storeConfigurationsEntityMock
    private val storeConfigurationsEntitiesIdNullMock = StoreConfigurationsEntitiesMock()
        .storeConfigurationsEntityIdNullMock

    private val storeConfigurationsEntitiesSuccess = storeConfigurationsEntitiesMock.toStoreConfigurationDTO()

    private val apiStoreConfigurationResponseEntity = APIResponseEntity(
        200,
        storeConfigurationsEntitiesMock,
        "CO",
        "Store by id get successfully",
        "OK"
    )

    private val apiStoreConfigurationResponseNullEntity = APIResponseEntity<StoreConfigurationsEntity>(
        200,
        null,
        "CO",
        "Store by id get successfully",
        "OK"
    )

    private val apiStoreConfigurationResponseFailEntity = APIResponseEntity<StoreConfigurationsEntity>(
        200,
        storeConfigurationsEntitiesIdNullMock,
        "CO",
        "orders error payment by date successfully",
        "Fail"
    )

    private val apiStoreConfigurationResponseErrorEntity = APIResponseEntity(
        500,
        storeConfigurationsEntitiesMock,
        "CO",
        "orders error payment by date successfully",
        "Error"
    )

    private val storeId = 1L

    @Test
    fun test_StoreConfigurationRepositoryTest_getStoreConfiguration_when_repository_returns_OK() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                mockStoreOrAPI.getStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(Response.success(apiStoreConfigurationResponseEntity))

            val result = storeConfigurationRepository.getStoreConfiguration(
                token,
                storeId
            )

            assertEquals(Result.Success(storeConfigurationsEntitiesSuccess!!), result)
        }
    }

    @Test
    fun test_StoreConfigurationRepository_getStoreConfiguration_when_repository_returns_Null() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                mockStoreOrAPI.getStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(Response.success(apiStoreConfigurationResponseNullEntity))

            val result = storeConfigurationRepository.getStoreConfiguration(
                token,
                storeId
            )

            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_StoreConfigurationRepository_getStoreConfiguration_when_repository_returns_fails_converting() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                mockStoreOrAPI.getStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(Response.success(apiStoreConfigurationResponseFailEntity))

            val result = storeConfigurationRepository.getStoreConfiguration(
                token,
                storeId
            )

            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_StoreConfigurationRepository_getStoreConfiguration_when_repository_returns_Error() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                mockStoreOrAPI.getStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(
                Response.error(
                    500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        apiStoreConfigurationResponseErrorEntity.toJson()
                    )
                )
            )
            val result = storeConfigurationRepository.getStoreConfiguration(
                token,
                storeId
            )
            assertTrue(result is Result.Error)
        }
    }
}