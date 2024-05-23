package com.robinfood.changestatusbc.repositories.orders;

import com.robinfood.changestatusbc.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrdersRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByUuid(String uid);
}
