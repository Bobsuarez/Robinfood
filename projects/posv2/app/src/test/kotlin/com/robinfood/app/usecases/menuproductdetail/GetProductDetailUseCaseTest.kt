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
class GetProductDetailUseCaseTest {

    private val menuProductDetailDTOsMocks = MenuProductDetailDTOsMocks()

    private val finalProductIds: List<Long> = listOf(1L)

    private val token =
            "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"

    @Mock
    private lateinit var mockGetMenuProductUseCase: IGetMenuProductUseCase

    @Mock
    private lateinit var mockGroupProductsBySizeIdUseCase: IGroupProductsBySizeIdUseCase

    @InjectMocks
    private lateinit var getProductDetailUseCase: GetProductDetailUseCase

    @Test
    fun test_Get_Product_Detail_Returns_Correctly() {
        runBlocking {
            `when`(mockGetMenuProductUseCase.invoke(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    finalProductIds
            )).thenReturn(Result.Success(listOf(menuProductDetailDTOsMocks.menuProductResponseDTO)))

            `when`(mockGroupProductsBySizeIdUseCase.invoke(
                    token,
                    listOf(menuProductDetailDTOsMocks.menuProductResponseDTO)
            )).thenReturn(Result.Success(menuProductDetailDTOsMocks.menuProductDetailDTO))

            val result = getProductDetailUseCase.invoke(
                    token,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    finalProductIds
            )

            assertEquals(Result.Success(menuProductDetailDTOsMocks.menuProductDetailDTO), result)
        }
    }

    @Test
    fun test_Get_Product_Detail_Returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                `when`(mockGetMenuProductUseCase.invoke(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        finalProductIds
                )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getProductDetailUseCase.invoke(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        finalProductIds
                )
            }
        }
    }

    @Test
    fun test_Get_Product_Detail_Change_Portion_Returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                `when`(mockGetMenuProductUseCase.invoke(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        finalProductIds
                )).thenReturn(Result.Success(listOf(menuProductDetailDTOsMocks.menuProductResponseDTO)))

                `when`(mockGroupProductsBySizeIdUseCase.invoke(
                        token,
                        listOf(menuProductDetailDTOsMocks.menuProductResponseDTO)
                )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getProductDetailUseCase.invoke(
                        token,
                        1L,
                        1L,
                        1L,
                        1L,
                        1L,
                        finalProductIds
                )
            }
        }
    }
}
