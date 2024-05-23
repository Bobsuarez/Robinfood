package com.robinfood.core.entities.transactionrequest

data class TransactionUserEntity (
    val email: String,
    val firstName: String,
    val id: Long,
    val lastName: String,
    val mobile: String,
    val phoneCode: String
)