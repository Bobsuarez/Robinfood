package com.robinfood.app.usecases.getstoredeliveryplatforms

import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.store.IStoreRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GetStoreDeliveryPlatformsUseCaseTest {

    @Mock
    private lateinit var storeRepository: IStoreRepository

    @InjectMocks
    private lateinit var getStoreDeliveryPlatformsUseCase: GetStoreDeliveryPlatformsUseCase

    private val page = 1
    private val size = 1
    private val storeId = 1L
    private val token = "Token"

    private val storeDeliveryPlatformsResponse = listOf(
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

    @Test
    fun test_GetStoreDeliveryPlatforms_when_service_returns_OK() {
        runBlocking {
            `when`(
                    storeRepository.getStoreDeliveryPlatforms(page, size, storeId, token)
            ).thenReturn(Result.Success(storeDeliveryPlatformsResponse))

            val result = getStoreDeliveryPlatformsUseCase.invoke(
                    page,
                    size,
                    storeId,
                    token
            )

            assertEquals(Result.Success(storeDeliveryPlatformsResponse), result)
        }
    }
}
