package com.robinfood.core.dtos.witnesstape

import java.io.InputStream
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

data class WitnessTapeTransformDTO(
    val businessName: String?,
    val businessNit: String?,
    val createAt: String?,
    val datePrint: String?,
    val listOrderDet: JRBeanCollectionDataSource?,
    val listPosDet: JRBeanCollectionDataSource?,
    val storeAddress: String?,
    val storeName: String?,
    val subReportDetail: InputStream?,
    val subReportPos: InputStream?
) {
    class Builder(
        private var businessName: String? = null,
        private var businessNit: String? = null,
        private var createAt: String? = null,
        private var datePrint: String? = null,
        private var listOrderDet: JRBeanCollectionDataSource? = null,
        private var listPosDet: JRBeanCollectionDataSource? = null,
        private var storeAddress: String? = null,
        private var storeName: String? = null,
        private var subReportDetail: InputStream? = null,
        private var subReportPos: InputStream? = null
    ) {
        fun businessName(businessName: String?) = apply { this.businessName = businessName }
        fun businessNit(businessNit: String?) = apply { this.businessNit = businessNit }
        fun createAt(createAt: String) = apply { this.createAt = createAt }
        fun datePrint(datePrint: String) = apply { this.datePrint = datePrint }
        fun listOrderDet(listOrderDet: JRBeanCollectionDataSource) = apply { this.listOrderDet = listOrderDet }
        fun listPosDet(listPosDet: JRBeanCollectionDataSource) = apply { this.listPosDet = listPosDet }
        fun storeAddress(storeAddress: String?) = apply { this.storeAddress = storeAddress }
        fun storeName(storeName: String?) = apply { this.storeName = storeName }
        fun subReportDetail(subReportDetail: InputStream) = apply { this.subReportDetail = subReportDetail }
        fun subReportPos(subReportPos: InputStream) = apply { this.subReportPos = subReportPos }

        fun build(): WitnessTapeTransformDTO {
            return WitnessTapeTransformDTO(
                businessName,
                businessNit,
                createAt,
                datePrint,
                listOrderDet,
                listPosDet,
                storeAddress,
                storeName,
                subReportDetail,
                subReportPos
            )
        }
    }
}
