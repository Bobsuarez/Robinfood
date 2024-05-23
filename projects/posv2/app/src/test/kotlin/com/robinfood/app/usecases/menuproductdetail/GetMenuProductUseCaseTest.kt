package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.app.mocks.MenuProductDetailDTOsMocks
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class GetMenuProductUseCaseTest {

    private val menuProductDetailDTOsMocks = MenuProductDetailDTOsMocks()

    private val token = "token"

    @Mock
    private lateinit var mockMenuRepository: IMenuRepository

    @InjectMocks
    private lateinit var getMenuProductUseCase: GetMenuProductUseCase

    @Test
    fun test_Get_Menu_Product_Returns_Correctly() {
        runBlocking {
            val productsId = listOf(1L)
            val resultMocked = Result.Success(menuProductDetailDTOsMocks.menuProductResponseDTO)

            `when`(
                mockMenuRepository.getProductDetail(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productsId.first()
                )
            ).thenReturn(resultMocked)

            val result = getMenuProductUseCase.invoke(
                token,
                1L,
                1L,
                1L,
                1L,
                1L,
                productsId
            )

            if (result is Result.Success) {
                assertEquals(result.data.size, 1)
                assertEquals(result.data.first().groups.size, 1)
                assertEquals(result.data.first().groups.first().portions.size, 1)
            }
        }
    }

    @Test
    fun test_Get_Menu_Product_Returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                val productsId = listOf(1L)

                `when`(
                    mockMenuRepository.getProductDetail(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        productsId.first()
                    )
                ).thenThrow(
                    ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Error repository"
                    )
                )

                getMenuProductUseCase.invoke(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productsId
                )
            }
        }
    }

    @Test
    fun test_Get_Menu_Product_Returns_Partial_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                val productsId = listOf(1L, 2L)
                val resultMocked = Result.Success(menuProductDetailDTOsMocks.menuProductResponseDTO)

                `when`(
                    mockMenuRepository.getProductDetail(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        productsId.first()
                    )
                ).thenReturn(resultMocked)

                `when`(
                    mockMenuRepository.getProductDetail(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        productsId.last()
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getMenuProductUseCase.invoke(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productsId
                )
            }
        }
    }

    @Test
    fun test_Get_Menu_Product_Returns_Partial_Error_1() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                val productsId = listOf(1L, 2L)
                val resultMocked = Result.Success(menuProductDetailDTOsMocks.menuProductResponseDTO)

                `when`(
                    mockMenuRepository.getProductDetail(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        productsId.first()
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                `when`(
                    mockMenuRepository.getProductDetail(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        productsId.last()
                    )
                ).thenReturn(resultMocked)

                getMenuProductUseCase.invoke(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    productsId
                )
            }
        }
    }

}