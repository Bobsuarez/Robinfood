package com.robinfood.app.controllers.menu

import com.robinfood.app.usecases.getmenu.IGetMenuUseCase
import com.robinfood.app.usecases.menuproductdetail.IGetProductDetailUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.MENU
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.menu.MenuDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.enums.Result
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest

/**
 * Implementation of 'IMenuController'
 */
@RestController
@RequestMapping(API_V1 + MENU)
class MenuController(
        private val getProductDetailUseCase: IGetProductDetailUseCase,
        private val getMenuUseCase: IGetMenuUseCase
) : IMenuController {

    @GetMapping("/product")
    override suspend fun productDetail(
            @RequestParam(value = "brand_id", required = true) brandId: Long,
            @RequestParam(value = "country_id", required = true) countryId: Long,
            @RequestParam(value = "final_product_ids", required = true) finalProductIds: List<Long>,
            @RequestParam(value = "flow_id", required = true) flowId: Long,
            httpServletRequest: HttpServletRequest,
            @RequestParam(value = "platform_id", required = false) platformId: Long?,
            @RequestParam(value = "store_id", required = true) storeId: Long
    ): ResponseEntity<ApiResponseDTO<MenuProductDetailDTO>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        return when (
            val resultProductDetails = getProductDetailUseCase.invoke(
                    token,
                    countryId,
                    brandId,
                    flowId,
                    storeId,
                    platformId,
                    finalProductIds
            )
        ) {
            is Result.Success -> ResponseEntity(ApiResponseDTO(resultProductDetails.data), HttpStatus.OK)
            is Result.Error -> throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    resultProductDetails.exception.localizedMessage
            )
        }
    }

    @GetMapping("/{storeId}")
    override suspend fun getMenu(
            @RequestParam(value = "countryId") countryId: Long,
            @RequestParam(value = "flowId") flowId: Long,
            httpServletRequest: HttpServletRequest,
            @PathVariable storeId: Long
    ): ResponseEntity<ApiResponseDTO<MenuDTO>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        val menuResult = getMenuUseCase.invoke(
                token,
                countryId,
                flowId,
                storeId
        )
        return when (menuResult) {
            is Result.Error -> ResponseEntity(
                    ApiResponseDTO(menuResult.exception.localizedMessage),
                    menuResult.httpStatus
            )
            is Result.Success -> ResponseEntity(ApiResponseDTO(menuResult.data, "Menu retrieved successfully"), HttpStatus.OK)
        }
    }
}