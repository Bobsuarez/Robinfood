package com.robinfood.app.controllers.paymentmethods

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest

/**
 * Exposes the API that handles all data related to payment methods
 */
@Tag(name = "Payment methods", description = "Requests for payment methods related data")
interface IPaymentMethodsController {

    /**
     * Retrieves the payment methods that belongs to store and channel given
     * [httpServletRequest] the authentication token to be used
     * [channelId] number of objects per page
     * [originId] the origin identification to get payment methods
     * [storeId] the store identification to get payment methods
     * @return the active delivery platforms for the store
     */
    suspend fun getPaymentMethods(
        httpServletRequest: HttpServletRequest,
        channelId: Long,
        originId: Long,
        storeId: Long
    ): ResponseEntity<ApiResponseDTO<List<PaymentMethodResponseDTO>>>
}
