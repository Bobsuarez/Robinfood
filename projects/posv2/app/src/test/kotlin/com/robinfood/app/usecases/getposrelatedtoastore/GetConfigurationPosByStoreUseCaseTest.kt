package com.robinfood.app.usecases.getposrelatedtoastore

import com.robinfood.app.mocks.dailyreportevoucher.StorePosDTOMock
import com.robinfood.app.mocks.witnesstape.StoreOrderRequestDTOMocks
import com.robinfood.core.enums.Result
import com.robinfood.repository.posrelatedtoastore.ConfigurationPosByStoreRepository
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
class GetConfigurationPosByStoreUseCaseTest {

    @Mock
    private lateinit var configurationPosByStoreRepository: ConfigurationPosByStoreRepository

    @InjectMocks
    private lateinit var getConfigurationPosByStoreUseCase: GetConfigurationPosByStoreUseCase

    private val token = "token"

    private val storeOrderRequestDTOMocks = StoreOrderRequestDTOMocks().storeOrderRequestDTOMocks

    private val storeId = 27L

    private val storePosDTOMock = StorePosDTOMock().storePosDTOs

    @Test
    fun test_Get_Configuration_Pos_By_Store_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                configurationPosByStoreRepository.getPosRelatedToAStore(
                    storeId,
                    token
                )
            ).thenReturn(Result.Success(storePosDTOMock))

            val result = getConfigurationPosByStoreUseCase.invoke(
                storeId,
                token
            )
            Assertions.assertEquals(storePosDTOMock, result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_Get_Configuration_Pos_By_Store_UseCase_invoke_when_service_returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    configurationPosByStoreRepository.getPosRelatedToAStore(
                        storeId,
                        token
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getConfigurationPosByStoreUseCase.invoke(
                    storeId,
                    token
                )
            }
        }
    }
}