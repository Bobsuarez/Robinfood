package com.robinfood.core.dtos.coupons

import javax.validation.Valid
import javax.validation.constraints.NotNull

data class CouponValidationOrderRequestDTO (

        @NotNull(message = "Order id should not be null")
        val orderId: Long,

        @NotNull(message = "Flow id should not be null")
        val flowId: Long,

        @NotNull
        @Valid
        val products: List<CouponValidationOrderProductRequestDTO>
)