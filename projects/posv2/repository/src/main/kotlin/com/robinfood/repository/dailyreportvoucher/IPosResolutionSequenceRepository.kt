package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.enums.Result

interface IPosResolutionSequenceRepository {

    suspend fun getPosResolutionSequence(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): Result<PosResolutionSequenceDTO>
}