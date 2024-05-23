package com.robinfood.app.usecases.getstoreconfigurations

import com.robinfood.app.mocks.StoreConfigurationsDTOMock
import com.robinfood.core.enums.Result
import com.robinfood.repository.storeconfigurations.IStoreConfigurationRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class GetStoreConfigurationUseCaseTest {

    @Mock
    private lateinit var storeConfigurationRepository: IStoreConfigurationRepository

    @InjectMocks
    private lateinit var getStoreConfigurationUseCase: GetStoreConfigurationUseCase

    private val token = "token"

    private val storeConfigurationsDTOMock = StoreConfigurationsDTOMock().storeConfigurationsDTOMock

    private val storeId = 1L

    @Test
    fun test_Get_Store_Configuration_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                storeConfigurationRepository.getStoreConfiguration(
                    token,
                    storeId
                )
            ).thenReturn(Result.Success(storeConfigurationsDTOMock))

            val result = getStoreConfigurationUseCase.invoke(
                storeId,
                token
            )
            Assertions.assertEquals(storeConfigurationsDTOMock, result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_Get_Store_Configuration_UseCase_invoke_when_service_returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    storeConfigurationRepository.getStoreConfiguration(
                        token,
                        storeId
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            }
        }
    }

}