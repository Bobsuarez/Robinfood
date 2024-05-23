package com.robinfood.app.controllers.store

import com.robinfood.app.POSApplication
import com.robinfood.app.usecases.getstoredeliveryplatforms.IGetStoreDeliveryPlatformsUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.STORE
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
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
import java.util.Collections

@AutoConfigureMockMvc
@SpringBootTest(classes = [POSApplication::class])
@TestPropertySource(properties = [
    "jwt-token-prefix=Bearer ",
    "jwt-token-secret=secretForTesting",
    "jwt-token-aud=internal",
    "jwt-token-mod=posv2"
])
class StoreControllerTest {

    companion object {
        private const val BEARER_AUTH = "Bearer "
        private const val TOKEN =
                "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var mockGetStoreDeliveryPlatformsUseCase: IGetStoreDeliveryPlatformsUseCase

    private val page = 1
    private val size = 1
    private val storeId = 1L

    private val storeDeliveryPlatformsResponseDTOs = listOf(
            StoreDeliveryPlatformDTO(
                    color = "FFFFFF",
                    flowId = 1L,
                    id = 1L,
                    imageUrl = "image.png",
                    name = "Platform 1",
                    slug = "Platform 1",
                    status = 1L
            ),
            StoreDeliveryPlatformDTO(
                    color = "FFFFFF",
                    flowId = 2L,
                    id = 2L,
                    imageUrl = "image.png",
                    name = "Platform 2",
                    slug = "Platform 2",
                    status = 2L
            )
    )

    private val emptyStoreDeliveryPlatformsResponseDTOs = Collections.emptyList<StoreDeliveryPlatformDTO>()

    @Test
    @Throws(Exception::class)
    fun test_GetDeliveryPlatforms_Should_Return_Ok() {
        runBlocking {
            `when`(mockGetStoreDeliveryPlatformsUseCase.invoke(
                    page,
                    size,
                    storeId,
                    BEARER_AUTH + TOKEN,
            )).thenReturn(Result.Success(storeDeliveryPlatformsResponseDTOs))
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("$API_V1$STORE/$storeId/delivery-platforms")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("page", "1")
                    .queryParam("size", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()

            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isOk)
                    .andExpect(content().json(ApiResponseDTO(
                            storeDeliveryPlatformsResponseDTOs,
                            "Store delivery platforms retrieved successfully"
                    ).toJson()))

        }
    }

    @Test
    @Throws(Exception::class)
    fun test_GetDeliveryPlatforms_Should_Return_Ok_with_platforms_empty() {
        runBlocking {
            `when`(mockGetStoreDeliveryPlatformsUseCase.invoke(
                    page,
                    size,
                    storeId,
                    BEARER_AUTH + TOKEN,
            )).thenReturn(Result.Success(emptyStoreDeliveryPlatformsResponseDTOs))
            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("$API_V1$STORE/$storeId/delivery-platforms")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("page", "1")
                    .queryParam("size", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()

            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isNoContent)
                    .andExpect(content().json(ApiResponseDTO(
                            emptyStoreDeliveryPlatformsResponseDTOs,
                            "There are no platforms assigned to the store"
                    ).toJson()))

        }
    }


    @Test
    @Throws(Exception::class)
    fun test_GetDeliveryPlatforms_Should_Return_Failure() {
        runBlocking {
            `when`(mockGetStoreDeliveryPlatformsUseCase.invoke(
                    page,
                    size,
                    storeId,
                    BEARER_AUTH + TOKEN,
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

            val result = mockMvc.perform(MockMvcRequestBuilders
                    .get("$API_V1$STORE/$storeId/delivery-platforms")
                    .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                    .queryParam("page", "1")
                    .queryParam("size", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn()

            mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isBadRequest)
        }
    }
}