package com.robinfood.core.dtos.dailyreportvoucher

import java.io.InputStream
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

data class PaymentMethodTitleDTO private constructor(
    val listPaymentDet: JRBeanCollectionDataSource?,
    val subReportPayment: InputStream?,
    val totalCompensation: Double?,
    val totalOwnIncome: Double?
) {
    class Builder(
        private var listPaymentDet: JRBeanCollectionDataSource? = null,
        private var subReportPayment: InputStream? = null,
        private var totalCompensation: Double? = null,
        private var totalOwnIncome: Double? = null
    ) {
        fun listPaymentDet(listPaymentDet: JRBeanCollectionDataSource) = apply { this.listPaymentDet = listPaymentDet }
        fun subReportPayment(subReportPayment: InputStream) = apply { this.subReportPayment = subReportPayment }
        fun totalCompensation(totalCompensation: Double) = apply { this.totalCompensation = totalCompensation }
        fun totalOwnIncome(totalOwnIncome: Double) = apply { this.totalOwnIncome = totalOwnIncome }

        fun build(): PaymentMethodTitleDTO {
            return PaymentMethodTitleDTO(
                listPaymentDet, subReportPayment, totalCompensation, totalOwnIncome
            )
        }
    }
}
