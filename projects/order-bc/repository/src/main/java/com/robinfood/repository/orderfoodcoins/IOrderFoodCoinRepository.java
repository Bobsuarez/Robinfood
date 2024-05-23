package com.robinfood.repository.orderfoodcoins;

import com.robinfood.core.entities.OrderFoodCoinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderFoodCoinRepository extends CrudRepository<OrderFoodCoinEntity,Long> {

    OrderFoodCoinEntity findByOrderId (Long orderId);

    void deleteAllByOrderIdIsIn(List<Long> ordersIds);
}
