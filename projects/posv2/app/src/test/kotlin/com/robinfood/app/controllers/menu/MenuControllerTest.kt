package com.robinfood.app.controllers.menu

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.robinfood.app.POSApplication
import com.robinfood.app.mocks.MenuProductDetailDTOsMocks
import com.robinfood.app.usecases.getmenu.IGetMenuUseCase
import com.robinfood.app.usecases.menuproductdetail.IGetProductDetailUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.MENU
import com.robinfood.core.constants.APIConstants.MENU_PRODUCT_DETAIL
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.menu.MenuBaseProductDTO
import com.robinfood.core.dtos.menu.MenuBrandDTO
import com.robinfood.core.dtos.menu.MenuCategoryDTO
import com.robinfood.core.dtos.menu.MenuDTO
import com.robinfood.core.dtos.menu.MenuSizeDTO
import com.robinfood.core.entities.menu.MenuArticleDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@AutoConfigureMockMvc
@SpringBootTest(classes = [POSApplication::class])
@TestPropertySource(properties = [
    "jwt-token-prefix=Bearer ",
    "jwt-token-secret=secretForTesting",
    "jwt-token-aud=internal",
    "jwt-token-mod=posv2"
])
class MenuControllerTest {

    companion object {
        private const val BEARER_AUTH = "Bearer "
        private const val TOKEN =
                "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"
    }

    private val menuProductDetailDTOsMocks = MenuProductDetailDTOsMocks()

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var mockGetMenuUseCase: IGetMenuUseCase

    @MockBean
    private lateinit var mockGetProductDetailUseCase: IGetProductDetailUseCase

    private val countryId = 1L
    private val flowId = 1L
    private val storeId = 1L
    private val finalProductIds: List<Long> = listOf(1L)


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
                    1,
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

    private val menuDTO = MenuDTO(listOf(menuBrandDTO))

    @Test
    @Throws(Exception::class)
    fun test_GetMenu_Should_Return_Ok() {
        runBlocking {
            `when`(mockGetMenuUseCase.invoke(
                    BEARER_AUTH + TOKEN,
                    countryId,
                    flowId,
                    storeId
            )).thenReturn(Result.Success(menuDTO))
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("$API_V1$MENU/$storeId")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("countryId", "1")
                    .queryParam("flowId", "1")
                    .queryParam("storeId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()

            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isOk)
                    .andExpect(content().json(ApiResponseDTO(menuDTO, "Menu retrieved successfully").toJson()))

        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetMenu_Should_Return_Failure() {
        runBlocking {
            `when`(mockGetMenuUseCase.invoke(
                    BEARER_AUTH + TOKEN,
                    countryId,
                    flowId,
                    storeId
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("$API_V1$MENU/$storeId")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("countryId", "1")
                    .queryParam("flowId", "1")
                    .queryParam("storeId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()

            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isBadRequest)
        }
    }

    @Test
    fun test_ProductDetail_Should_Return_Ok() {
        runBlocking {
            `when`(mockGetProductDetailUseCase.invoke(
                    BEARER_AUTH + TOKEN,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    finalProductIds
            )).thenReturn(Result.Success(menuProductDetailDTOsMocks.menuProductDetailDTO))

            val mapper = ObjectMapper()
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("${API_V1}${MENU_PRODUCT_DETAIL}")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("final_product_ids", "1")
                    .queryParam("country_id", "1")
                    .queryParam("brand_id", "1")
                    .queryParam("flow_id", "1")
                    .queryParam("store_id", "1")
                    .queryParam("platform_id", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isOk)
                    .andExpect(content().json(ApiResponseDTO(menuProductDetailDTOsMocks.menuProductDetailDTO, "OK").toJson()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_ProductDetail_Should_Return_menu_product_Failure() {
        runBlocking {
            `when`(mockGetProductDetailUseCase.invoke(
                    BEARER_AUTH + TOKEN,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    finalProductIds,
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            val mapper = ObjectMapper()
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("${API_V1}${MENU_PRODUCT_DETAIL}")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("final_product_ids", "1")
                    .queryParam("country_id", "1")
                    .queryParam("brand_id", "1")
                    .queryParam("flow_id", "1")
                    .queryParam("store_id", "1")
                    .queryParam("platform_id", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isBadRequest)
        }
    }
}