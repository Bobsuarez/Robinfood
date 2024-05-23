package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.core.dtos.menu.response.MenuHallProductResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

/**
 * Implementation of IGetMenuProductUseCase
 */
open class GetMenuProductUseCase(
        private val menuRepository: IMenuRepository
): IGetMenuProductUseCase {

    override suspend fun invoke(
            token: String,
            countryId: Long,
            brandId: Long,
            flowId: Long,
            storeId: Long,
            platformId: Long?,
            productIds: List<Long>
    ): Result<List<MenuHallProductResponseDTO>> {
        
        return coroutineScope {
            val resultProducts = productIds.map { productId: Long ->
                async {
                    menuRepository.getProductDetail(
                            token,
                            countryId,
                            brandId,
                            flowId,
                            storeId,
                            platformId,
                            productId
                    )
                }
            }.awaitAll()

            val menuResult = mutableListOf<MenuHallProductResponseDTO>()

            for (resultProduct in resultProducts) {
                when(resultProduct) {
                    is Result.Success ->  menuResult.add(resultProduct.data)
                    is Result.Error -> throw ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            resultProduct.exception.localizedMessage
                    )
                }
            }
            Result.Success(menuResult)
        }
    }
}