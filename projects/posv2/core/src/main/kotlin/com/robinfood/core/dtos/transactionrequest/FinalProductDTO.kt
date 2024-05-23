package com.robinfood.core.dtos.transactionrequest

import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class FinalProductDTO (

    @NotNull
    @Valid
    val article: ArticleDTO,

    @NotNull
    @Valid
    val brand: BrandDTO,

    @Min(0)
    val co2Total: BigDecimal?,

    @NotNull
    @Size
    @Valid
    val discounts: List<DiscountDTO>,

    @NotNull
    @Size
    @Valid
    val groups: List<GroupDTO>,

    @NotNull
    @Positive
    val id: Long,

    @NotBlank
    val image: String,

    @NotBlank
    val name: String,

    @NotNull
    @Positive
    val price: BigDecimal,

    @NotNull
    @Positive
    val quantity: Int,

    @NotNull
    @Size
    @Valid
    val removedPortions: List<RemovedPortionDTO>,

    @NotNull
    @Valid
    val size: SizeDTO,

    @NotBlank
   val sku: String,

    @Size
    @Valid
    val taxes: List<TaxDTO>,

    @NotNull
    @Positive
    val totalPrice: BigDecimal
)