package com.robinfood.app.usecases.getmenu

import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GetMenuStoreBrandsUseCaseTest {

    @Mock
    private lateinit var menuRepository: IMenuRepository

    @InjectMocks
    private lateinit var getMenuStoreBrandsUseCase: GetMenuStoreBrandsUseCase

    private val token = "token"
    private val storeId = 1L

    private val menuBrandDTOs = listOf(
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

    @Test
    fun test_GetMenuStoreBrands_when_service_returns_OK() {
        runBlocking {
            `when`(
                    menuRepository.getStoreBrands(
                    token,
                    storeId
                )
            ).thenReturn(Result.Success(menuBrandDTOs))

            val result = getMenuStoreBrandsUseCase.invoke(
                    token,
                    storeId
            )

            assertEquals(Result.Success(menuBrandDTOs), result)
        }
    }
}