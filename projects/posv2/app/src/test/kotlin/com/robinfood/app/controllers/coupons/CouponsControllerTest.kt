package com.robinfood.app.controllers.coupons

import com.robinfood.app.POSApplication
import com.robinfood.app.mocks.CouponValidationRequestDTOsMocks
import com.robinfood.app.usecases.validatecoupon.IValidateCouponUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.COUPONS
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
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
import java.math.BigDecimal

@AutoConfigureMockMvc
@SpringBootTest(classes = [POSApplication::class])
@TestPropertySource(properties = [
    "jwt-token-prefix=Bearer ",
    "jwt-token-secret=secretForTesting",
    "jwt-token-aud=internal",
    "jwt-token-mod=posv2"
])
class CouponsControllerTest {

    companion object {
        private const val BEARER_AUTH = "Bearer "
        private const val TOKEN =
            "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var mockValidateCouponUseCase: IValidateCouponUseCase

    private val couponValidationRequestDTOsMocks = CouponValidationRequestDTOsMocks()

    private val couponValidationRequestDTO = couponValidationRequestDTOsMocks.couponValidationRequestDTO

    private val couponValidationResponseDTO = CouponValidationResponseDTO(
            1L,
            BigDecimal.valueOf(2000.0),
            BigDecimal.valueOf(8900.0),
            1L
    )



    @Test
    @Throws(Exception::class)
    fun test_validateCoupon_Should_Return_Ok() {
        runBlocking {
            `when`(mockValidateCouponUseCase.invoke(
                BEARER_AUTH + TOKEN,
                    couponValidationRequestDTO
            )).thenReturn(Result.Success(couponValidationResponseDTO))
            val result = mockMvc.perform(MockMvcRequestBuilders
                .post("$API_V1$COUPONS/validate")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(couponValidationRequestDTO.toJson())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk)
                .andExpect(content().json(ApiResponseDTO(couponValidationResponseDTO, "Coupon validated successfully").toJson()))
        }
    }


    @Test
    @Throws(Exception::class)
    fun test_validateCoupon_Should_Return_Failure() {
        runBlocking {
            `when`(mockValidateCouponUseCase.invoke(
                BEARER_AUTH + TOKEN,
                    couponValidationRequestDTO
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))
            val result = mockMvc.perform(MockMvcRequestBuilders
                .post("$API_V1$COUPONS/validate")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(couponValidationRequestDTO.toJson())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isBadRequest)
        }
    }

}