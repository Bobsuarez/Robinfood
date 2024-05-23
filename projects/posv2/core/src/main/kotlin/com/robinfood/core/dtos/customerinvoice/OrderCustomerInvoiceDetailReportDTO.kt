package com.robinfood.core.dtos.customerinvoice

import java.io.InputStream
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

data class OrderCustomerInvoiceDetailReportDTO private constructor(
    val orderCustomerInvoiceDetail: JRBeanCollectionDataSource?,
    val subReportProducts: InputStream?,
    val hasCompensation: Boolean?,
    val legendCompensation: String?,
    val listServicesOrder: JRBeanCollectionDataSource?,
    val subReportServices: InputStream?,
    val totalToCollect: String?
) {
    class Builder(
        private var orderCustomerInvoiceDetail: JRBeanCollectionDataSource? = null,
        private var subReportProducts: InputStream? = null,
        private var hasCompensation: Boolean? = null,
        private var legendCompensation: String? = null,
        private var listServicesOrder: JRBeanCollectionDataSource? = null,
        private var subReportServices: InputStream? = null,
        private var totalToCollect: String? = null
    ) {
        fun orderCustomerInvoiceDetail(orderCustomerInvoiceDetail: JRBeanCollectionDataSource) =
            apply {
                this.orderCustomerInvoiceDetail = orderCustomerInvoiceDetail
            }

        fun subReportProducts(subReportProducts: InputStream) =
            apply {
                this.subReportProducts = subReportProducts
            }

        fun hasCompensation(hasCompensation: Boolean) =
            apply {
                this.hasCompensation = hasCompensation
            }

        fun legendCompensation(legendCompensation: String) =
            apply {
                this.legendCompensation = legendCompensation
            }

        fun listServicesOrder(listServicesOrder: JRBeanCollectionDataSource) =
            apply {
                this.listServicesOrder = listServicesOrder
            }

        fun subReportServices(subReportServices: InputStream) =
            apply {
                this.subReportServices = subReportServices
            }

        fun totalToCollect(totalToCollect: String) =
            apply {
                this.totalToCollect = totalToCollect
            }

        fun build(): OrderCustomerInvoiceDetailReportDTO {
            return OrderCustomerInvoiceDetailReportDTO(
                orderCustomerInvoiceDetail, subReportProducts, hasCompensation, legendCompensation,
                listServicesOrder, subReportServices, totalToCollect
            )
        }
    }
}
