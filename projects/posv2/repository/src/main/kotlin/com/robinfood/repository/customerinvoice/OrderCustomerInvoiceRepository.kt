package com.robinfood.repository.customerinvoice

import com.robinfood.core.constants.GlobalConstants.ORIGIN
import com.robinfood.core.dtos.customerinvoice.PrintFormatDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.customerinvoice.toPrintFormatDTO
import com.robinfood.network.api.RickOrAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class OrderCustomerInvoiceRepository(
    private val rickOrAPI: RickOrAPI,
    private val dispatchers: DispatcherProvider
) : IOrderCustomerInvoiceRepository {

    override suspend fun getOrderCustomerInvoiceDetail(orderId: Long, token: String): Result<PrintFormatDTO> {
        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    rickOrAPI.getOrderCustomerInvoiceDetail(
                        token,
                        ORIGIN,
                        orderId
                    ).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val printFormatEntity = result.data.data
                    if (printFormatEntity == null) {
                        Result.Error(
                            OrchestratorException("Order Customer Invoice response is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val printFormatDTO = printFormatEntity.printFormat.toPrintFormatDTO()
                        Result.Success(printFormatDTO)
                    }
                }
                is Result.Error -> result
            }
        }
    }

}
