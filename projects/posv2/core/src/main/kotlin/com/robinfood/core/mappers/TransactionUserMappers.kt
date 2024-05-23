package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.TransactionUserDTO
import com.robinfood.core.entities.transactionrequest.TransactionUserEntity

fun TransactionUserDTO.toTransactionUserEntity(): TransactionUserEntity {
    return TransactionUserEntity(
        email,
        firstName,
        id,
        lastName,
        mobile,
        phoneCode
    )
}