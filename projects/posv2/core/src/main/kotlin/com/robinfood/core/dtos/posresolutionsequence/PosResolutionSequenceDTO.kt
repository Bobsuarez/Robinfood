package com.robinfood.core.dtos.posresolutionsequence

data class PosResolutionSequenceDTO (
    val cancelledInvoices: Long,
    val current: Int,
    val effectiveInvoices: Long,
    val endDate: String,
    val endNumber: Int,
    val id: Long,
    val initialDate: String,
    val name: String?,
    var posId: Long,
    val prefix: String,
    val startNumber: Int,
    val statusId: Long,
    val storeId: Long,
    val typeDocument: String
)