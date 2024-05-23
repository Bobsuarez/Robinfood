package com.robinfood.core.dtos.customerinvoice

import java.io.InputStream

data class OrderCustomerInvoiceDTO private constructor(
    val advertisingSpace: String?,
    val barCodeImage: String?,
    val barCodeInfo: String?,
    val brandImage: String?,
    val companyAddress: String?,
    val companyName: String?,
    val customerName: String?,
    val customerPhone: String?,
    val dianResolutionInfo: String?,
    val discounts: String?,
    val ie: String?,
    val invoice: String?,
    val invoiceDate: String?,
    val invoiceInfo: String?,
    val invoiceOrder: String?,
    val isWalkers: Boolean?,
    val nit: String?,
    val notesInfo: String?,
    val order: String?,
    val paymentName: String?,
    val paymentValue: String?,
    val qrImage: InputStream?,
    val qrInfo: String?,
    val sat: String?,
    val storeAddress: String?,
    val storeEmail: String?,
    val storeName: String?,
    val subTotal: String?,
    val taxes: String?,
    val total: String?
) {
    class Builder(
        private var advertisingSpace: String? = null,
        private var barCodeImage: String? = null,
        private var barCodeInfo: String? = null,
        private var brandImage: String? = null,
        private var companyAddress: String? = null,
        private var companyName: String? = null,
        private var customerName: String? = null,
        private var customerPhone: String? = null,
        private var dianResolutionInfo: String? = null,
        private var discounts: String? = null,
        private var ie: String? = null,
        private var invoice: String? = null,
        private var invoiceDate: String? = null,
        private var invoiceInfo: String? = null,
        private var invoiceOrder: String? = null,
        private var isWalkers: Boolean? = null,
        private var nit: String? = null,
        private var notesInfo: String? = null,
        private var order: String? = null,
        private var paymentName: String? = null,
        private var paymentValue: String? = null,
        private var qrImage: InputStream? = null,
        private var qrInfo: String? = null,
        private var sat: String? = null,
        private var storeAddress: String? = null,
        private var storeEmail: String? = null,
        private var storeName: String? = null,
        private var subTotal: String? = null,
        private var taxes: String? = null,
        private var total: String? = null
    ) {
        fun advertisingSpace(advertisingSpace: String?) = apply { this.advertisingSpace = advertisingSpace }
        fun barCodeImage(barCodeImage: String?) = apply { this.barCodeImage = barCodeImage }
        fun barCodeInfo(barCodeInfo: String?) = apply { this.barCodeInfo = barCodeInfo }
        fun brandImage(brandImage: String) = apply { this.brandImage = brandImage }
        fun companyAddress(companyAddress: String) = apply { this.companyAddress = companyAddress }
        fun companyName(companyName: String) = apply { this.companyName = companyName }
        fun customerName(customerName: String) = apply { this.customerName = customerName }
        fun customerPhone(customerPhone: String) = apply { this.customerPhone = customerPhone }
        fun dianResolutionInfo(dianResolutionInfo: String) = apply { this.dianResolutionInfo = dianResolutionInfo }
        fun discounts(discounts: String) = apply { this.discounts = discounts }
        fun ie(ie: String?) = apply { this.ie = ie }
        fun invoice(invoice: String) = apply { this.invoice = invoice }
        fun invoiceDate(invoiceDate: String) = apply { this.invoiceDate = invoiceDate }
        fun invoiceInfo(invoiceInfo: String?) = apply { this.invoiceInfo = invoiceInfo }
        fun invoiceOrder(invoiceOrder: String) = apply { this.invoiceOrder = invoiceOrder }
        fun isWalkers(isWalkers: Boolean) = apply { this.isWalkers = isWalkers }
        fun nit(nit: String) = apply { this.nit = nit }
        fun notesInfo(notesInfo: String) = apply { this.notesInfo = notesInfo }
        fun order(order: String) = apply { this.order = order }
        fun paymentName(paymentName: String) = apply { this.paymentName = paymentName }
        fun paymentValue(paymentValue: String) = apply { this.paymentValue = paymentValue }
        fun qrImage(qrImage: InputStream?) = apply { this.qrImage = qrImage }
        fun qrInfo(qrInfo: String) = apply { this.qrInfo = qrInfo }
        fun storeAddress(storeAddress: String) = apply { this.storeAddress = storeAddress }
        fun storeEmail(storeEmail: String?) = apply { this.storeEmail = storeEmail }
        fun storeName(storeName: String) = apply { this.storeName = storeName }
        fun subTotal(subTotal: String) = apply { this.subTotal = subTotal }
        fun taxes(taxes: String) = apply { this.taxes = taxes }
        fun total(total: String) = apply { this.total = total }

        fun build(): OrderCustomerInvoiceDTO {
            return OrderCustomerInvoiceDTO(
                advertisingSpace,
                barCodeImage,
                barCodeInfo,
                brandImage,
                companyAddress,
                companyName,
                customerName,
                customerPhone,
                dianResolutionInfo,
                discounts,
                ie,
                invoice,
                invoiceDate,
                invoiceInfo,
                invoiceOrder,
                isWalkers,
                nit,
                notesInfo,
                order,
                paymentName,
                paymentValue,
                qrImage,
                qrInfo,
                sat,
                storeAddress,
                storeEmail,
                storeName,
                subTotal,
                taxes,
                total
            )
        }
    }
}
