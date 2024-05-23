package com.robinfood.app.usecases.crosssellingmenu

import com.robinfood.app.usecases.getmenu.IGetMenuStoreBrandsUseCase
import com.robinfood.app.usecases.menuproductdetail.IGetProductDetailUseCase
import com.robinfood.core.dtos.crosssellingmenu.CrossSellingMenuDTO
import com.robinfood.core.dtos.menu.MenuBaseProductDTO
import com.robinfood.core.dtos.menu.MenuBrandDTO
import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.dtos.menu.MenuCategoryDTO
import com.robinfood.core.dtos.menu.response.CategoryDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailCrossSellingDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.server.ResponseStatusException

class GetCrossSellingMenuUseCase(
        private val getMenuStoreBrandsUseCase: IGetMenuStoreBrandsUseCase,
        private val menuRepository: IMenuRepository,
        private val getProductDetailUseCase: IGetProductDetailUseCase
) : IGetCrossSellingMenuUseCase {

    @Value("#{'\${app.cross-selling-brands}'.split(',')}")
    private val crossSellingBrandsIdsList: List<Long> = listOf()

    @Value("#{'\${app.cross-selling-desserts-halls}'.split(',')}")
    private val crossSellingDessertsCategoriesIds: List<Long> = listOf()

    @Value("#{'\${app.cross-selling-drinks-halls}'.split(',')}")
    private val crossSellingDrinksCategoriesIds: List<Long> = listOf()

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun invoke(
            token: String,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): Result<CrossSellingMenuDTO> {

        log.info("Started GetCrossSellingMenuUseCase method invoke(). " +
                "Parameters are storeId = {}, countryId = {}, flowId = {}", storeId, countryId, flowId)

        val storeBrands = when (
            val resultStoreBrand = getMenuStoreBrandsUseCase.invoke(token, storeId)
        ) {
            is Result.Success -> resultStoreBrand.data
            is Result.Error -> throw ResponseStatusException(
                    resultStoreBrand.httpStatus,
                    resultStoreBrand.exception.localizedMessage
            )
        }
        log.info("Retrieved store brands = {}", storeBrands)

        val crossSellingBrands = storeBrands.filter { crossSellingBrandsIdsList.contains(it.id) }

        log.info("Filtered brands {}", crossSellingBrands)

        val brandsMenuList: List<MenuBrandDTO> = getMenuBrands(
                crossSellingBrands,
                token,
                countryId,
                flowId,
                storeId
        )

        val crossSellingCategoriesIds = crossSellingDessertsCategoriesIds + crossSellingDrinksCategoriesIds

        val crossSellingDessertsCategoriesList = getCrossSellingCategories(
                brandsMenuList,
                crossSellingCategoriesIds
        )

        val crossSellingProductsDetailList: MutableList<MenuProductDetailCrossSellingDTO> = mutableListOf()

        for (crossSellingDessertsCategory in crossSellingDessertsCategoriesList) {
            val productsDetail = getProductsDetail(
                    crossSellingDessertsCategory.baseProducts,
                    crossSellingDessertsCategory,
                    token,
                    countryId,
                    flowId,
                    storeId
            )
            crossSellingProductsDetailList.addAll(productsDetail.filterNotNull())
        }

        completeBrandName(crossSellingProductsDetailList, storeBrands)

        log.info("Finished GetCrossSellingMenuUseCase. crossSellingProductsDetailList = {}",
                CrossSellingMenuDTO(crossSellingProductsDetailList))

        return Result.Success(CrossSellingMenuDTO(crossSellingProductsDetailList))

    }

    /**
     * Loop through brands list to set product's brand name
     */
    private fun completeBrandName(products: List<MenuProductDetailCrossSellingDTO>, brands: List<MenuBrandResponseDTO>) {
        for (product in products) {
            val brandName = brands.find { product.brand.id == it.id }
            product.brand.name = brandName!!.name
        }
    }

    private suspend fun getMenuBrands(
            brands: List<MenuBrandResponseDTO>,
            token: String,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): List<MenuBrandDTO> {
        log.info("Started GetCrossSellingMenuUseCase method getBrandsMenu().")

        val brandsMenusResults = brands.map { menuBrandResponseDTO: MenuBrandResponseDTO ->
            when (
                val menuBrand = menuRepository.getStoreMenuBrand(
                        token,
                        menuBrandResponseDTO,
                        countryId,
                        flowId,
                        storeId
                )
            ) {
                is Result.Success -> menuBrand.data
                is Result.Error -> null
            }
        }
        return brandsMenusResults.filterNotNull()
    }

    private fun getCrossSellingCategories(
            brandsMenus: List<MenuBrandDTO>,
            categoriesIdsToFilter: List<Long>
    ): List<MenuCategoryDTO> {

        log.info("Started GetCrossSellingMenuUseCase method getCrossSellingCategories().")

        val crossSellingCategories = brandsMenus.flatMap {
            it.categories
        }.filter { categoriesIdsToFilter.contains(it.id) }

        log.info("Cross-Selling categories for store {}", crossSellingCategories)

        return crossSellingCategories

    }

    private suspend fun getProductsDetail(
            products: List<MenuBaseProductDTO>,
            menuCategory: MenuCategoryDTO,
            token: String,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): MutableList<MenuProductDetailCrossSellingDTO?> {
        val category = CategoryDTO(menuCategory.id, menuCategory.name)
        return coroutineScope {
            val productMenuDetailsResults = products.map { menuBaseProductDTO: MenuBaseProductDTO ->
                async {
                    getProductDetailUseCase.invoke(
                            token,
                            countryId,
                            menuBaseProductDTO.brandId,
                            flowId,
                            storeId,
                            null,
                            listOf(menuBaseProductDTO.sizes.first().article.menuHallProductId)
                    )
                }
            }.awaitAll()
            val productMenuDetails: MutableList<MenuProductDetailCrossSellingDTO?> = mutableListOf()
            for (resultProduct in productMenuDetailsResults) {
                when (resultProduct) {
                    is Result.Success -> {
                        val menuProductDetail: MenuProductDetailDTO = resultProduct.data
                        val menuProductCrossSellingDetail = MenuProductDetailCrossSellingDTO(
                                menuProductDetail.brand,
                                category,
                                menuProductDetail.description,
                                menuProductDetail.flowType,
                                menuProductDetail.id,
                                menuProductDetail.image,
                                menuProductDetail.name,
                                menuProductDetail.sizes,
                                menuProductDetail.sku
                        )
                        productMenuDetails.add(menuProductCrossSellingDetail)
                    }
                    is Result.Error -> productMenuDetails.add(null)
                }
            }
            log.info("Cross-Selling products detail {}", productMenuDetails)
            productMenuDetails
        }
    }
}