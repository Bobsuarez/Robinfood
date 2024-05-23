package com.robinfood.localorderbc.repositories;

import com.robinfood.localorderbc.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByStatusIdAndCreatedAtGreaterThanEqual(Long statusId, LocalDateTime currentDate);
}
