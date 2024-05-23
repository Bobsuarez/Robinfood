package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.app.mocks.DailyReportVoucherRequestDTOMocks
import com.robinfood.app.mocks.PosResolutionSequenceDTOMocks
import com.robinfood.core.enums.Result
import com.robinfood.repository.dailyreportvoucher.IPosResolutionSequenceRepository
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
class GetPosResolutionUseCaseTest {
    @Mock
    private lateinit var mockPosResolutionSequenceRepository: IPosResolutionSequenceRepository

    @InjectMocks
    private lateinit var getPosResolutionUseCase: GetPosResolutionUseCase

    private val token = "token"

    private val posResolutionSequenceDTOMocks = PosResolutionSequenceDTOMocks().posResolutionSequenceDTO

    private val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTOMocks()
        .dailyReportVoucherRequestDTOMocks

    @Test
    fun test_Get_Pos_Resolution_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                mockPosResolutionSequenceRepository.getPosResolutionSequence(
                    dailyReportVoucherRequestDTOMocks,
                    token,
                )
            ).thenReturn(Result.Success(posResolutionSequenceDTOMocks))

            val result = getPosResolutionUseCase.invoke(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertEquals(posResolutionSequenceDTOMocks, result)
        }
    }

    @Test
    fun test_Get_Pos_Resolution_UseCase_invoke_when_service_returns_Error() {
        runBlocking {
            Mockito.`when`(
                mockPosResolutionSequenceRepository.getPosResolutionSequence(
                    dailyReportVoucherRequestDTOMocks,
                    token,
                )
            ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            val result = getPosResolutionUseCase.invoke(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertEquals(null, result)
        }
    }

}