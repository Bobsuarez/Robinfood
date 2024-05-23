package com.robinfood.app.usecases.validatecoupon

import com.robinfood.app.mocks.CouponValidationRequestDTOsMocks
import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.mappers.coupons.toCouponValidationRequestEntity
import com.robinfood.repository.coupons.ICouponsRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.InjectMocks
import org.mockito.Mock
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class ValidateCouponUseCaseTest {
    @Mock
    private lateinit var couponsRepository: ICouponsRepository

    @InjectMocks
    private lateinit var validateCouponUseCase: ValidateCouponUseCase

    private val token = "token"

    private val couponValidationRequestDTOsMocks = CouponValidationRequestDTOsMocks()

    private val couponValidationRequestDTO = couponValidationRequestDTOsMocks.couponValidationRequestDTO

    private val couponValidationRequestEntity = couponValidationRequestDTO.toCouponValidationRequestEntity()

    private val couponValidationResponseDTO = CouponValidationResponseDTO (
            1L,
            BigDecimal.valueOf(2000.0),
            BigDecimal.valueOf(8900.0),
            1L
    )

    @Test
    fun test_ValidateCoupon_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                    couponsRepository.validateCoupon(
                            token,
                            couponValidationRequestEntity
                    )
            ).thenReturn(Result.Success(couponValidationResponseDTO))
            val result = validateCouponUseCase.invoke(
                    token,
                    couponValidationRequestDTO
            )
            assertEquals(Result.Success(couponValidationResponseDTO), result)
        }
    }
}