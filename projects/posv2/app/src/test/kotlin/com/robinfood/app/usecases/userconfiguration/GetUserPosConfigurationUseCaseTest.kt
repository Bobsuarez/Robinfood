package com.robinfood.app.usecases.userconfiguration

import com.robinfood.core.dtos.userconfiguration.PosResponseDTO
import com.robinfood.core.dtos.userconfiguration.StoreResponseDTO
import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.userconfiguration.IUserConfigurationRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GetUserPosConfigurationUseCaseTest {
    @Mock
    private lateinit var mockUserConfigurationRepository: IUserConfigurationRepository

    @InjectMocks
    private lateinit var getUserPosConfigurationUseCase: GetUserPosConfigurationUseCase

    private val token = "token"

    private val posResponseDTO = PosResponseDTO(
        1L,
        "POS TEST",
        "CO",
        1L,
        true,
        1,
        true
    )

    private val storeResponseDTO = StoreResponseDTO(
            "Address TEST",
            "City TEST",
            "Colombia",
            1L,
            "Store internal name",
            "Store name",
            "America/Bogota",
            "uuid"
    )

    private val userConfigurationResponseDTO = UserConfigurationResponseDTO(
            posResponseDTO,
            storeResponseDTO
    )

    @Test
    fun test_getPosStore_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                    mockUserConfigurationRepository.getUserPosConfiguration(
                            token,
                            1L,
                    )
            ).thenReturn(Result.Success(userConfigurationResponseDTO))
            val result = getUserPosConfigurationUseCase.invoke(
                    token,
                    1L,
            )
            assertEquals(Result.Success(userConfigurationResponseDTO), result)
        }
    }
}