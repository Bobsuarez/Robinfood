package com.robinfood.app.usecases.getmenu

import com.robinfood.core.dtos.menu.MenuBaseProductDTO
import com.robinfood.core.dtos.menu.MenuBrandDTO
import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.dtos.menu.MenuCategoryDTO
import com.robinfood.core.dtos.menu.MenuDTO
import com.robinfood.core.dtos.menu.MenuSizeDTO
import com.robinfood.core.entities.menu.MenuArticleDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.math.BigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class GetMenuUseCaseTest {

    @Mock
    private lateinit var menuRepository: IMenuRepository

    @Mock
    private lateinit var getMenuStoreBrandsUseCase: IGetMenuStoreBrandsUseCase

    @InjectMocks
    private lateinit var getMenuUseCase: GetMenuUseCase

    private val token = "token"
    private val countryId = 1L
    private val flowId = 1L
    private val storeId = 1L

    private val menuBrandResponseDTOs = listOf(
            MenuBrandResponseDTO(
                    color = "#000000",
                    id = 1L,
                    image = "image.png",
                    name = "Brand 1"
            ),
            MenuBrandResponseDTO(
                    color = "#000000",
                    id = 2L,
                    image = "image.png",
                    name = "Brand 2"
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
                    name = "Menu Product",
                    sizes = menuSizeDTOs
            )
    )

    private val menuCategoryDTOs = listOf(
            MenuCategoryDTO(
                    baseProducts = menuBaseProductDTOs,
                    id = 1L,
                    name = "Menu Category",
                    position = 1
            )
    )

    private val menuBrandDTO = MenuBrandDTO(
            backgroundColor = "#000000",
            categories = menuCategoryDTOs,
            id = 1L,
            imageUrl = "image.png",
            name = "Menu brand"
    )

    private val menuDTO = MenuDTO(listOf(menuBrandDTO, menuBrandDTO))

    @Test
    fun test_GetMenu_when_service_returns_OK() {
        runBlocking {
            `when`(
                    getMenuStoreBrandsUseCase.invoke(
                            token,
                            storeId
                    )
            ).thenReturn(Result.Success(menuBrandResponseDTOs))

            for (menuBrandResponseDTO in menuBrandResponseDTOs) {
                `when`(
                        menuRepository.getStoreMenuBrand(
                                token,
                                menuBrandResponseDTO,
                                countryId,
                                flowId,
                                storeId
                        )
                ).thenReturn(Result.Success(menuBrandDTO))
            }

            val result = getMenuUseCase.invoke(
                    token,
                    countryId,
                    flowId,
                    storeId
            )

            assertEquals(Result.Success(menuDTO), result)
        }
    }

    @Test
    fun test_GetMenu_when_service_returns_Null_OK() {
        val menuBrandResponseDTOEmpty: List<MenuBrandResponseDTO> = listOf()

        val menusDTO = MenuDTO(listOf())

        runBlocking {
            `when`(
                    getMenuStoreBrandsUseCase.invoke(
                            token,
                            storeId
                    )
            ).thenReturn(Result.Success(menuBrandResponseDTOEmpty))

            for (menuBrandResponseDTO in menuBrandResponseDTOEmpty) {
                `when`(
                        menuRepository.getStoreMenuBrand(
                                token,
                                menuBrandResponseDTO,
                                countryId,
                                flowId,
                                storeId
                        )
                ).thenReturn(Result.Success(menuBrandDTO))
            }

            val result = getMenuUseCase.invoke(
                    token,
                    countryId,
                    flowId,
                    storeId
            )

            assertEquals(Result.Success(menusDTO), result)
        }
    }


    @Test
    @Throws(Exception::class)
    fun test_GetMenu_when_service_returns_Error() {
        val menuBrandResponseDTOEmpty: List<MenuBrandResponseDTO> = listOf()

        runBlocking {

            `when`(
                    getMenuStoreBrandsUseCase.invoke(
                            token,
                            storeId
                    )
            ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            for (menuBrandResponseDTO in menuBrandResponseDTOEmpty) {
                `when`(
                        menuRepository.getStoreMenuBrand(
                                token,
                                menuBrandResponseDTO,
                                countryId,
                                flowId,
                                storeId
                        )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))
            }

            val result = getMenuUseCase.invoke(
                    token,
                    countryId,
                    flowId,
                    storeId
            )

            assertNotNull(result)

        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetMenu_when_service_returns_Fail_Repository() {

        runBlocking {

            `when`(
                    getMenuStoreBrandsUseCase.invoke(
                            token,
                            storeId
                    )
            ).thenReturn(Result.Success(menuBrandResponseDTOs))

            for (menuBrandResponseDTO in menuBrandResponseDTOs) {
                `when`(
                        menuRepository.getStoreMenuBrand(
                                token,
                                menuBrandResponseDTO,
                                countryId,
                                flowId,
                                storeId
                        )
                ).thenReturn(Result.Error(Exception("Some error repository"), HttpStatus.BAD_REQUEST))
            }

            val result = getMenuUseCase.invoke(
                    token,
                    countryId,
                    flowId,
                    storeId
            )

            assertNotNull(result)

        }
    }

    class Model(parentContext: CoroutineContext = Dispatchers.Default) {
        private val modelScope = CoroutineScope(parentContext)
        var result = false

        fun doWork() {
            modelScope.launch {
                Thread.sleep(3000)
                result = true
            }
        }
    }

    @Test
    fun test_GetMenu_when_service_returns_Async() {
        runBlocking {

            `when`(
                    getMenuStoreBrandsUseCase.invoke(
                            token,
                            storeId
                    )
            ).thenReturn(Result.Success(menuBrandResponseDTOs))

            for (menuBrandResponseDTO in menuBrandResponseDTOs) {
                `when`(
                        menuRepository.getStoreMenuBrand(
                                token,
                                menuBrandResponseDTO,
                                countryId,
                                flowId,
                                storeId
                        )
                ).thenReturn(Result.Success(menuBrandDTO))
            }

            val result = getMenuUseCase.invoke(
                    token,
                    countryId,
                    flowId,
                    storeId
            )

            val model = Model(coroutineContext)
            model.doWork()
            model

            assertEquals(Result.Success(menuDTO), result)
        }
    }
}