package com.robinfood.app.controllers.giftcard

import com.robinfood.app.usecases.validategiftcard.IValidateGiftCardUseCase
import com.robinfood.core.constants.APIConstants
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.giftcard.ValidateGiftCardDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(APIConstants.API_V1 + APIConstants.GIFT_CARD_VALIDATION)
class ValidateGiftCardController(
        private val iValidateGiftCardUseCase: IValidateGiftCardUseCase
) : IValidateGiftCardController {

    @GetMapping
    override suspend fun getValidationGiftCard(
            @RequestParam(value = "displayTypeIds", required = true) displayTypeIds: List<Long>
    ): ResponseEntity<ApiResponseDTO<ValidateGiftCardDTO>> {

        val isEnableButton = iValidateGiftCardUseCase.invoke(displayTypeIds)
        return ResponseEntity(ApiResponseDTO(isEnableButton), HttpStatus.OK)
    }
}