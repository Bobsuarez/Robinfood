package com.robinfood.repository.paymentmethods

import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import com.robinfood.core.enums.Result

/**
 * Repository for payment methods related data
 */
interface IPaymentMethodsRepository {

    /**
     * Retrieves the store's payment methods
     * [channelId] channel id
     * [originId] the origin identification to get payment methods
     * [storeId] the store identification to get payment methods
     * [token] the authentication token to be used
     * @return the active delivery platforms for the store
     */
    suspend fun getPaymentMethods(
            channelId: Long,
            originId: Long,
            storeId: Long,
            token: String
    ): Result<List<PaymentMethodResponseDTO>>
}