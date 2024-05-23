package com.robinfood.app.usecases.getordercustomerinvoice

import com.robinfood.core.dtos.customerinvoice.PrintFormatDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.customerinvoice.IOrderCustomerInvoiceRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetOrderCustomerInvoiceUseCase(
    private val orderCustomerInvoiceRepository: IOrderCustomerInvoiceRepository
) : IGetOrderCustomerInvoiceUseCase {

    override suspend fun invoke(
        orderId: Long,
        token: String
    ): PrintFormatDTO? {

        val orderCustomerInvoiceResult = orderCustomerInvoiceRepository.getOrderCustomerInvoiceDetail(
            orderId,
            token
        )

        return when (orderCustomerInvoiceResult) {
            is Result.Error -> throw  ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found")
            is Result.Success -> return orderCustomerInvoiceResult.data
        }
    }
}