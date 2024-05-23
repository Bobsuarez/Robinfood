package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.dailyreportvoucher.IPosResolutionSequenceRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Implementation of IGetPosResolutionUseCase
 */
@Service
class GetPosResolutionUseCase(
    private val posResolutionSequenceRepository: IPosResolutionSequenceRepository
) : IGetPosResolutionUseCase {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun invoke(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): PosResolutionSequenceDTO? {

        val poosResolutionSequenceResult = posResolutionSequenceRepository.getPosResolutionSequence(
            dailyReportVoucherRequestDTO,
            token
        )

        return when (poosResolutionSequenceResult) {
            is Result.Error -> {
                log.error("Pos With Id Not Found {}", dailyReportVoucherRequestDTO.posId)
                null
            }
            is Result.Success -> poosResolutionSequenceResult.data
        }

    }

}