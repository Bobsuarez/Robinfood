package com.robinfood.app.controllers.giftcard

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.giftcard.ValidateGiftCardDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "Gift-Card", description = "Gift card validation request")
interface IValidateGiftCardController {

    /**
     * Sends request to get products detail
     * [displayTypeIds] the final display types ids identifiers
     */
    @Operation(summary = "Send a request to get display types ids validation")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "this service validates if the ids belong to a gift card")])
    suspend fun getValidationGiftCard(
            displayTypeIds: List<Long>
    ): ResponseEntity<ApiResponseDTO<ValidateGiftCardDTO>>
}