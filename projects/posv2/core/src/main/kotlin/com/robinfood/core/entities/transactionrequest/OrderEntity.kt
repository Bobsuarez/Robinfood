package com.robinfood.core.entities.transactionrequest

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderEntity(
    val billingResolutionId: Long,
    val brand: OrderBrandEntity,
    val couponCriteriaInfo: Map<String, Any>,
    val coupons: List<CouponEntity>,
    val co2Total: BigDecimal,
    val deliveryTypeId: Long,
    val discounts: List<DiscountEntity>,
    val finalProducts: List<FinalProductEntity>,
    val flags: FlagsEntity?,
    val flowId: Long,
    val id: Long? = null,
    val notes: String,
    val origin: OriginEntity,
    val paymentModelId: Long,
    val services: List<ServiceEntity>,
    val store: StoreEntity,
    val thirdParty: ThirdPartyEntity?,
    val taxCriteria: Map<String, Any>,
    val total: BigDecimal,
    val uuid: String? = null
)
