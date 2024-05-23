package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO
import com.robinfood.core.entities.OrderCouponEntity
import com.robinfood.core.entities.transactionrequest.TransactionRequestEntity
import java.math.BigDecimal
import java.util.Collections

fun TransactionRequestDTO.toTransactionRequestEntity(): TransactionRequestEntity {

    var couponEntities: List<OrderCouponEntity> = Collections.emptyList()

    if (coupons != null) {
        couponEntities = coupons.map { orderCouponDTO ->  orderCouponDTO.toOrderCouponEntity() }
    }

    return TransactionRequestEntity(
            buyer = buyer?.toBuyerEntity(),
            coupons = couponEntities,
            company = company.toCompanyEntity(),
            co2Total = co2Total ?: BigDecimal.ZERO,
            device = device.toDeviceEntity(),
            discounts = discounts.map { discountDTO -> discountDTO.toDiscountEntity() },
            flowId = flowId,
            id = id,
            delivery = integrationData?.toIntegrationDataEntity(),
            orders = orders.map { orderDTO -> orderDTO.toOrderEntity() },
            origin = origin.toOriginEntity(),
            paymentMethods = paymentMethods.map { paymentMethodDTO -> paymentMethodDTO.toPaymentMethodEntity() },
            paymentsPaid = paymentsPaid.map { paymentMethodDTO -> paymentMethodDTO.toPaymentMethodEntity() },
            total = total,
            totalPaidPayments = totalPaidPayments,
            updateOrder = updateOrder,
            user = user.toTransactionUserEntity(),
            uuid = uuid
    )
}