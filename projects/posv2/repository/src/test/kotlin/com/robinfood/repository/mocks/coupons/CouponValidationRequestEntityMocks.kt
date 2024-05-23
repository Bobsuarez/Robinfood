package com.robinfood.repository.mocks.coupons

import com.robinfood.core.entities.coupons.CouponValidationOrderProductRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationOrderRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationOriginRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationPaymentRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationUserRequestEntity
import java.math.BigDecimal
import java.util.Collections

class CouponValidationRequestEntityMocks {

    private val couponValidationOrderProductRequestEntity = CouponValidationOrderProductRequestEntity(
            1L,
            1L
    )

    private val couponValidationOrderRequestEntity = CouponValidationOrderRequestEntity(
            1L,
            1L,
            Collections.singletonList(couponValidationOrderProductRequestEntity)
    )

    private val couponValidationOriginRequestEntity = CouponValidationOriginRequestEntity(
            1L,
            "ip",
            1L,
            "platformVersion",
            1L,
            "America/Bogota"
    )

    private val couponValidationPaymentRequestEntity = CouponValidationPaymentRequestEntity(
            1L,
            BigDecimal.valueOf(8900.0)
    )

    private val couponValidationUserRequestEntity = CouponValidationUserRequestEntity(
            1L
    )

    val couponValidationRequestEntity = CouponValidationRequestEntity(
            code = "COUPON-TEST",
            order = couponValidationOrderRequestEntity,
            origin = couponValidationOriginRequestEntity,
            payment = couponValidationPaymentRequestEntity,
            user = couponValidationUserRequestEntity
    )
}