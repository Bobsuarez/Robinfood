package com.robinfood.app.usecases.getpaymentmethods

import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.paymentmethods.IPaymentMethodsRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GetPaymentMethodsUseCaseTest {

    @Mock
    private lateinit var paymentMethodsRepository: IPaymentMethodsRepository

    @InjectMocks
    private lateinit var getPaymentMethodsUseCase: GetPaymentMethodsUseCase

    private val channelId = 1L
    private val originId = 1L
    private val storeId = 1L
    private val token = "Token"

    private val paymentMethodsResponseList = listOf(
            PaymentMethodResponseDTO(
                    id = 1L,
                    image = "image.png",
                    name = "payment method 1",
                    originId = 2L,
                    slugName = "payment method 1"
            ),
            PaymentMethodResponseDTO(
                    id = 2L,
                    image = "image.png",
                    name = "payment method 2",
                    originId = 2L,
                    slugName = "payment method 2"
            )
    )

    @Test
    fun test_GetPaymentMethods_when_service_returns_OK() {
        runBlocking {
            `when`(
                    paymentMethodsRepository.getPaymentMethods(channelId, originId, storeId, token)
            ).thenReturn(Result.Success(paymentMethodsResponseList))

            val result = getPaymentMethodsUseCase.invoke(
                    channelId,
                    originId,
                    storeId,
                    token
            )

            assertEquals(Result.Success(paymentMethodsResponseList), result)
        }
    }
}
