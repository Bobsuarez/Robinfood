package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.app.mocks.MenuProductDetailDTOsMocks
import com.robinfood.core.enums.Result
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
class GroupProductsBySizeIdUseCaseTest {

    private val menuProductDetailDTOsMocks = MenuProductDetailDTOsMocks()

    private val token = "token"

    @Mock
    private lateinit var getSuggestedPortionUseCase: IGetSuggestedPortionsUseCase

    @InjectMocks
    private lateinit var groupProductsBySizeIdUseCase: GroupProductsBySizeIdUseCase

    @Test
    fun test_Group_Products_By_SizeId_UseCase_Returns_Correctly() {
        runBlocking {
            val mockDataResponse = menuProductDetailDTOsMocks.menuProductDetailDTO

            `when`(getSuggestedPortionUseCase.invoke(token, listOf(1L)))
                    .thenReturn(Result.Success(listOf(menuProductDetailDTOsMocks.suggestedPortions)))

            val result = groupProductsBySizeIdUseCase.invoke(token, listOf(menuProductDetailDTOsMocks.menuProductResponseDTO))

            if (result is Result.Success) {
                assertEquals(result.data.sizes.size, 1)
                assertEquals(result.data.sizes.first().groups.size, 1)
                assertEquals(result.data.sizes.first().groups.first().portions.size, 1)

                assertEquals(
                        mockDataResponse.sizes,
                        result.data.sizes
                )

            }
        }
    }

    @Test
    fun test_Group_Products_By_SizeId_UseCase_Returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                `when`(getSuggestedPortionUseCase.invoke(token, listOf(1L)))
                        .thenThrow(
                                ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Error use case"
                                )
                        )
                groupProductsBySizeIdUseCase.invoke(token, listOf(menuProductDetailDTOsMocks.menuProductResponseDTO))
            }
        }
    }

    @Test
    fun test_Group_Products_By_SizeId_UseCase_Returns_Suggested_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                `when`(getSuggestedPortionUseCase.invoke(token, listOf(1L)))
                        .thenReturn(
                                Result.Error(Exception("Some error"),
                                        HttpStatus.BAD_REQUEST
                                )
                        )
                groupProductsBySizeIdUseCase.invoke(
                        token,
                        listOf(menuProductDetailDTOsMocks.menuProductResponseDTO)
                )
            }
        }
    }

    @Test
    fun test_Group_Products_By_SizeId_suggestedPortions_id_not_Equals_UseCase_Returns_Correctly() {
        runBlocking {
            val mockDataResponse = menuProductDetailDTOsMocks.menuProductDetailDTO

            menuProductDetailDTOsMocks.suggestedPortions.id = 1000L

            `when`(getSuggestedPortionUseCase.invoke(token, listOf(1L)))
                    .thenReturn(Result.Success(listOf(menuProductDetailDTOsMocks.suggestedPortions)))

            val result = groupProductsBySizeIdUseCase.invoke(token, listOf(menuProductDetailDTOsMocks.menuProductResponseDTO))

            if (result is Result.Success) {
                assertEquals(result.data.sizes.size, 1)
                assertEquals(result.data.sizes.first().groups.size, 1)
                assertEquals(result.data.sizes.first().groups.first().portions.size, 1)
            }
        }
    }


}