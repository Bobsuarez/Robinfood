package com.robinfood.core.entities.coupons

data class CouponValidationOriginRequestEntity (
        val companyId: Long?,
        val ip: String?,
        val platformId: Long?,
        val platformVersion: String?,
        val storeId: Long?,
        val timezone: String?
)