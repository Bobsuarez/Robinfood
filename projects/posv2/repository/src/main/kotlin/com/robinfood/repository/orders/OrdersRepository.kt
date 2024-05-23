package com.robinfood.repository.orders

import org.springframework.stereotype.Repository

/**
 * Implementation of 'IOrdersRepository' change state cancelled
 */
@Repository
class OrdersRepository : IOrdersRepository {

    override fun deleteOrder(orderId: Long): Boolean {
        return true
    }
}