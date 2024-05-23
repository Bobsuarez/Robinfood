package com.robinfood.repository.orders

/**
 * Repository that retrieves or sends all orders data to the different sources of truth
 */
interface IOrdersRepository {

    /**
     * Changes the state of an order
     * @param orderId is the order id
     * @return true if the order state is changed successfully, otherwise returns false
     */
    fun deleteOrder(orderId: Long): Boolean
}