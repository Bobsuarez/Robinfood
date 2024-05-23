package com.robinfood.repository.orderdiscount;

import com.robinfood.core.entities.OrderDiscountEntity;

import java.util.List;

public class OrderDiscountLocalDatasource implements IOrderDiscountLocalDatasource {

    // Locally stored order discounts
    private List<OrderDiscountEntity> orderDiscountEntitiesStored;

    @Override
    public List<OrderDiscountEntity> getCurrentOrderDiscountsStored() {
        return orderDiscountEntitiesStored;
    }

    @Override
    public void setLocalOrderDiscountsEntities(List<OrderDiscountEntity> transactionEntity) {
        orderDiscountEntitiesStored = transactionEntity;
    }
}
