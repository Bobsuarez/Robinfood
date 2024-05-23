package com.robinfood.repository.paymentmethods

import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.paymentmethods.toPaymentMethodResponseDTO
import com.robinfood.network.api.PaymentMethodsAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

/**
 * Implementation of IPaymentMethodsRepository
 */
@Repository
class PaymentMethodsRepository(
        private val paymentMethodsAPI: PaymentMethodsAPI,
        private val dispatchers: DispatcherProvider
) : IPaymentMethodsRepository {
    /**
     * Retrieves the store's payment methods
     * [channelId] channel id
     * [originId] the origin identification to get payment methods
     * [storeId] the store identification to get payment methods
     * [token] the authentication token to be used
     * @return the active delivery platforms for the store
     */
    override suspend fun getPaymentMethods(
            channelId: Long,
            originId: Long,
            storeId: Long,
            token: String
    ): Result<List<PaymentMethodResponseDTO>> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                    call = {
                        paymentMethodsAPI.getPaymentMethods(channelId, originId, storeId, token).callServices()
                    }
            )
            when (result) {
                is Result.Success -> {
                    val paymentMethodsResponseEntity = result.data.data
                    if (paymentMethodsResponseEntity == null) {
                        Result.Error(
                                OrchestratorException("Payment methods response is null"),
                                HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                       val paymentMethodsDTOs = paymentMethodsResponseEntity.mapNotNull {
                           paymentMethodResponseEntity -> paymentMethodResponseEntity.toPaymentMethodResponseDTO()
                       }
                       Result.Success(paymentMethodsDTOs)
                    }
                }
                is Result.Error -> result
            }
        }
    }
}