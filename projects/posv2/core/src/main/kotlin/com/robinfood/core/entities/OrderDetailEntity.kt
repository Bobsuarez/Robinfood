package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderDetailEntity(
        val buyer: OrderBuyerEntity?,
        val co2Total: BigDecimal?,
        val currency: String?,
        val coupons: List<OrderCouponEntity>?,
        val discount: BigDecimal?,
        val discounts: List<OrderDetailDiscountEntity>?,
        val electronicBill: ElectronicBillEntity?,
        val flowId: Long,
        val id: Long?,
        val invoice: String?,
        val orderNumber: String?,
        val orderUuid: String?,
        val originId: Long?,
        val originName: String?,
        val paymentMethods: List<OrderDetailPaymentMethodEntity>?,
        val products: List<OrderDetailProductEntity>,
        val storeId: Long?,
        val storeName: String?,
        val subtotal: BigDecimal?,
        val tax: BigDecimal?,
        val total: BigDecimal?,
        val transactionId: Long?,
        val transactionUuid: String?,
        val user: OrderUserEntity?
)
