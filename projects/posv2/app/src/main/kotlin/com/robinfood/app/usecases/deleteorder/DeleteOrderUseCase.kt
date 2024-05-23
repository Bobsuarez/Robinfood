package com.robinfood.app.usecases.deleteorder

import com.robinfood.repository.orders.IOrdersRepository

/**
 * Implementation of 'IDeleteOrderUseCase'
 */
class DeleteOrderUseCase(
    private val ordersRepository: IOrdersRepository
) : IDeleteOrderUseCase {

    override fun invoke(orderId: Long): Boolean {
        return ordersRepository.deleteOrder(orderId)
    }
}