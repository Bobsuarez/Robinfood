package com.robinfood.app.usecases.witnesstape

import com.robinfood.app.mocks.witnesstape.StoreOrderRequestDTOMocks
import com.robinfood.app.mocks.witnesstape.StoreResolutionSequenceDTOMocks
import com.robinfood.core.enums.Result
import com.robinfood.repository.witnesstape.IStoreResolutionSequenceRepository
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
class GetStoreResolutionSequenceUseCaseTest {

    @Mock
    private lateinit var storeResolutionSequenceRepository: IStoreResolutionSequenceRepository

    @InjectMocks
    private lateinit var getStoreResolutionSequenceUseCase: GetStoreResolutionSequenceUseCase

    private val token = "token"

    private val storeResolutionSequenceDTOs = StoreResolutionSequenceDTOMocks().storeResolutionSequenceEntities

    private val storeOrderRequestDTOMocks = StoreOrderRequestDTOMocks().storeOrderRequestDTOMocks

    @Test
    fun test_Get_Store_Resolution_Sequence_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                storeResolutionSequenceRepository.getStoreResolutionSequence(
                    storeOrderRequestDTOMocks,
                    token
                )
            ).thenReturn(Result.Success(storeResolutionSequenceDTOs))

            val result = getStoreResolutionSequenceUseCase.invoke(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertEquals(storeResolutionSequenceDTOs, result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_Get_Store_Resolution_Sequence_UseCase_invoke_when_service_returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    storeResolutionSequenceRepository.getStoreResolutionSequence(
                        storeOrderRequestDTOMocks,
                        token
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getStoreResolutionSequenceUseCase.invoke(
                    storeOrderRequestDTOMocks,
                    token
                )
            }
        }
    }
}