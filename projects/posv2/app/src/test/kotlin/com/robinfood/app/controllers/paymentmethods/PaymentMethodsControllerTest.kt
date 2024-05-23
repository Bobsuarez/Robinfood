package com.robinfood.app.controllers.paymentmethods

import com.robinfood.app.POSApplication
import com.robinfood.app.usecases.getpaymentmethods.IGetPaymentMethodsUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.PAYMENT_METHODS
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.Collections

@AutoConfigureMockMvc
@SpringBootTest(classes = [POSApplication::class])
@TestPropertySource(properties = [
    "jwt-token-prefix=Bearer ",
    "jwt-token-secret=secretForTesting",
    "jwt-token-aud=internal",
    "jwt-token-mod=posv2"
])
class PaymentMethodsControllerTest {

    companion object {
        private const val BEARER_AUTH = "Bearer "
        private const val TOKEN =
            "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var mockGetPaymentMethodsUseCase: IGetPaymentMethodsUseCase

    private val channelId = 1L
    private val originId = 1L
    private val storeId = 1L

    private val paymentMethodsDTOs = Collections.singletonList(
            PaymentMethodResponseDTO(
                    id = 1L,
                    image = "Image.png",
                    name = "Payment method 1",
                    originId = 6L,
                    slugName = "Payment method 1"
            )
    )

    @Test
    @Throws(Exception::class)
    fun test_getPaymentMethods_Should_Return_Ok() {
        runBlocking {
            `when`(mockGetPaymentMethodsUseCase.invoke(
                    channelId,
                    originId,
                    storeId,
                    BEARER_AUTH + TOKEN
            )).thenReturn(Result.Success(paymentMethodsDTOs))
            val result = mockMvc.perform(MockMvcRequestBuilders
                .get("$API_V1$PAYMENT_METHODS")
                .queryParam("channel_id", "1")
                .queryParam("origin_id", "1")
                .queryParam("store_id", "1")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk)
                .andExpect(content().json(ApiResponseDTO(paymentMethodsDTOs, "Payment methods retrieved successfully").toJson()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_getPaymentMethods_Should_Return_empty() {
        runBlocking {
            `when`(mockGetPaymentMethodsUseCase.invoke(
                    channelId,
                    originId,
                    storeId,
                    BEARER_AUTH + TOKEN
            )).thenReturn(Result.Success(Collections.emptyList()))
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("$API_V1$PAYMENT_METHODS")
                    .queryParam("channel_id", "1")
                    .queryParam("origin_id", "1")
                    .queryParam("store_id", "1")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isNoContent)
                    .andExpect(content().json(ApiResponseDTO<List<PaymentMethodResponseDTO>>(Collections.emptyList(), "There are no payment methods assigned to the store").toJson()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_validateCoupon_Should_Return_Failure() {
        runBlocking {
            `when`(mockGetPaymentMethodsUseCase.invoke(
                    channelId,
                    originId,
                    storeId,
                BEARER_AUTH + TOKEN
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))
            val result = mockMvc.perform(MockMvcRequestBuilders
                .get("$API_V1$PAYMENT_METHODS")
                .queryParam("channel_id", "1")
                .queryParam("origin_id", "1")
                .queryParam("store_id", "1")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isBadRequest)
        }
    }

}