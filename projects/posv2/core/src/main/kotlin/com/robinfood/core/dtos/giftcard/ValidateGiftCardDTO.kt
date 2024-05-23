package com.robinfood.core.dtos.giftcard

data class ValidateGiftCardDTO(
        var isEnableButton: Boolean?,
        val paymentMethodId: Long?
)