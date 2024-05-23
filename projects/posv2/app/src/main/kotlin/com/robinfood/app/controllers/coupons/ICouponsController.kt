package com.robinfood.app.controllers.coupons

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.coupons.CouponValidationRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest

/**
 * Exposes the API that handles all data related to coupons
 */
@Tag(name = "Coupons", description = "Requests for coupons related data")
interface ICouponsController {

    /**
     * Sends request to validate a coupon
     *
     * [couponValidationRequestDTO] The validation coupon request
     * [httpServletRequest] the authentication token to be used
     * @return The coupon validation response
     */
    @Operation(summary = "Sends a request to validate a coupon")
    @ApiResponses(
            ApiResponse(
                    responseCode = "200",
                    description = "This sends a request to validate a coupon",
                    content = [(Content(
                            array = ArraySchema(schema = Schema(implementation = CouponValidationResponseDTO::class)),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    ))]
            )
    )
    suspend fun validateCoupon(
        couponValidationRequestDTO: CouponValidationRequestDTO,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<CouponValidationResponseDTO>>
}