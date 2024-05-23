package com.robinfood.core.dtos

import java.math.BigDecimal

data class OrderDetailDTO (
        val buyer: OrderBuyerDTO,
        val co2Total: BigDecimal,
        var crossSellingCategoryToOffer: Long,
        var crossSellingCategoriesToOffer: List<Long>,
        val currency: String,
        val coupons: List<OrderCouponDTO>,
        val discount: BigDecimal,
        val discounts: List<OrderDetailDiscountDTO>,
        val electronicBill: ElectronicBillDTO?,
        val id: Long,
        val invoice: String,
        val flowId: Long,
        val orderNumber: String,
        val orderUuid: String?,
        val originId: Long,
        val originName: String,
        val paymentMethods: List<OrderDetailPaymentMethodDTO>,
        val products: List<OrderDetailProductDTO>,
        var showCrossSelling: Boolean = false,
        var showCrossSellingTopping: Boolean = false,
        var mixedFlowCrossSelling: Boolean = false,
        val store: OrderDetailStoreDTO,
        val subtotal: BigDecimal,
        val tax: BigDecimal,
        val total: BigDecimal,
        val transactionId: Long?,
        val transactionUuid: String?,
        val user: OrderUserDTO
)