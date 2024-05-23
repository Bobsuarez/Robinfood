package com.robinfood.repository.menu

import com.robinfood.core.dtos.menu.MenuBrandDTO
import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.dtos.menu.response.MenuHallProductResponseDTO
import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.menu.toMenuBrandDTO
import com.robinfood.core.mappers.menu.toMenuBrandResponseDTO
import com.robinfood.core.mappers.menu.toMenuProductResponseDTO
import com.robinfood.core.mappers.menu.toMenuSuggestedPortionResponseDTO
import com.robinfood.network.api.MenuBCAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

/**
 * Implementation of IMenuRepository
 */
@Repository
class MenuRepository(
        private val menuBCAPI: MenuBCAPI
) : IMenuRepository {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun getStoreBrands(
            token: String,
            storeId: Long
    ): Result<List<MenuBrandResponseDTO>> {

        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                    call = {
                        menuBCAPI.getStoreBrands(
                                token,
                                storeId
                        ).callServices()
                    }
            )
            when (result) {
                is Result.Success -> {
                    val brandResponseEntities = result.data.data
                    if (brandResponseEntities == null) {
                        Result.Error(
                                OrchestratorException("Store brands array is null"),
                                HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val brandResponseDTOs = brandResponseEntities.mapNotNull { menuBrandResponseEntity ->
                            menuBrandResponseEntity.toMenuBrandResponseDTO()
                        }
                        Result.Success(brandResponseDTOs)
                    }
                }
                is Result.Error -> result
            }
        }
    }

    override suspend fun getStoreMenuBrand(
            token: String,
            menuBrandResponse: MenuBrandResponseDTO,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): Result<MenuBrandDTO> {

        log.info("Started MenuRepository method getStoreMenuBrand(). " +
                "Parameters menuBrandResponse = {}, countryId = {}, flowId = {}, storeId = {}",
                menuBrandResponse, countryId, flowId, storeId)

        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                    call = {
                        menuBCAPI.getStoreBrandMenu(
                                token,
                                menuBrandResponse.id,
                                countryId,
                                flowId,
                                storeId
                        ).callServices()
                    }
            )
            when (result) {
                is Result.Success -> {
                    val brandMenuResponseEntity = result.data.data
                    if (brandMenuResponseEntity == null) {
                        Result.Error(OrchestratorException("Menu is null"), HttpStatus.NOT_FOUND)
                    } else {
                        val menuBrandDTO = brandMenuResponseEntity.toMenuBrandDTO(menuBrandResponse)
                        if (menuBrandDTO == null) {
                            Result.Error(OrchestratorException("Menu could not be parsed"), HttpStatus.NOT_FOUND)
                        } else {
                            Result.Success(menuBrandDTO)
                        }
                    }
                }
                is Result.Error -> result
            }
        }
    }

    override suspend fun getProductDetail(
        token: String,
        countryId: Long,
        brandId: Long,
        flowId: Long,
        storeId: Long,
        platformId: Long?,
        productIds: Long
    ): Result<MenuHallProductResponseDTO> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                    call = {
                        menuBCAPI.getProductDetail(
                                token,
                                productIds,
                                countryId,
                                brandId,
                                flowId,
                                storeId,
                                platformId
                        ).callServices()
                    }
            )
            when (result) {
                is Result.Success -> {
                    val menuProductDetailEntity = result.data.data
                    if (menuProductDetailEntity == null) {
                        Result.Error(OrchestratorException("Product detail array is null"), HttpStatus.INTERNAL_SERVER_ERROR)
                    } else {
                        val menuProductDetailDTO = menuProductDetailEntity.toMenuProductResponseDTO()

                        if (menuProductDetailDTO == null) {
                            Result.Error(OrchestratorException("Error converting product detail array is null"), HttpStatus.INTERNAL_SERVER_ERROR)
                        } else {
                            Result.Success(menuProductDetailDTO)
                        }
                    }
                }
                is Result.Error -> result
            }
        }
    }

    override suspend fun getSuggestedPortions(
            token: String,
            portionsIds: List<Long>
    ): Result<List<MenuSuggestedPortionResponseDTO>> {
        return withContext(Dispatchers.IO) {
            val resultSuggestedPortions = safeApiCall(
                    call = {
                        menuBCAPI.getSuggestedPortions(
                                token,
                                portionsIds
                        ).callServices()
                    }
            )

            when (resultSuggestedPortions) {
                is Result.Success -> {
                    val menuChangePortionsData = resultSuggestedPortions.data.data
                    var resultData = listOf<MenuSuggestedPortionResponseDTO>()
                    if (menuChangePortionsData != null) {
                        resultData = menuChangePortionsData.mapNotNull { changePortion -> changePortion.toMenuSuggestedPortionResponseDTO() }
                    }
                    Result.Success(resultData)
                }
                is Result.Error -> resultSuggestedPortions
            }
        }
    }
}