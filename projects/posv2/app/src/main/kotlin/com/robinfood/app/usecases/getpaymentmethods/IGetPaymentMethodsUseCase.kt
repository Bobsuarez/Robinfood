package com.robinfood.app.usecases.getpaymentmethods

import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves the store's payment methods
 */
interface IGetPaymentMethodsUseCase {

    /**
     * Retrieves store's payment methods
     * [channelId] the identification of channel
     * [originId] the origin identification to get payment methods
     * [storeId] store identification to ask for delivery platforms
     * [token] the authorization token
     * @return the active delivery platforms for the store
     */
    suspend fun invoke(
            channelId: Long,
            originId: Long,
            storeId: Long,
            token: String
    ): Result<List<PaymentMethodResponseDTO>>
}
