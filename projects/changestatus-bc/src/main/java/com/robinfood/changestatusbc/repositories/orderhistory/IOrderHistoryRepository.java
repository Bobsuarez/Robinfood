package com.robinfood.changestatusbc.repositories.orderhistory;

import com.robinfood.changestatusbc.entities.OrderHistoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOrderHistoryRepository extends CrudRepository<OrderHistoryEntity, Long> {

}
