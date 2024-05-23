package com.robinfood.app.controllers.crosssellingmenu

import com.robinfood.app.POSApplication
import com.robinfood.app.mocks.MenuProductDetailDTOsMocks
import com.robinfood.app.usecases.crosssellingmenu.IGetCrossSellingMenuUseCase
import com.robinfood.core.constants.APIConstants
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.crosssellingmenu.CrossSellingMenuDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest(classes = [POSApplication::class])
@TestPropertySource(properties = [
    "jwt-token-prefix=Bearer ",
    "jwt-token-secret=secretForTesting",
    "jwt-token-aud=internal",
    "jwt-token-mod=posv2"
])
class CrossSellingMenuControllerTest {

    companion object {
        private const val BEARER_AUTH = "Bearer "
        private const val TOKEN =
                "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"
    }

    private val countryId = 1L
    private val flowId = 1L
    private val storeId = 1L

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var mockGetCrossSellingMenuUseCase: IGetCrossSellingMenuUseCase

    private val menuProductDetailDTOsMocks = MenuProductDetailDTOsMocks()

    private val crossellingMenuDto = CrossSellingMenuDTO(listOf(menuProductDetailDTOsMocks.menuProductDetailCrossSellingDTO))



    @Test
    @Throws(Exception::class)
    fun test_GetCrossSellingMenu_Should_Return_Ok() {
        runBlocking {
            Mockito.`when`(mockGetCrossSellingMenuUseCase.invoke(
                    BEARER_AUTH + TOKEN,
                    countryId,
                    flowId,
                    storeId
            )).thenReturn(Result.Success(crossellingMenuDto))
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("${APIConstants.API_V1}${APIConstants.STORE}/$storeId/${APIConstants.CROSS_SELLING_MENU}")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("countryId", "1")
                    .queryParam("flowId", "1")
                    .queryParam("storeId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()

            mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(result))
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andExpect(MockMvcResultMatchers.content().json(ApiResponseDTO(crossellingMenuDto, "Cross-Selling Menu retrieved successfully").toJson()))

        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetCrossSellingMenu_Should_Return_Failure() {
        runBlocking {

            Mockito.`when`(mockGetCrossSellingMenuUseCase.invoke(
                    BEARER_AUTH + TOKEN,
                    countryId,
                    flowId,
                    storeId
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("${APIConstants.API_V1}${APIConstants.STORE}/$storeId/${APIConstants.CROSS_SELLING_MENU}")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("countryId", "1")
                    .queryParam("flowId", "1")
                    .queryParam("storeId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()

            mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(result))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest)
        }
    }
}