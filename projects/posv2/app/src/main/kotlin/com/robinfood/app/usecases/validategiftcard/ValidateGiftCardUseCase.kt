package com.robinfood.app.usecases.validategiftcard

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.giftcard.ValidateGiftCardDTO
import org.springframework.beans.factory.annotation.Value

class ValidateGiftCardUseCase : IValidateGiftCardUseCase {

    @Value("\${orders.displayTypes-giftCard}")
    val giftCardDisplayTypeIds: List<Long> = listOf()

    @Value("\${orders.paymentMethod-giftCard}")
    val giftCardProductMethodId: Long = DEFAULT_LONG_VALUE

    override suspend fun invoke(displayTypes: List<Long>): ValidateGiftCardDTO {
        val isExistId = giftCardDisplayTypeIds.any { displayTypes.contains(it) }
        return ValidateGiftCardDTO(!isExistId, giftCardProductMethodId)
    }
}