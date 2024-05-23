package com.robinfood.repository.orderdiscount;

import com.robinfood.core.entities.OrderDiscountEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDiscountRepository implements IOrderDiscountRepository {

    private final IOrderDiscountLocalDatasource orderDiscountLocalDatasource;

    public OrderDiscountRepository(IOrderDiscountLocalDatasource orderDiscountLocalDatasource) {
        this.orderDiscountLocalDatasource = orderDiscountLocalDatasource;
    }

    @Override
    public void setLocalOrderDiscounts(List<OrderDiscountEntity> orderDiscountEntities) {
        orderDiscountLocalDatasource.setLocalOrderDiscountsEntities(orderDiscountEntities);
    }

    @Override
    public List<OrderDiscountEntity> getLocalOrderDiscounts() {
        return orderDiscountLocalDatasource.getCurrentOrderDiscountsStored();
    }
}
