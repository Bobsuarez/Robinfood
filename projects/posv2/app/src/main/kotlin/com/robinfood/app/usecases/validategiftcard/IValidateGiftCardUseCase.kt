package com.robinfood.app.usecases.validategiftcard

import com.robinfood.core.dtos.giftcard.ValidateGiftCardDTO

interface IValidateGiftCardUseCase {

    /**
     * Sends a request to validate a giftCard
     * @param displayTypeIds list of display types to validate
     * @return ValidateGiftCardDTO object
     */
    suspend fun invoke(
        displayTypeIds: List<Long>
    ): ValidateGiftCardDTO
}