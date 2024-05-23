package com.robinfood.repository.userconfiguration

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.userconfiguration.PosEntity
import com.robinfood.core.entities.userconfiguration.StoreEntity
import com.robinfood.core.entities.userconfiguration.UserConfigurationEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.userconfiguration.toUserConfigurationResponseDTO
import com.robinfood.network.api.StoreOrAPI
import com.robinfood.network.di.DispatcherProvider
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
class UserConfigurationRepositoryTest {

    @Mock
    private lateinit var mockStoreOrAPI: StoreOrAPI

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    @InjectMocks
    private lateinit var userConfigurationRepository: UserConfigurationRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val posEntity = PosEntity(
        1L,
        "POS TEST",
        "CO",
        1L,
        true,
        1,
        true
    )

    private val storeEntity = StoreEntity(
            "Address TEST",
            "City TEST",
            "Colombia",
            1L,
            "Store internal name",
            "Store name",
            "America/Bogota",
            "uuid"
    )

    private val userConfigurationFailEntity = UserConfigurationEntity(
            null,
            null
    )

    private val userConfigurationEntity = UserConfigurationEntity(
            posEntity,
            storeEntity
    )

    private val apiUserConfigurationResponseEntity = APIResponseEntity(
        200,
            userConfigurationEntity,
        "CO",
        "Pos by User get successfully",
        "OK"
    )

    private val apiUserConfigurationResponseFailEntity = APIResponseEntity(
            200,
            userConfigurationFailEntity,
            "CO",
            "Pos by User get successfully",
            "OK"
    )

    private val apiUserConfigurationResponseErrorEntity = APIResponseEntity(
        500,
            userConfigurationEntity,
        "CO",
        "Pos by User does not exist",
        "Error"
    )

    private val apiUserConfigurationResponseNullEntity = APIResponseEntity<UserConfigurationEntity>(
        200,
        null,
        "CO",
        "Pos by User get successfully",
        "OK"
    )

    private val userConfigurationResponseSuccess = userConfigurationEntity.toUserConfigurationResponseDTO()

    private val userId = 1L

    @Test
    fun test_getUserConfiguration_when_repository_returns_OK() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                mockStoreOrAPI.getUserPosConfiguration(
                    token,
                    userId
                )
            ).thenReturn(Response.success(apiUserConfigurationResponseEntity))
            val result = userConfigurationRepository.getUserPosConfiguration(
                token,
                userId
            )

            assertEquals(Result.Success(userConfigurationResponseSuccess!!), result)
        }
    }

    @Test
    fun test_getUserConfiguration_when_repository_fails_converting() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                    mockStoreOrAPI.getUserPosConfiguration(
                            token,
                            userId
                    )
            ).thenReturn(Response.success(apiUserConfigurationResponseFailEntity))
            val result = userConfigurationRepository.getUserPosConfiguration(
                    token,
                    userId
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getUserConfiguration_when_repository_returns_Error() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                mockStoreOrAPI.getUserPosConfiguration(
                    token,
                    userId
                )
            ).thenReturn(
                Response.error(
                    500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        apiUserConfigurationResponseErrorEntity.toJson()
                    )
                )
            )
            val result = userConfigurationRepository.getUserPosConfiguration(
                token,
                userId
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getUserConfiguration_when_repository_returns_Null() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                mockStoreOrAPI.getUserPosConfiguration(
                    token,
                    userId
                )
            ).thenReturn(Response.success(apiUserConfigurationResponseNullEntity))
            val result = userConfigurationRepository.getUserPosConfiguration(
                token,
                userId
            )
            assertTrue(result is Result.Error)
        }
    }
}