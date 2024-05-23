package com.robinfood.repository.paymentmethods

import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.paymentmethods.PaymentMethodResponseEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.network.api.PaymentMethodsAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response
import java.util.Collections

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class PaymentMethodsRepositoryTest {

    @Mock
    private lateinit var mockPaymentMethodsAPI: PaymentMethodsAPI

    @Mock
    private lateinit var mockDispatchers: DispatcherProvider

    @InjectMocks
    private lateinit var paymentMethodsRepository: PaymentMethodsRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val channelId: Long = 1L
    private val originId: Long = 1L
    private val storeId: Long = 1L
    private val token = "token"

    private val paymentMethodsResponseEntities = listOf(
            PaymentMethodResponseEntity(
                    id = 1L,
                    image = "image.png",
                    name = "Payment Method 1",
                    originId = 1L,
                    slugName = "paymentMethodSlug"
            ),
            PaymentMethodResponseEntity(
                    id = 2L,
                    image = "image.png",
                    name = "Payment Method 2",
                    originId = 2L,
                    slugName = "paymentMethodSlug"
            )
    )

    private val paymentMethodsResponseIdNullEntities = listOf(
        PaymentMethodResponseEntity(
            id = null,
            image = "image.png",
            name = "Payment Method 1",
            originId = 1L,
            slugName = "paymentMethodSlug"
        )
    )

    private val paymentMethodsResponseDTOs = listOf(
            PaymentMethodResponseDTO(
                    id = 1L,
                    image = "image.png",
                    name = "Payment Method 1",
                    originId = 1L,
                    slugName = "paymentMethodSlug"
            ),
            PaymentMethodResponseDTO(
                    id = 2L,
                    image = "image.png",
                    name = "Payment Method 2",
                    originId = 2L,
                    slugName = "paymentMethodSlug"
            )
    )

    private val apiResponseErrorEntity = APIResponseEntity(
            500,
            paymentMethodsResponseEntities,
            "CO",
            "Error",
            "Payment methods could not be returned"
    )

    private val apiResponseEntity = APIResponseEntity(
            200,
            paymentMethodsResponseEntities,
            "CO",
            "",
            "Payment methods returned"
    )

    private val apiResponseIdNullEntity = APIResponseEntity(
        200,
        paymentMethodsResponseIdNullEntities,
        "CO",
        "",
        "Payment methods returned"
    )

    private val apiResponseEntityWithPaymentMethodsNull = APIResponseEntity<List<PaymentMethodResponseEntity>>(
            200,
            null,
            "CO",
            "",
            "Payment methods returned"
    )

    private val apiResponseEntityWithPaymentMethodsEmpty = APIResponseEntity<List<PaymentMethodResponseEntity>>(
            200,
            Collections.emptyList(),
            "CO",
            "",
            "Payment methods returned"
    )

    @Test
    fun `test get payment methods when service returns successfully`() {
        runBlocking {
            `when`(mockDispatchers.io).thenReturn(testDispatcher)
            `when`(mockPaymentMethodsAPI.getPaymentMethods(
                    channelId,
                    originId,
                    storeId,
                    token
            )).thenReturn(Response.success(apiResponseEntity))
            val result = paymentMethodsRepository.getPaymentMethods(
                    channelId,
                    originId,
                    storeId,
                    token
            )
            assertEquals(Result.Success(paymentMethodsResponseDTOs), result)
        }
    }

    @Test
    fun `test get payment methods when service returns successfully but the payment methods are null`() {
        runBlocking {
            `when`(mockDispatchers.io).thenReturn(testDispatcher)
            `when`(mockPaymentMethodsAPI.getPaymentMethods(
                    channelId,
                    originId,
                    storeId,
                    token
            )).thenReturn(Response.success(apiResponseEntityWithPaymentMethodsNull))
            val result = paymentMethodsRepository.getPaymentMethods(
                    channelId,
                    originId,
                    storeId,
                    token
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get payment methods when service returns successfully but the payment methods are empty`() {
        runBlocking {
            `when`(mockDispatchers.io).thenReturn(testDispatcher)
            `when`(mockPaymentMethodsAPI.getPaymentMethods(
                    channelId,
                    originId,
                    storeId,
                    token
            )).thenReturn(Response.success(apiResponseEntityWithPaymentMethodsEmpty))
            val result = paymentMethodsRepository.getPaymentMethods(
                    channelId,
                    originId,
                    storeId,
                    token
            )
            assertEquals(Result.Success(Collections.emptyList<PaymentMethodResponseDTO>()), result)
        }
    }

    @Test
    fun `test get payment methods when service returns with error`() {
        runBlocking {
            `when`(mockDispatchers.io).thenReturn(testDispatcher)
            `when`(mockPaymentMethodsAPI.getPaymentMethods(
                    channelId,
                    originId,
                    storeId,
                    token
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiResponseErrorEntity.toJson()
            )))
            val result = paymentMethodsRepository.getPaymentMethods(
                    channelId,
                    originId,
                    storeId,
                    token
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get payment methods when service id is null returns successfully`() {
        runBlocking {
            `when`(mockDispatchers.io).thenReturn(testDispatcher)
            `when`(mockPaymentMethodsAPI.getPaymentMethods(
                channelId,
                originId,
                storeId,
                token
            )).thenReturn(Response.success(apiResponseIdNullEntity))
            val result = paymentMethodsRepository.getPaymentMethods(
                channelId,
                originId,
                storeId,
                token
            )
            assertTrue(result is Result.Success)
        }
    }
}