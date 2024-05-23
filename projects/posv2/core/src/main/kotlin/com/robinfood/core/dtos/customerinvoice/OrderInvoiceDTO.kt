package com.robinfood.core.dtos.customerinvoice

data class OrderInvoiceDTO(
    val additionalNotes: String,
    val brandId: Long,
    val brandLogo: String,
    val cashPaymentName: String,
    val cashPaymentValue: Double,
    val code: String,
    val codeCaja: String,
    val compensation: CompensationInvoiceDTO,
    val consCaja: String,
    val copyOrderCommand: String,
    val copyOrderTicket: String,
    val detail: DetailInvoiceDTO,
    val discount: Double,
    val externalOrderNumber: String,
    val franchiseName: String,
    val hasCompensation: Boolean,
    val id: Long,
    val infAddress: String,
    val infCity: String,
    val infNit: String,
    val isDelivery: Boolean,
    val isExternalDelivery: Boolean,
    val isWalkers: Boolean,
    val multiBrand: Boolean,
    val nameCommand: String,
    val nameCustomer: String,
    val orderBrandId: String,
    val orderNumber: String,
    val phoneCustomer: String,
    val paymentMethod: String,
    val prepaid: String,
    val prepaidName: String,
    val printType: Int,
    val prepaidValue: String,
    val products: List<ProductInvoiceDTO>,
    val resDateInit: String,
    val resDateEnd: String,
    val reception: Int,
    val resNumber: String,
    val resNumberInit: Int,
    val resNumberEnd: Int,
    val resTypeNumber: String,
    val services: List<ServicesInvoiceDTO>,
    val storeId: Long,
    val storeName: String,
    val subtotal: Double,
    val tax: Double,
    val toGo: Int,
    val total: Double,
    val totalToCollect: TotalToCollectInvoiceDTO,
    val totalToPay: TotalToPayInvoiceDTO,
    val typeIdent: String,
    val typeLabel: String,
    val uid: String,
    val userId: Long,
    val userLoyaltyStatus: Long,
    val wherePrinter: Int,
    val wifiVoucher: String
)