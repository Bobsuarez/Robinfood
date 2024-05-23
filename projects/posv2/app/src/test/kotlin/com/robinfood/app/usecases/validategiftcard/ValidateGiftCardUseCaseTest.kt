package com.robinfood.app.usecases.validategiftcard

import com.robinfood.app.mocks.gifcard.ValidateGiftCardDTOMock
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.util.ReflectionTestUtils

@ExtendWith(MockitoExtension::class)
class ValidateGiftCardUseCaseTest {

    @InjectMocks
    private lateinit var validateGiftCardUseCase: ValidateGiftCardUseCase

    @BeforeEach
    fun setUp() {
        ReflectionTestUtils.setField(
                validateGiftCardUseCase, "giftCardDisplayTypeIds", listOf(1L))

        ReflectionTestUtils.setField(
                validateGiftCardUseCase, "giftCardProductMethodId", 0L)
    }

    private val categoryIdsWithGiftCardId: List<Long> = listOf(1L, 3L)
    private val categoryIdsWithOutGiftCardId: List<Long> = listOf(8L, 3L)

    @Test
    fun test_ValidateGiftCard_When_DisplayTypeIds_IsNotIncluded_Should_True() {

        runBlocking {

            val validateGiftCardDTO = ValidateGiftCardDTOMock().validateGiftCardDTO
            val isEnableButton = validateGiftCardUseCase.invoke(categoryIdsWithGiftCardId);

            assertEquals(validateGiftCardDTO, isEnableButton)
        }
    }

    @Test
    fun test_ValidateGiftCard_When_DisplayTypeIds_IsIncluded_Should_False() {

        runBlocking {

            val validateGiftCardDTO = ValidateGiftCardDTOMock().validateGiftCardDTO
            validateGiftCardDTO.isEnableButton = true
            val isEnableButton = validateGiftCardUseCase.invoke(categoryIdsWithOutGiftCardId);

            assertEquals(validateGiftCardDTO, isEnableButton)
        }
    }
}
