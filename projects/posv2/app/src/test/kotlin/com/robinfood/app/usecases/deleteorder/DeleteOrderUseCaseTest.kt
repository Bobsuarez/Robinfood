package com.robinfood.app.usecases.deleteorder

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.InjectMocks
import org.mockito.Mock
import com.robinfood.repository.orders.IOrdersRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

@ExtendWith(MockitoExtension::class)
class DeleteOrderUseCaseTest {

    @InjectMocks
    private lateinit var deleteOrderUseCase: DeleteOrderUseCase

    @Mock
    private lateinit var mockOrdersRepository: IOrdersRepository

    @Test
    fun test_DeleteOrder_Returns_Correctly() {
        val orderId: Long = 1234
        Mockito.`when`(mockOrdersRepository.deleteOrder(orderId)).thenReturn(true)
        val result = deleteOrderUseCase.invoke(orderId)
        Assertions.assertThat(result).isEqualTo(true)
    }
}