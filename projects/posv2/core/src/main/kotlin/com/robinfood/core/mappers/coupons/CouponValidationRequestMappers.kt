package com.robinfood.core.mappers.coupons

import com.robinfood.core.dtos.coupons.CouponValidationOrderProductRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationOrderRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationOriginRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationPaymentRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationUserRequestDTO
import com.robinfood.core.entities.coupons.CouponValidationOrderProductRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationOrderRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationOriginRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationPaymentRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationUserRequestEntity

fun CouponValidationOrderProductRequestDTO.toCouponValidationOrderProductRequestEntity(): CouponValidationOrderProductRequestEntity {
    return CouponValidationOrderProductRequestEntity(
            brandId = brandId,
            id = id
    )
}

fun CouponValidationOrderRequestDTO.toCouponValidationOrderRequestEntity(): CouponValidationOrderRequestEntity {
    return CouponValidationOrderRequestEntity(
            orderId =  orderId,
            flowId = flowId,
            products = products.map {
                couponValidationProductDTO -> couponValidationProductDTO.toCouponValidationOrderProductRequestEntity()
            }
    )
}

fun CouponValidationOriginRequestDTO.toCouponValidationOriginRequestEntity(): CouponValidationOriginRequestEntity {
    return CouponValidationOriginRequestEntity(
            companyId = companyId,
            ip = ip,
            platformId = platformId,
            platformVersion = platformVersion,
            storeId = storeId,
            timezone = timezone
    )
}

fun CouponValidationPaymentRequestDTO.toCouponValidationPaymentRequestEntity(): CouponValidationPaymentRequestEntity {
    return CouponValidationPaymentRequestEntity(
            paymentMethodId = paymentMethodId,
            total = total
    )
}

fun CouponValidationUserRequestDTO.toCouponValidationUserRequestEntity(): CouponValidationUserRequestEntity {
    return CouponValidationUserRequestEntity(
            userId = userId
    )
}

fun CouponValidationRequestDTO.toCouponValidationRequestEntity(): CouponValidationRequestEntity {
    return CouponValidationRequestEntity(
            code = code,
            order = order.toCouponValidationOrderRequestEntity(),
            origin = origin.toCouponValidationOriginRequestEntity(),
            payment = payment.toCouponValidationPaymentRequestEntity(),
            user = user.toCouponValidationUserRequestEntity()
    )
}
