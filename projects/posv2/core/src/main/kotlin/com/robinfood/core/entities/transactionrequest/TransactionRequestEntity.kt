package com.robinfood.core.entities.transactionrequest

import com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_VALUE
import com.robinfood.core.entities.OrderCouponEntity
import java.math.BigDecimal

data class TransactionRequestEntity(
    val buyer: BuyerEntity?,
    val coupons: List<OrderCouponEntity>,
    val company: CompanyEntity,
    val co2Total: BigDecimal,
    val device: DeviceEntity,
    val discounts: List<DiscountEntity>,
    val flowId: Long,
    val id: Long? = null,
    val delivery: IntegrationDataEntity?,
    val orders: List<OrderEntity>,
    val origin: OriginEntity,
    val paid: Boolean = !DEFAULT_BOOLEAN_VALUE,
    val paymentMethods: List<PaymentMethodEntity>,
    val paymentsPaid: List<PaymentMethodEntity>,
    val total: BigDecimal,
    val totalPaidPayments: BigDecimal,
    val updateOrder: Boolean,
    val user: TransactionUserEntity,
    val uuid: String? = null
)
