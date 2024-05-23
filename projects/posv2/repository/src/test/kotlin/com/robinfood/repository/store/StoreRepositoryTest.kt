package com.robinfood.repository.store

import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.store.StoreDeliveryPlatformEntity
import com.robinfood.core.entities.store.StoreDeliveryPlatformResponseEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.network.api.IntegrationsBackofficeAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
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
class StoreRepositoryTest {

    @Mock
    private lateinit var integrationsBackofficeAPI: IntegrationsBackofficeAPI

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    @InjectMocks
    private lateinit var storeRepository: StoreRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val testDispatcherExceptionHandler = TestCoroutineExceptionHandler()

    private val page = 1
    private val size = 1
    private val storeId = 1L
    private val token = "Token"

    private val storeDeliveryPlatformsResponseEntities = listOf(
            StoreDeliveryPlatformEntity(
                    color = "FFFFFF",
                    flowId = 1L,
                    id = 1L,
                    image = "image.png",
                    name = "Platform 1",
                    slug = "Platform 1",
                    status = 1L
            ),
            StoreDeliveryPlatformEntity(
                    color = "FFFFFF",
                    flowId = 2L,
                    id = 2L,
                    image = "image.png",
                    name = "Platform 2",
                    slug = "Platform 2",
                    status = 2L
            )
    )

    private val storeDeliveryPlatformsDTOs = listOf(
            StoreDeliveryPlatformDTO(
                    color = "FFFFFF",
                    flowId = 1L,
                    id = 1L,
                    imageUrl = "image.png",
                    name = "Platform 1",
                    slug = "Platform 1",
                    status = 1L
            ),
            StoreDeliveryPlatformDTO(
                    color = "FFFFFF",
                    flowId = 2L,
                    id = 2L,
                    imageUrl = "image.png",
                    name = "Platform 2",
                    slug = "Platform 2",
                    status = 2L
            )
    )

    private val storeDeliveryPlatformResponseEntity = StoreDeliveryPlatformResponseEntity(
            content = storeDeliveryPlatformsResponseEntities
    )

    private val apiStoreDeliveryPlatformsResponseEntity = APIResponseEntity(
            200,
            storeDeliveryPlatformResponseEntity,
            "CO",
            "Store delivery platforms retrieved successfully",
            "OK"
    )

    private val apiStoreDeliveryPlatformsResponseEntityError = APIResponseEntity(
            500,
            storeDeliveryPlatformResponseEntity,
            "CO",
            "Store delivery platforms could not be retrieved",
            "Error"
    )

    private val apiStoreDeliveryPlatformsResponseEntityNull = APIResponseEntity<StoreDeliveryPlatformResponseEntity>(
            200,
            null,
            "CO",
            "Store delivery platforms retrieved successfully",
            "OK"
    )

    @Test
    fun test_GetStoreDeliveryPlatforms_when_service_returns_OK() {
        runBlocking {

            `when`(dispatchers.io).thenReturn(testDispatcher)

            `when`(integrationsBackofficeAPI.getStoreDeliveryPlatforms(
                    storeId,
                    page,
                    size,
                    token
            )).thenReturn(Response.success(apiStoreDeliveryPlatformsResponseEntity))
            val result = storeRepository.getStoreDeliveryPlatforms(
                    page,
                    size,
                    storeId,
                    token,
            )
            assertEquals(Result.Success(storeDeliveryPlatformsDTOs), result)
        }
    }

    @Test
    fun test_GetStoreDeliveryPlatforms_when_service_returns_ERROR() {
        runBlocking {

            `when`(dispatchers.io).thenReturn(testDispatcher)

            `when`(integrationsBackofficeAPI.getStoreDeliveryPlatforms(
                    storeId,
                    page,
                    size,
                    token
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiStoreDeliveryPlatformsResponseEntityError.toJson()
            )))
            val result = storeRepository.getStoreDeliveryPlatforms(
                    page,
                    size,
                    storeId,
                    token,
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_GetStoreDeliveryPlatforms_when_service_returns_NULL() {
        runBlocking {

            `when`(dispatchers.io).thenReturn(testDispatcher)

            `when`(integrationsBackofficeAPI.getStoreDeliveryPlatforms(
                    storeId,
                    page,
                    size,
                    token
            )).thenReturn(Response.success(apiStoreDeliveryPlatformsResponseEntityNull))
            val result = storeRepository.getStoreDeliveryPlatforms(
                    page,
                    size,
                    storeId,
                    token,
            )
            assertTrue(result is Result.Error)
        }
    }

}