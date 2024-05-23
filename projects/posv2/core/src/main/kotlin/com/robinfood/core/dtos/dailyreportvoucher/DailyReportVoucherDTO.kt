package com.robinfood.core.dtos.dailyreportvoucher

import java.io.InputStream
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

data class DailyReportVoucherDTO private constructor(
    val datePrint: String?,
    val listCategoryTitle: JRBeanCollectionDataSource?,
    val listPaymenTitle: JRBeanCollectionDataSource?,
    val subReportCategoryTitle: InputStream?,
    val subReportPaymentTitle: InputStream?
) {
    class Builder(
        private var datePrint: String? = null,
        private var listCategoryTitle: JRBeanCollectionDataSource? = null,
        private var listPaymenTitle: JRBeanCollectionDataSource? = null,
        private var subReportCategoryTitle: InputStream? = null,
        private var subReportPaymentTitle: InputStream? = null
    ) {
        fun datePrint(datePrint: String) = apply {
            this.datePrint = datePrint
        }

        fun subReportCategoryTitle(subReportCategoryTitle: InputStream) = apply {
            this.subReportCategoryTitle = subReportCategoryTitle
        }

        fun listCategoryTitle(listCategoryTitle: JRBeanCollectionDataSource) = apply {
            this.listCategoryTitle = listCategoryTitle
        }

        fun subReportPaymentTitle(subReportPaymentTitle: InputStream) = apply {
            this.subReportPaymentTitle = subReportPaymentTitle
        }

        fun listPaymenTitle(listPaymenTitle: JRBeanCollectionDataSource) = apply {
            this.listPaymenTitle = listPaymenTitle
        }

        fun build(): DailyReportVoucherDTO {
            return DailyReportVoucherDTO(
                datePrint, listCategoryTitle, listPaymenTitle, subReportCategoryTitle,
                subReportPaymentTitle
            )
        }
    }
}