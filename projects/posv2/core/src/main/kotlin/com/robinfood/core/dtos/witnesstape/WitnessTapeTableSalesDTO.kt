package com.robinfood.core.dtos.witnesstape

data class WitnessTapeTableSalesDTO(
    var box: String?,
    val compensationCo2: Double?,
    val discounts: Double?,
    val docEquivalent: String?,
    val posId: Long?,
    var prefix: String?,
    val status: String?,
    val subTotal: Double?,
    val taxes: Double?,
    val total: Double?
) {
    class Builder(
        private var box: String? = null,
        private var compensationCo2: Double? = null,
        private var discounts: Double? = null,
        private var docEquivalent: String? = null,
        private var posId: Long? = null,
        private var prefix: String? = null,
        private var status: String? = null,
        private var subTotal: Double? = null,
        private var taxes: Double? = null,
        private var total: Double? = null
    ) {
        fun box(box: String) = apply { this.box = box }
        fun compensationCo2(compensationCo2: Double) = apply { this.compensationCo2 = compensationCo2 }
        fun discounts(discounts: Double) = apply { this.discounts = discounts }
        fun docEquivalent(docEquivalent: String) = apply { this.docEquivalent = docEquivalent }
        fun posId(posId: Long) = apply { this.prefix = prefix }
        fun prefix(prefix: String?) = apply { this.prefix = prefix }
        fun status(status: String) = apply { this.status = status }
        fun subTotal(subTotal: Double) = apply { this.subTotal = subTotal }
        fun taxes(taxes: Double) = apply { this.taxes = taxes }
        fun total(total: Double) = apply { this.total = total }

        fun build(): WitnessTapeTableSalesDTO {
            return WitnessTapeTableSalesDTO(
                box, compensationCo2, discounts, docEquivalent, posId, prefix, status, subTotal, taxes, total
            )
        }
    }
}
