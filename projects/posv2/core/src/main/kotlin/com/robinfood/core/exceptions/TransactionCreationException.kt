package com.robinfood.core.exceptions

import org.springframework.http.HttpStatus

class TransactionCreationException(
        val data: Any? = null,
        val description: String? = null,
        val status: HttpStatus
) : Exception(description)