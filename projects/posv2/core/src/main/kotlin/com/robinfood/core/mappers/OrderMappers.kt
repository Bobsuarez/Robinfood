package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.OrderCreatedDTO
import com.robinfood.core.dtos.transactionrequest.OrderDTO
import com.robinfood.core.entities.OrderCreatedEntity
import com.robinfood.core.entities.transactionrequest.OrderEntity
import java.math.BigDecimal

fun OrderDTO.toOrderEntity(): OrderEntity {
    return OrderEntity(
        billingResolutionId = billingResolutionId,
        brand = brand.toOrderBrandEntity(),
        couponCriteriaInfo = couponCriteriaInfo,
        coupons = coupons.map { couponDTO -> couponDTO.toCouponEntity() },
        co2Total = co2Total ?: BigDecimal.ZERO,
        deliveryTypeId = deliveryTypeId,
        discounts = discounts.map { discountDTO -> discountDTO.toDiscountEntity() },
        finalProducts = finalProducts.map { finalProductDTO -> finalProductDTO.toFinalProductEntity() },
        flags = flags?.toFlagsEntity(),
        flowId = flowId,
        id = id,
        notes = notes,
        origin = origin.toOriginEntity(),
        paymentModelId = paymentModelId,
        services = services.map { serviceDTO -> serviceDTO.toServiceEntity() },
        store = store.toStoreEntity(),
        taxCriteria = taxCriteria,
        thirdParty = thirdParty?.toThirdPartyEntity(),
        total = total,
        uuid = uuid
    )
}

fun OrderCreatedEntity.toOrderCreatedDTO(): OrderCreatedDTO {
    return OrderCreatedDTO(
        discountPrice = discountPrice ?: BigDecimal.ZERO,
        id = id ?: DEFAULT_LONG_VALUE,
        orderInvoiceNumber = orderInvoiceNumber.orEmpty(),
        orderNumber = orderNumber.orEmpty(),
        subtotal = subtotal ?: BigDecimal.ZERO,
        taxPrice = taxPrice ?: BigDecimal.ZERO,
        total = total ?: BigDecimal.ZERO,
        uid = uid.orEmpty()
    )
}