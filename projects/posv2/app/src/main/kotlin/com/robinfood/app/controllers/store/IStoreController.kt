package com.robinfood.app.controllers.store

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest

/**
 * Exposes the API that handles all data related to store
 */
@Tag(name = "Store", description = "Requests for store related data")
interface IStoreController {

    /**
     * Retrieves the delivery platforms that belongs to store given
     * [httpServletRequest] the authentication token to be used
     * [page] page to request
     * [size] number of objects per page
     * [storeId] the store identification to get menu
     * @return the active delivery platforms for the store
     */
    suspend fun getDeliveryPlatforms(
        httpServletRequest: HttpServletRequest,
        page: Int?,
        size: Int?,
        storeId: Long
    ): ResponseEntity<ApiResponseDTO<List<StoreDeliveryPlatformDTO>>>
}
