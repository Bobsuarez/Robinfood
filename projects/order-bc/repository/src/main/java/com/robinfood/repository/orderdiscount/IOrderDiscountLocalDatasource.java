package com.robinfood.repository.orderdiscount;

import com.robinfood.core.entities.OrderDiscountEntity;

import java.util.List;

/**
 * Interface in order to manage the local datasource related to order discounts data
 */
public interface IOrderDiscountLocalDatasource {

    /**
     * Returns the current stored order discounts
     *
     * @return the current stored order discounts
     */
    List<OrderDiscountEntity> getCurrentOrderDiscountsStored();

    /**
     * Set the order discounts entities data stored locally
     *
     * @param orderDiscountsEntities the order discounts data
     */
    void setLocalOrderDiscountsEntities(List<OrderDiscountEntity> orderDiscountsEntities);
}
