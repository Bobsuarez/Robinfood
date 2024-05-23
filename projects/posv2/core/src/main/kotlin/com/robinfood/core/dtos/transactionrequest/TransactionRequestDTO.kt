package com.robinfood.core.dtos.transactionrequest

import com.robinfood.core.dtos.OrderCouponDTO
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class TransactionRequestDTO(

        val buyer: BuyerDTO?,

        @NotNull
        @Valid
        val company: CompanyDTO,

        @Valid
        val coupons: List<OrderCouponDTO>?,

        @Min(0)
        val co2Total: BigDecimal?,

        @NotNull
        @Valid
        val device: DeviceDTO,

        @NotNull
        @Valid
        val discounts: List<DiscountDTO>,

        @NotNull
        @Valid
        val flowId: Long,

        @Positive
        val id: Long? = null,

        @Valid
        val integrationData: IntegrationDataDTO?,

        @NotNull
        @Size
        @Valid
        var orders: List<OrderDTO>,

        @NotNull
        @Valid
        val origin: OriginDTO,

        @NotNull
        val paid: Boolean,

        @NotNull
        @Size
        @Valid
        val paymentMethods: List<PaymentMethodDTO>,

        @NotNull
        @Size
        @Valid
        val paymentsPaid: List<PaymentMethodDTO>,

        @Min(0)
        @NotNull
        val total: BigDecimal,

        @Min(0)
        @NotNull
        val totalPaidPayments: BigDecimal,

        @NotNull
        val updateOrder: Boolean,

        @NotNull
        @Valid
        val user: TransactionUserDTO,

        var uuid: String?
)