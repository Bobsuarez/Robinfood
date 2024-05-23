package com.robinfood.core.dtos.coupons

import javax.validation.constraints.NotNull

data class CouponValidationUserRequestDTO (
        @NotNull(message = "User id should not be null")
        val userId: Long,

        @NotNull(message = "User name should not be null")
        val name: String
)