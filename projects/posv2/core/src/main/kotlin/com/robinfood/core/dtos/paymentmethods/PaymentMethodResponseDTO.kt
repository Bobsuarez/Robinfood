package com.robinfood.core.dtos.paymentmethods

data class PaymentMethodResponseDTO(
        val id: Long,
        val image: String,
        val name: String,
        val originId: Long,
        val slugName: String
)