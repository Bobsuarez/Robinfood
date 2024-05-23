package com.robinfood.core.dtos.dailyreportvoucher

import java.io.InputStream
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

data class CategoryTitleDTO private constructor(
    val businessName: String?,
    val businessNit: String?,
    val canceledAmount: String?,
    val createAt: String?,
    val current: Int?,
    val endDate: String?,
    val finalNumber: String?,
    val id: Long?,
    val initialDate: String?,
    val initialNumber: String?,
    val name: String?,
    var posId: Long?,
    var posType: String?,
    val prefix: String?,
    val quantityEquivalentDocs: String?,
    val statusId: Long?,
    val storeAddress: String?,
    val storeId: Long?,
    val storeName: String?,
    val typeDocument: String?,
    val subReportCategory: InputStream?,
    val listCategoryDet: JRBeanCollectionDataSource?
) {

    class Builder(
        private var businessName: String? = null,
        private var businessNit: String? = null,
        private var canceledAmount: String? = null,
        private var createAt: String? = null,
        private var current: Int? = null,
        private var endDate: String? = null,
        private var finalNumber: String? = null,
        private var id: Long? = null,
        private var initialDate: String? = null,
        private var initialNumber: String? = null,
        private var name: String? = null,
        private var posId: Long? = null,
        private var posType: String? = null,
        private var prefix: String? = null,
        private var quantityEquivalentDocs: String? = null,
        private var statusId: Long? = null,
        private var storeAddress: String? = null,
        private var storeId: Long? = null,
        private var storeName: String? = null,
        private var typeDocument: String? = null,
        private var subReportCategory: InputStream? = null,
        private var listCategoryDet: JRBeanCollectionDataSource? = null

    ) {
        fun businessName(businessName: String?) = apply { this.businessName = businessName }
        fun businessNit(businessNit: String?) = apply { this.businessNit = businessNit }
        fun canceledAmount(canceledAmount: String) = apply { this.canceledAmount = canceledAmount }
        fun createAt(createAt: String) = apply { this.createAt = createAt }
        fun current(current: Int) = apply { this.current = current }
        fun endDate(endDate: String) = apply { this.endDate = endDate }
        fun finalNumber(finalNumber: String) = apply { this.finalNumber = finalNumber }
        fun id(id: Long) = apply { this.id = id }
        fun initialDate(initialDate: String) = apply { this.initialDate = initialDate }
        fun initialNumber(initialNumber: String) = apply { this.initialNumber = initialNumber }
        fun name(name: String) = apply { this.name = name }
        fun posId(posId: Long) = apply { this.posId = posId }
        fun posType(posType: String) = apply { this.posType = posType }
        fun prefix(prefix: String) = apply { this.prefix = prefix }
        fun quantityEquivalentDocs(quantityEquivalentDocs: String) =
            apply { this.quantityEquivalentDocs = quantityEquivalentDocs }
        fun statusId(statusId: Long) = apply { this.statusId = statusId }
        fun storeAddress(storeAddress: String?) = apply { this.storeAddress = storeAddress }
        fun storeId(storeId: Long) = apply { this.storeId = storeId }
        fun storeName(storeName: String?) = apply { this.storeName = storeName }
        fun typeDocument(typeDocument: String) = apply { this.typeDocument = typeDocument }
        fun subReportCategory(subReportCategory: InputStream) = apply { this.subReportCategory = subReportCategory }
        fun listCategoryDet(listCategoryDet: JRBeanCollectionDataSource) =
            apply { this.listCategoryDet = listCategoryDet }

        fun build(): CategoryTitleDTO {
            return CategoryTitleDTO(
                businessName, businessNit, canceledAmount, createAt, current, endDate,
                finalNumber, id, initialDate, initialNumber, name, posId, posType, prefix, quantityEquivalentDocs,
                statusId, storeAddress,
                storeId, storeName, typeDocument, subReportCategory, listCategoryDet
            )
        }
    }

}