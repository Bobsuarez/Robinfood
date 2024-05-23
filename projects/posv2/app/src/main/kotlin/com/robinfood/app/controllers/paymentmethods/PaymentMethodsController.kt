package com.robinfood.app.controllers.paymentmethods

import com.robinfood.app.usecases.getpaymentmethods.IGetPaymentMethodsUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.PAYMENT_METHODS
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import com.robinfood.core.enums.Result
import javax.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

/**
 * Implementation of 'IStoreController'
 */
@RestController
@RequestMapping(API_V1 + PAYMENT_METHODS)
class PaymentMethodsController(
        private val getPaymentMethodsUseCase: IGetPaymentMethodsUseCase
) : IPaymentMethodsController {

    @GetMapping()
    override suspend fun getPaymentMethods(
            httpServletRequest: HttpServletRequest,
            @RequestParam(value = "channel_id", required = true) channelId: Long,
            @RequestParam(value = "origin_id", required = true) originId: Long,
            @RequestParam(value = "store_id", required = true) storeId: Long
    ): ResponseEntity<ApiResponseDTO<List<PaymentMethodResponseDTO>>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        return when (
            val resultPaymentMethods = getPaymentMethodsUseCase.invoke(
                    channelId,
                    originId,
                    storeId,
                    token
            )
        ) {
            is Result.Success -> {
                var responseStatus = HttpStatus.OK
                var responseMessage = "Payment methods retrieved successfully"
                if (resultPaymentMethods.data.isEmpty()) {
                    responseMessage = "There are no payment methods assigned to the store"
                    responseStatus = HttpStatus.NO_CONTENT
                }
                ResponseEntity(
                        ApiResponseDTO(
                                resultPaymentMethods.data,
                                responseMessage
                        ),
                        responseStatus
                )
            }
            is Result.Error -> throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    resultPaymentMethods.exception.localizedMessage
            )
        }
    }
}
