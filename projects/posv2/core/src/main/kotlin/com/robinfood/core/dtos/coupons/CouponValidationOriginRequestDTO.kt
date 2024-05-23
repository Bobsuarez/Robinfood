package com.robinfood.core.dtos.coupons

import javax.validation.constraints.NotNull

data class CouponValidationOriginRequestDTO (

        @NotNull(message = "Company id should not be null")
        val companyId: Long,

        @NotNull(message = "IP should not be null")
        val ip: String,

        @NotNull(message = "Platform id should not be null")
        val platformId: Long,

        @NotNull(message = "Platform version should not be null")
        val platformVersion: String,

        @NotNull(message = "Store id should not be null")
        val storeId: Long,

        @NotNull(message = "timezone should not be null")
        val timezone: String
)