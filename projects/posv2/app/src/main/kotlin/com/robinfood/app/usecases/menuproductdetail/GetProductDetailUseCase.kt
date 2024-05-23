package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.enums.Result
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class GetProductDetailUseCase(
        private val getMenuProductUseCase: IGetMenuProductUseCase,
        private val groupProductsBySizeIdUseCase: IGroupProductsBySizeIdUseCase
): IGetProductDetailUseCase {

    override suspend fun invoke(
            token: String,
            countryId: Long,
            brandId: Long,
            flowId: Long,
            storeId: Long,
            platformId: Long?,
            productIds: List<Long>
    ): Result<MenuProductDetailDTO> {
        val resultProductDetails = when (
            val resultProductDetails = getMenuProductUseCase.invoke(
                    token,
                    countryId,
                    brandId,
                    flowId,
                    storeId,
                    platformId,
                    productIds
            )
        ) {
            is Result.Success -> resultProductDetails.data
            is Result.Error -> throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    resultProductDetails.exception.localizedMessage
            )
        }

        return when (val resultGroupedProductDetails = groupProductsBySizeIdUseCase.invoke(token, resultProductDetails)) {
            is Result.Success -> {
                Result.Success(resultGroupedProductDetails.data)
            }
            is Result.Error -> throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    resultGroupedProductDetails.exception.localizedMessage
            )
        }
    }
}