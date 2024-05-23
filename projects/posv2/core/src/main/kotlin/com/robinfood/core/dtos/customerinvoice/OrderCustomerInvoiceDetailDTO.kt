package com.robinfood.core.dtos.customerinvoice

data class OrderCustomerInvoiceDetailDTO private constructor(
    val orderAddiction: String?,
    val orderDetail: String?
) {
    class Builder(
        private var orderAddiction: String? = null,
        private var orderDetail: String? = null
    ) {
        fun orderAddiction(orderAddiction: String) = apply { this.orderAddiction = orderAddiction }
        fun orderDetail(orderDetail: String) = apply { this.orderDetail = orderDetail }

        fun build(): OrderCustomerInvoiceDetailDTO {
            return OrderCustomerInvoiceDetailDTO(
                orderAddiction, orderDetail
            )
        }
    }
}
