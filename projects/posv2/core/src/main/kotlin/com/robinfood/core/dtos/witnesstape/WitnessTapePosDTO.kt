package com.robinfood.core.dtos.witnesstape

data class WitnessTapePosDTO(
    val box: String?,
    val finalNumber: String?,
    val initialNumber: String?,
    val posId: Long?,
    val prefix: String?,
    val totalAnnulment: Int?,
    val totalDocEquivalent: Int?
) {
    class Builder(
        private var box: String? = null,
        private var finalNumber: String? = null,
        private var initialNumber: String? = null,
        private var posId: Long? = null,
        private var prefix: String? = null,
        private var totalAnnulment: Int? = null,
        private var totalDocEquivalent: Int? = null
    ) {
        fun box(box: String) = apply { this.box = box }
        fun finalNumber(finalNumber: String) = apply { this.finalNumber = finalNumber }
        fun initialNumber(initialNumber: String) = apply { this.initialNumber = initialNumber }
        fun posId(posId: Long) = apply { this.posId = posId }
        fun prefix(prefix: String) = apply { this.prefix = prefix }
        fun totalAnnulment(totalAnnulment: Int) = apply { this.totalAnnulment = totalAnnulment }
        fun totalDocEquivalent(totalDocEquivalent: Int) = apply { this.totalDocEquivalent = totalDocEquivalent }

        fun build(): WitnessTapePosDTO {
            return WitnessTapePosDTO(
                box, finalNumber, initialNumber, posId, prefix, totalAnnulment, totalDocEquivalent
            )
        }
    }
}
