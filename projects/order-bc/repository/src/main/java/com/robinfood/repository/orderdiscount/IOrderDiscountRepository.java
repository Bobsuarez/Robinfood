package com.robinfood.repository.orderdiscount;

import com.robinfood.core.entities.OrderDiscountEntity;

import java.util.List;

/**
 * Repository that handles order discounts data
 */
public interface IOrderDiscountRepository {

    /**
     * Returns the current stored order discounts from local datasource
     *
     * @return current stored order discounts
     */
    List<OrderDiscountEntity> getLocalOrderDiscounts();

    /**
     * Set the order discounts entities data stored in local datasource
     *
     * @param orderDiscountEntities the order discounts data to be stored
     */
    void setLocalOrderDiscounts(List<OrderDiscountEntity> orderDiscountEntities);
}
