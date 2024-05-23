package com.robinfood.app.usecases.crossellingmenu

import com.robinfood.app.mocks.MenuProductDetailDTOsMocks
import com.robinfood.app.usecases.crosssellingmenu.GetCrossSellingMenuUseCase
import com.robinfood.app.usecases.getmenu.IGetMenuStoreBrandsUseCase
import com.robinfood.app.usecases.menuproductdetail.IGetProductDetailUseCase
import com.robinfood.core.dtos.menu.*
import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.entities.menu.MenuArticleDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class GetCrossSellingMenuUseCaseTest {

    @Mock
    private lateinit var getMenuStoreBrandsUseCase: IGetMenuStoreBrandsUseCase

    @Mock
    private lateinit var menuRepository: IMenuRepository

    @Mock
    private lateinit var getProductDetailUseCase: IGetProductDetailUseCase

    @InjectMocks
    private lateinit var getCrossSellingMenuUseCase: GetCrossSellingMenuUseCase

    private val token = "token"
    private val countryId = 1L
    private val flowId = 1L
    private val storeId = 1L

    private val menuBrandResponseDTOs = listOf(
            MenuBrandResponseDTO(
                    color = "#000000",
                    id = 1L,
                    image = "image-muy-brand.png",
                    name = "Muy Brand Prueba"
            ),
            MenuBrandResponseDTO(
                    color = "#000000",
                    id = 2L,
                    image = "image-pecado-natura-brand.png",
                    name =  "Pecado Natural Brand Prueba"
            )
    )

    private val menuSizeDTOs = listOf(
            MenuSizeDTO(
                    MenuArticleDTO(
                            id = 1L,
                            menuHallProductId = 1L,
                            typeId = 1L,
                            typeName = "ARTICLE"
                    ),
                    discount = BigDecimal.ZERO,
                    id = 1L,
                    name = "Size",
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
                    name = "Product ABC for Testing",
                    sizes = menuSizeDTOs
            ),
            MenuBaseProductDTO(
                    brandId = 1L,
                    description = "Description",
                    displayType = 1,
                    flowType = "flow",
                    id = 1L,
                    image = "image.png",
                    name = "Product XYZ for Testing",
                    sizes = menuSizeDTOs
            )
    )

    private val menuCategoryDTOs = listOf(
            MenuCategoryDTO(
                    baseProducts = menuBaseProductDTOs,
                    id = 5L,
                    name = "Postres Pruebas",
                    position = 1
            ),
            MenuCategoryDTO(
                    baseProducts = menuBaseProductDTOs,
                    id = 4L,
                    name = "Bebidas Pruebas",
                    position = 1
            ),
            MenuCategoryDTO(
                    baseProducts = menuBaseProductDTOs,
                    id = 3L,
                    name = "Plato principal Pruebas",
                    position = 1
            ),
    )

    private val menuBrandDTO = MenuBrandDTO(
            backgroundColor = "#000000",
            categories = menuCategoryDTOs,
            id = 1L,
            imageUrl = "image.png",
            name = "Menu brand"
    )

    private val menuProductDetailDTOsMocks = MenuProductDetailDTOsMocks()

    val menuProductDetailDTO = MenuProductDetailDTO(
            com.robinfood.core.dtos.MenuBrandDTO(
                    1L,
                    "RobinFood"
            ),
            "Product",
            1L,
            "suggested",
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            "Papas",
            1L,
            listOf(menuProductDetailDTOsMocks.menuProductSizeDTO).toMutableList(),
            "sku",
    )

    @BeforeEach
    fun setUp() {
        ReflectionTestUtils.setField(
                getCrossSellingMenuUseCase, "crossSellingBrandsIdsList", listOf(1L))

        ReflectionTestUtils.setField(
                getCrossSellingMenuUseCase, "crossSellingDessertsCategoriesIds", listOf(5L))

        ReflectionTestUtils.setField(
                getCrossSellingMenuUseCase, "crossSellingDrinksCategoriesIds", listOf(4L))
    }

    @Test
    fun `test GetCrossSellingMenu when service returns OK`() {
        runBlocking {
            //simulate getting brand list
            Mockito.`when`(getMenuStoreBrandsUseCase.invoke(token, storeId))
                    .thenReturn(Result.Success(menuBrandResponseDTOs))

            //simulate getting menu by brand
            Mockito.`when`(
                    menuRepository.getStoreMenuBrand(
                            token,
                            menuBrandResponseDTOs.first(),
                            countryId,
                            flowId,
                            storeId
                    )
            ).thenReturn(Result.Success(menuBrandDTO))

            for (menuBaseProductDto in menuBaseProductDTOs) {
                //simulate getting product detail in each menu
                Mockito.`when`(
                        getProductDetailUseCase.invoke(
                                token,
                                countryId,
                                menuBaseProductDto.brandId,
                                flowId,
                                storeId,
                                null,
                                listOf(menuBaseProductDto.sizes.first().article.menuHallProductId)
                        )
                ).thenReturn(Result.Success(menuProductDetailDTOsMocks.menuProductDetailDTO))
            }

            // Act
            val crossSellingMenuResult = getCrossSellingMenuUseCase.invoke(token, countryId, flowId, storeId)

            // Assert
            if (crossSellingMenuResult is Result.Success) {
                Assertions.assertEquals(
                        crossSellingMenuResult.data.products.first().brand.name,
                        menuProductDetailDTOsMocks.menuProductDetailDTO.brand.name
                )
            }
        }
    }

    @Test
    fun `test GetCrossSellingMenu when service returns exception in getting brands`() {
        runBlocking {
            assertThrows<ResponseStatusException>{
                Mockito.`when`(
                        getMenuStoreBrandsUseCase.invoke(token, storeId)
                ).thenReturn(Result.Error(
                        ResponseStatusException(HttpStatus.BAD_REQUEST, "Error repository"),
                        HttpStatus.BAD_REQUEST)
                )

                val crossSellingMenuResult = getCrossSellingMenuUseCase.invoke(token, countryId, flowId, storeId)
            }

        }
    }

    @Test
    fun `test GetCrossSellingMenu when service returns exception in getting menu by brand`() {
        runBlocking {

            //simulate getting brand list
            Mockito.`when`(getMenuStoreBrandsUseCase.invoke(token, storeId))
                    .thenReturn(Result.Success(menuBrandResponseDTOs))

            //simulate getting menu by brand
            Mockito.`when`(
                    menuRepository.getStoreMenuBrand(
                            token,
                            menuBrandResponseDTOs.first(),
                            countryId,
                            flowId,
                            storeId
                    )
            ).thenReturn(Result.Error(Throwable("algo"),HttpStatus.BAD_REQUEST))

            val crossSellingMenuResult = getCrossSellingMenuUseCase.invoke(token, countryId, flowId, storeId)

            if (crossSellingMenuResult is Result.Success) {
                Assertions.assertTrue(crossSellingMenuResult.data.products.isEmpty() )
            }
        }
    }

    @Test
    fun `test GetCrossSellingMenu when service returns exception in getting prodcuct detail`() {
        runBlocking {
            //simulate getting brand list
            Mockito.`when`(getMenuStoreBrandsUseCase.invoke(token, storeId))
                    .thenReturn(Result.Success(menuBrandResponseDTOs))

            //simulate getting menu by brand
            Mockito.`when`(
                    menuRepository.getStoreMenuBrand(
                            token,
                            menuBrandResponseDTOs.first(),
                            countryId,
                            flowId,
                            storeId
                    )
            ).thenReturn(Result.Success(menuBrandDTO))

            for (menuBaseProductDto in menuBaseProductDTOs) {
                //simulate getting product detail in each menu
                Mockito.`when`(
                        getProductDetailUseCase.invoke(
                                token,
                                countryId,
                                menuBaseProductDto.brandId,
                                flowId,
                                storeId,
                                null,
                                listOf(menuBaseProductDto.sizes.first().article.menuHallProductId)
                        )
                ).thenReturn(Result.Error(Exception("asd"),HttpStatus.INTERNAL_SERVER_ERROR))
            }

            // Act
            val crossSellingMenuResult = getCrossSellingMenuUseCase.invoke(token, countryId, flowId, storeId)

            // Assert
            if (crossSellingMenuResult is Result.Success) {
                Assertions.assertTrue(crossSellingMenuResult.data.products.isEmpty() )
            }
        }
    }
}
