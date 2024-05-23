package com.robinfood.app.usecases.deleteorder

/**
 * Use case that changes the state of an order
 */
interface IDeleteOrderUseCase {
    /**
     * Calls repository in order to change the state of an order
     * @param orderId is the id of the order
     * @return true if order state is changed successfully, otherwise returns false
     */
    fun invoke(orderId: Long): Boolean
}