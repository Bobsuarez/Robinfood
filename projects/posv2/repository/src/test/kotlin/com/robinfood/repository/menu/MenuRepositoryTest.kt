package com.robinfood.repository.menu

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.menu.MenuBaseProductDTO
import com.robinfood.core.dtos.menu.MenuBrandDTO
import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.dtos.menu.MenuCategoryDTO
import com.robinfood.core.dtos.menu.MenuSizeDTO
import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.menu.BrandMenuResponseEntity
import com.robinfood.core.entities.menu.MenuArticleDTO
import com.robinfood.core.entities.menu.MenuBrandResponseEntity
import com.robinfood.core.entities.menu.MenuHallProductResponseEntity
import com.robinfood.core.entities.menu.MenuHallResponseEntity
import com.robinfood.core.entities.menu.MenuProductResponseEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.menu.toMenuProductResponseDTO
import com.robinfood.core.mappers.menu.toMenuSuggestedPortionResponseDTO
import com.robinfood.network.api.MenuBCAPI
import com.robinfood.repository.mocks.MenuProductDetailEntitiesMocks
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class MenuRepositoryTest {

    private val menuProductDetailEntityMocks = MenuProductDetailEntitiesMocks()

    @Mock
    private lateinit var menuBCAPI: MenuBCAPI

    @InjectMocks
    private lateinit var menuRepository: MenuRepository

    private val token = "token"
    private val countryId = 1L
    private val flowId = 1L
    private val storeId = 1L
    private val productIds = listOf(1L)

    private val portionIds = listOf(1L)

    private val menuProductResponseDTO = menuProductDetailEntityMocks.menuProductResponseEntity.toMenuProductResponseDTO()

    private val menuProductEmptyGroupResponseDTO = menuProductDetailEntityMocks.menuProductEmptyGroupResponseEntity.toMenuProductResponseDTO()

    private val menuProductEmptyDataResponseDTO = menuProductDetailEntityMocks.menuProductEmptyDataResponseEntity.toMenuProductResponseDTO()

    private val menuSuggestedPortionResponseDTO = menuProductDetailEntityMocks.suggestedPortions.toMenuSuggestedPortionResponseDTO()

    private val menuSuggestedPortionEmptyResponseDTO = menuProductDetailEntityMocks.suggestedEmptyPortions.toMenuSuggestedPortionResponseDTO()


    private val menuBrandResponseEntity = MenuBrandResponseEntity(
            color = "#000000",
            id = 1L,
            image = "image.png",
            name = "Menu brand"
    )

    private val menuBrandResponseDTO = MenuBrandResponseDTO(
            color = "#000000",
            id = 1L,
            image = "image.png",
            name = "Menu brand"
    )

    private val menuBrandResponseIdNullEntity = MenuBrandResponseEntity(
            color = "#000000",
            id = null,
            image = "image.png",
            name = "Menu brand"
    )

    private val menuBrandResponseDTOs = listOf(menuBrandResponseDTO)

    private val menuBrandResponseEntities = listOf(menuBrandResponseEntity)

    private val menuBrandResponseEmptyEntities = listOf(menuBrandResponseIdNullEntity)

    private val menuSizeDTOs = listOf(
            MenuSizeDTO(
                    MenuArticleDTO(
                            id = DEFAULT_LONG_VALUE,
                            menuHallProductId = 1L,
                            typeId = 1L,
                            typeName = "ARTICLE"
                    ),
                    discount = BigDecimal.ZERO,
                    id = 1L,
                    name = "MUY",
                    price = BigDecimal.ZERO
            )
    )

    private val menuBaseProductDTOs = listOf(
            MenuBaseProductDTO(
                    brandId = 1L,
                    description = "Description",
                    displayType = 1,
                    flowType = "flow",
                    id = 1L,
                    image = "image.png",
                    name = "Product",
                    sizes = menuSizeDTOs
            )
    )

    private val menuCategoryDTOs = listOf(
            MenuCategoryDTO(
                    baseProducts = menuBaseProductDTOs,
                    id = 1L,
                    name = "Category",
                    position = 0
            )
    )

    private val menuBrandDTO = MenuBrandDTO(
            backgroundColor = "#000000",
            categories = menuCategoryDTOs,
            id = 1L,
            imageUrl = "image.png",
            name = "Menu brand"
    )

    private val apiMenuBrandsResponseEntity = APIResponseEntity(
            200,
            menuBrandResponseEntities,
            "CO",
            "",
            "Brands retrieved successfully"
    )

    private val apiMenuBrandsResponseEmptyEntity = APIResponseEntity(
            200,
            menuBrandResponseEmptyEntities,
            "CO",
            "",
            "Brands retrieved successfully"
    )

    private val apiMenuBrandsResponseErrorEntity = APIResponseEntity(
            500,
            menuBrandResponseEntities,
            "CO",
            "Error",
            "Brands could not be retrieved"
    )

    private val apiMenuBrandsResponseEntityWithNullBrands = APIResponseEntity<List<MenuBrandResponseEntity>>(
            500,
            null,
            "CO",
            "Error",
            "Brands could not be retrieved"
    )

    private val menuProductResponseEntities = listOf(
            MenuProductResponseEntity(
                    brandId = 1L,
                    countryId = 1L,
                    description = "Description",
                    discount = BigDecimal.ZERO,
                    display = 1,
                    displayType = 1,
                    flow = "flow",
                    id = 1L,
                    image = "image.png",
                    name = "Product",
                    parentId = 1L,
                    position = 0,
                    price = BigDecimal.ZERO,
                    sizeId = 1L,
                    sku = "SKU",
                    status = 1L,
                    typeId = 1L,
                    typeName = "ARTICLE"
            )
    )

    private val menuHallResponseEntities = listOf(
            MenuHallResponseEntity(
                    id = 1L,
                    name = "Category",
                    position = 0,
                    products = menuProductResponseEntities,
                    status = 1L
            )
    )

    private val brandMenuResponseEntity = BrandMenuResponseEntity(
            halls = menuHallResponseEntities,
            name = "Menu"
    )

    private val brandMenuResponseEntityWithHallsNull = BrandMenuResponseEntity(
            halls = null,
            name = "Menu"
    )

    private val apiBrandMenuResponseEntity = APIResponseEntity(
            200,
            brandMenuResponseEntity,
            "CO",
            "",
            "Brand Menu retrieved successfully"
    )

    private val apiBrandMenuResponseEntityWithHallsNull = APIResponseEntity(
            200,
            brandMenuResponseEntityWithHallsNull,
            "CO",
            "",
            "Brand Menu retrieved successfully"
    )

    private val apiBrandMenuResponseErrorEntity = APIResponseEntity(
            500,
            brandMenuResponseEntity,
            "CO",
            "Error",
            "Brand Menu could not be retrieved"
    )

    private val apiBrandMenuResponseEntityWithNullBrandMenu = APIResponseEntity<BrandMenuResponseEntity>(
            200,
            null,
            "CO",
            "",
            "Brand Menu retrieved successfully"
    )

    private val apiProductDetailResponseEntity = APIResponseEntity(
            200,
            menuProductDetailEntityMocks.menuProductResponseEntity,
            "CO",
            "",
            "OK"
    )

    private val apiProductDetailResponseEntityWithDataNull = APIResponseEntity<MenuHallProductResponseEntity>(
            200,
            null,
            "CO",
            "",
            "OK"
    )

    private val apiProductDetailEmptyGroupResponseEntity = APIResponseEntity(
            200,
            menuProductDetailEntityMocks.menuProductEmptyGroupResponseEntity,
            "CO",
            "",
            "OK"
    )

    private val apiProductDetailEmptyDataResponseEntity = APIResponseEntity(
            200,
            menuProductDetailEntityMocks.menuProductEmptyDataResponseEntity,
            "CO",
            "",
            "OK"
    )

    private val apiSuggestedResponseEntity = APIResponseEntity(
            200,
            listOf(menuProductDetailEntityMocks.suggestedPortions),
            "CO",
            "",
            "OK"
    )

    private val apiSuggestedEmptyResponseEntity = APIResponseEntity(
            200,
            listOf(menuProductDetailEntityMocks.suggestedEmptyPortions),
            "CO",
            "",
            "OK"
    )

    private val apiProductDetailResponseErrorEntity = APIResponseEntity(
            404,
            null,
            "CO",
            "Error",
            "Product not found"
    )

    private val apiProductDetailResponseSuccessNullEntity = APIResponseEntity(
            200,
            menuProductDetailEntityMocks.menuProductNullDataResponseEntity,
            "CO",
            "",
            "OK"
    )

    @Test
    fun test_GetStoreBrands_when_service_returns_OK() {
        runBlocking {
            `when`(menuBCAPI.getStoreBrands(
                    token,
                    storeId
            )).thenReturn(Response.success(apiMenuBrandsResponseEntity))
            val result = menuRepository.getStoreBrands(
                    token,
                    storeId
            )
            assertEquals(Result.Success(menuBrandResponseDTOs), result)
        }
    }

    @Test
    fun test_GetStoreBrands_when_service_returns_ERROR() {
        runBlocking {
            `when`(menuBCAPI.getStoreBrands(
                    token,
                    storeId
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiMenuBrandsResponseErrorEntity.toJson()
            )))
            val result = menuRepository.getStoreBrands(
                    token,
                    storeId
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_GetStoreBrands_when_service_returns_No_Brands() {
        runBlocking {
            `when`(menuBCAPI.getStoreBrands(
                    token,
                    storeId
            )).thenReturn(Response.success(apiMenuBrandsResponseEntityWithNullBrands))
            val result = menuRepository.getStoreBrands(
                    token,
                    storeId
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_GetStoreMenuBrand_when_service_returns_OK() {
        runBlocking {
            `when`(menuBCAPI.getStoreBrandMenu(
                    token,
                    menuBrandResponseDTO.id,
                    countryId,
                    flowId,
                    storeId
            )).thenReturn(Response.success(apiBrandMenuResponseEntity))
            val result = menuRepository.getStoreMenuBrand(
                    token,
                    menuBrandResponseDTO,
                    countryId,
                    flowId,
                    storeId
            )
            assertEquals(Result.Success(menuBrandDTO), result)
        }
    }

    @Test
    fun test_GetStoreMenuBrand_when_service_returns_OK_but_menu_cannot_be_parsed() {
        runBlocking {
            `when`(menuBCAPI.getStoreBrandMenu(
                    token,
                    menuBrandResponseDTO.id,
                    countryId,
                    flowId,
                    storeId
            )).thenReturn(Response.success(apiBrandMenuResponseEntityWithHallsNull))
            val result = menuRepository.getStoreMenuBrand(
                    token,
                    menuBrandResponseDTO,
                    countryId,
                    flowId,
                    storeId
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_GetStoreMenuBrand_when_service_returns_ERROR() {
        runBlocking {
            `when`(menuBCAPI.getStoreBrandMenu(
                    token,
                    menuBrandResponseDTO.id,
                    countryId,
                    flowId,
                    storeId
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiBrandMenuResponseErrorEntity.toJson()
            )))
            val result = menuRepository.getStoreMenuBrand(
                    token,
                    menuBrandResponseDTO,
                    countryId,
                    flowId,
                    storeId
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_GetStoreMenuBrand_when_service_returns_No_Brand_Menu() {
        runBlocking {
            `when`(menuBCAPI.getStoreBrandMenu(
                    token,
                    menuBrandResponseDTO.id,
                    countryId,
                    flowId,
                    storeId
            )).thenReturn(Response.success(apiBrandMenuResponseEntityWithNullBrandMenu))
            val result = menuRepository.getStoreMenuBrand(
                    token,
                    menuBrandResponseDTO,
                    countryId,
                    flowId,
                    storeId
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get product detail when service returns successfully`() {
        runBlocking {
            `when`(menuBCAPI.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )).thenReturn(Response.success(apiProductDetailResponseEntity))

            val result = menuRepository.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )
            assertEquals(Result.Success(menuProductResponseDTO!!), result)
        }
    }

    @Test
    fun `test get product detail when service returns successfully but product detail is null`() {
        runBlocking {
            `when`(menuBCAPI.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )).thenReturn(Response.success(apiProductDetailResponseEntityWithDataNull))

            val result = menuRepository.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get product detail with empty group when service returns successfully`() {
        runBlocking {
            `when`(menuBCAPI.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )).thenReturn(Response.success(apiProductDetailEmptyGroupResponseEntity))

            val result = menuRepository.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )
            assertEquals(Result.Success(menuProductEmptyGroupResponseDTO!!), result)
        }
    }

    @Test
    fun `test get product detail with empty data when service returns successfully`() {
        runBlocking {
            `when`(menuBCAPI.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )).thenReturn(Response.success(apiProductDetailEmptyDataResponseEntity))

            val result = menuRepository.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )
            assertEquals(Result.Success(menuProductEmptyDataResponseDTO!!), result)
        }
    }

    @Test
    fun `test get product detail when service returns error`() {
        runBlocking {
            `when`(menuBCAPI.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiProductDetailResponseErrorEntity.toJson()
            )))

            val result = menuRepository.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get product detail id is null when service returns success`() {
        runBlocking {
            `when`(menuBCAPI.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )).thenReturn(Response.success(apiProductDetailResponseSuccessNullEntity))

            val result = menuRepository.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productIds.first()
            )
            assertTrue(result is Result.Error)
        }
    }


    @Test
    fun `test get suggested portions when service returns successfully`() {
        runBlocking {
            `when`(menuBCAPI.getSuggestedPortions(
                    token,
                    portionIds
            )).thenReturn(Response.success(apiSuggestedResponseEntity))


            val result = menuRepository.getSuggestedPortions(
                    token,
                    portionIds
            )
            assertEquals(Result.Success(listOf(menuSuggestedPortionResponseDTO)), result)
        }
    }

    @Test
    fun `test get empty suggested portions when service returns successfully`() {
        runBlocking {
            `when`(menuBCAPI.getSuggestedPortions(
                    token,
                    portionIds
            )).thenReturn(Response.success(apiSuggestedEmptyResponseEntity))


            val result = menuRepository.getSuggestedPortions(
                    token,
                    portionIds
            )
            assertEquals(Result.Success(listOf(menuSuggestedPortionEmptyResponseDTO)), result)
        }
    }

    @Test
    fun `test get suggested portions when service returns error`() {
        runBlocking {
            `when`(menuBCAPI.getSuggestedPortions(
                    token,
                    portionIds
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiProductDetailResponseErrorEntity.toJson()
            )))

            val result = menuRepository.getSuggestedPortions(
                    token,
                    portionIds
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_GetStoreBrands_when_service_brandResponse_Is_Null_returns_OK() {
        runBlocking {

            `when`(menuBCAPI.getStoreBrands(
                    token,
                    storeId
            )).thenReturn(Response.success(apiMenuBrandsResponseEmptyEntity))

            val result = menuRepository.getStoreBrands(
                    token,
                    storeId
            )
            assertEquals(Result.Success(emptyList<MenuBrandResponseDTO>()), result)
        }
    }
}