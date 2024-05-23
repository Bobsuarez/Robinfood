package com.robinfood.app.mocks

import com.robinfood.core.dtos.coupons.CouponValidationOrderProductRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationOrderRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationOriginRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationPaymentRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationUserRequestDTO
import java.math.BigDecimal
import java.util.Collections

class CouponValidationRequestDTOsMocks {

    private val couponValidationOrderProductRequestDTO = CouponValidationOrderProductRequestDTO(
            1L,
            1L
    )

    private val couponValidationOrderRequestDTO = CouponValidationOrderRequestDTO(
            1L,
            1L,
            Collections.singletonList(couponValidationOrderProductRequestDTO)
    )

    private val couponValidationOriginRequestDTO = CouponValidationOriginRequestDTO(
            1L,
            "ip",
            1L,
            "platformVersion",
            1L,
            "America/Bogota"
    )

    private val couponValidationPaymentRequestDTO = CouponValidationPaymentRequestDTO(
            1L,
            BigDecimal.valueOf(8900.0)
    )

    private val couponValidationUserRequestDTO = CouponValidationUserRequestDTO(
            1L,
            "User"
    )

    val couponValidationRequestDTO = CouponValidationRequestDTO(
            code = "COUPON-TEST",
            order = couponValidationOrderRequestDTO,
            origin = couponValidationOriginRequestDTO,
            payment = couponValidationPaymentRequestDTO,
            user = couponValidationUserRequestDTO
    )
}