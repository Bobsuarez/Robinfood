package com.robinfood.app.usecases.getstoredeliveryplatforms

import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves the store's delivery platforms
 */
interface IGetStoreDeliveryPlatformsUseCase {

    /**
     * Retrieves store's delivery platforms
     * [page] the page the client needs of the current pagination
     * [size] the number of results per page
     * [storeId] store identification to ask for delivery platforms
     * [token] the authorization token
     * @return the active delivery platforms for the store
     */
    suspend fun invoke(
        page: Int?,
        size: Int?,
        storeId: Long,
        token: String
    ): Result<List<StoreDeliveryPlatformDTO>>
}
