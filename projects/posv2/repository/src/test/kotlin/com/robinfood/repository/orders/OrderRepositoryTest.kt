package com.robinfood.repository.orders

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.InjectMocks
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@ExtendWith(MockitoExtension::class)
class OrdersRepositoryTest {

    @InjectMocks
    private lateinit var orderRepository: OrdersRepository

    @Test
    fun test_DeleteOrder_Repository_Returns_Correctly() {
        val orderId: Long = 1234
        val result = orderRepository.deleteOrder(orderId)
        Assertions.assertThat(result).isEqualTo(true)
    }
}
