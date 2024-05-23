package com.robinfood.app.controllers.coupons

import com.robinfood.app.usecases.validatecoupon.IValidateCouponUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.COUPONS
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.coupons.CouponValidationRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
import com.robinfood.core.enums.Result
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping(API_V1 + COUPONS)
class CouponsController(
        private val validateCouponUseCase: IValidateCouponUseCase
): ICouponsController {
    /**
     * Sends request to validate a coupon
     *
     * [couponValidationRequestDTO] The validation coupon request
     * [httpServletRequest] the authentication token to be used
     * @return The coupon validation response
     */
    @PostMapping("/validate")
    override suspend fun validateCoupon(
            @Valid @RequestBody couponValidationRequestDTO: CouponValidationRequestDTO,
            httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<CouponValidationResponseDTO>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        return when(val couponValidation = validateCouponUseCase.invoke(token, couponValidationRequestDTO)) {
            is Result.Error -> ResponseEntity(
                    ApiResponseDTO(couponValidation.exception.localizedMessage),
                    couponValidation.httpStatus
            )
            is Result.Success -> ResponseEntity(
                    ApiResponseDTO(couponValidation.data, "Coupon validated successfully"),
                    HttpStatus.OK
            )
        }
    }
}