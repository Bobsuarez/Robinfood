package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.app.mocks.MenuProductDetailDTOsMocks
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository
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


@ExtendWith(MockitoExtension::class)
class GetSuggestedPortionUseCaseTest {

    private val menuProductDetailDTOsMocks = MenuProductDetailDTOsMocks()

    private val token = "token"

    @Mock
    private lateinit var mockMenuRepository: IMenuRepository

    @InjectMocks
    private lateinit var getSuggestedPortionUseCase: GetSuggestedPortionUseCase

    @Test
    fun test_Get_Change_Portion_Returns_Correctly() {
        runBlocking {
            val portionIds = listOf(1L)
            val resultMocked = Result.Success(listOf(menuProductDetailDTOsMocks.suggestedPortions))

            `when`(mockMenuRepository.getSuggestedPortions(token, portionIds))
                    .thenReturn(resultMocked)

            val result = getSuggestedPortionUseCase.invoke(token, portionIds)

            assertEquals(Result.Success(listOf(menuProductDetailDTOsMocks.suggestedPortions)), result)
        }
    }

    @Test
    fun test_Get_Change_Portion_Returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                val portionIds = listOf(1L)

                `when`(mockMenuRepository.getSuggestedPortions(token, portionIds))
                        .thenThrow(
                                ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Error repository"
                                )
                        )

                getSuggestedPortionUseCase.invoke(token, portionIds)
            }
        }
    }
}