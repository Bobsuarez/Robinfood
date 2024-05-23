package com.robinfood.core.entities.posresolutionsequence

data class PosResolutionSequenceEntity (
    val cancelledInvoices: Long,
    val current: Int,
    val effectiveInvoices: Long,
    val endDate: String,
    val endNumber: Int,
    val id: Long?,
    val initialDate: String,
    val name: String,
    val posId: Long,
    val prefix: String,
    val startNumber: Int,
    val statusId: Long,
    val storeId: Long,
    val typeDocument: String
)