package com.robinfood.app.controllers.menu

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import com.robinfood.core.dtos.menu.MenuDTO
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest

/**
 * Exposes the API that handles all data related to menu
 */
@Tag(name = "Menu", description = "Requests for menu related data")
interface IMenuController {

    /**
     * Retrieves the menu based on the following query params
     * [countryId] the store country identification
     * [flowId] flow identification
     * [httpServletRequest] the authentication token to be used
     * [storeId] the store identification to get menu
     * @return the active menu for the store
     */
    suspend fun getMenu(
        countryId: Long,
        flowId: Long,
        httpServletRequest: HttpServletRequest,
        storeId: Long
    ): ResponseEntity<ApiResponseDTO<MenuDTO>>

    /**
     * Sends request to get products detail
     * [brandId] brand identifier of the product
     * [countryId] country identifier of the product
     * [finalProductIds] the final product identifiers
     * [flowId] flow identifier of the product
     * [httpServletRequest] the authentication token to be used
     * [platformId] platform identifier of the product
     * [storeId] store identifier of the product
     * @return The product object with the detail
     */
    @Operation(summary = "Sends a request to get products detail")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "This Sends a request to get products detail")])
    suspend fun productDetail(
            brandId: Long,
            countryId: Long,
            finalProductIds: List<Long>,
            flowId: Long,
            httpServletRequest: HttpServletRequest,
            platformId: Long?,
            storeId: Long
    ): ResponseEntity<ApiResponseDTO<MenuProductDetailDTO>>
}