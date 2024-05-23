package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO

/**
 * Use case Pos Resolution
 */
interface IGetPosResolutionUseCase {

    /**
     * Sends a request to get pos resolution
     * @param token the authentication token
     * @param dailyReportVoucherRequestDTO request for get information
     * @return the pos resolution by PosId
     */
    suspend fun invoke(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): PosResolutionSequenceDTO?
}