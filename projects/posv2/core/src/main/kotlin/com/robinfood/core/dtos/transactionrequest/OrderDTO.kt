package com.robinfood.core.dtos.transactionrequest

import com.robinfood.core.dtos.OrderThirdPartyDTO
import com.robinfood.core.dtos.OrderThirdPartyRequestDTO
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class OrderDTO(

        @Positive
        val billingResolutionId: Long,

        @NotNull
        @Valid
        val brand: OrderBrandDTO,

        @NotNull
        val couponCriteriaInfo: Map<String, Any>,

        @NotNull
        @Size
        @Valid
        val coupons: List<CouponDTO>,

        @Min(0)
        val co2Total: BigDecimal?,

        @NotNull
        @Positive
        val deliveryTypeId: Long,

        @NotNull
        @Size
        @Valid
        val discounts: List<DiscountDTO>,

        @NotNull
        @Size(min = 1)
        @Valid
        val finalProducts: List<FinalProductDTO>,

        @Valid
        val flags: FlagsDTO?,

        @NotNull
        @Positive
        val flowId: Long,

        @Positive
        val id: Long? = null,

        @NotBlank
        val notes: String,

        @NotNull
        @Valid
        val origin: OriginDTO,

        @NotNull
        @Positive
        val paymentModelId: Long,

        @NotNull
        @Size
        @Valid
        val services: List<ServiceDTO>,

        @NotNull
        @Valid
        val store: StoreDTO,

        @NotNull
        val taxCriteria: Map<String, Any>,

        @Valid
        val thirdParty: OrderThirdPartyRequestDTO?,

        @NotNull
        @Positive
        val total: BigDecimal,

        var uuid: String?
)
