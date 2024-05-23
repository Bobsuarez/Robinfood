package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.posresolution.toPosResolutionEntityToPosResolutionDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

/**
 * Implementation of IPosResolutionSequenceRepository
 */
@Repository
class PosResolutionSequenceRepository(
    private val orderCreationQueriesAPI: OrderCreationQueriesAPI,
    private val dispatchers: DispatcherProvider
) : IPosResolutionSequenceRepository {

    override suspend fun getPosResolutionSequence(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): Result<PosResolutionSequenceDTO> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    orderCreationQueriesAPI.getPosResolutionSequence(
                        token,
                        dailyReportVoucherRequestDTO.timeZone,
                        dailyReportVoucherRequestDTO.posId,
                        dailyReportVoucherRequestDTO.localDateStart,
                        dailyReportVoucherRequestDTO.localDateEnd
                    ).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val posResolutionSequenceEntity = result.data.data
                    if (posResolutionSequenceEntity == null) {
                        Result.Error(
                            OrchestratorException("User pos configuration by User is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val posResolutionSequenceDTO = posResolutionSequenceEntity
                            .toPosResolutionEntityToPosResolutionDTO()
                        returnResult(posResolutionSequenceDTO)
                    }
                }
                is Result.Error -> result
            }
        }

    }

    private fun returnResult(
        posResolutionSequenceDTO: PosResolutionSequenceDTO?
    ): Result<PosResolutionSequenceDTO> {
        return if (posResolutionSequenceDTO == null) {
            Result.Error(
                OrchestratorException("Error converting pos resolution is null"),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        } else {
            Result.Success(posResolutionSequenceDTO)
        }
    }

}