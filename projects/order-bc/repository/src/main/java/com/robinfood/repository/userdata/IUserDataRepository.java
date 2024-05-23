package com.robinfood.repository.userdata;

import com.robinfood.core.entities.OrderUserDataEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository that handles user data information
 */
public interface IUserDataRepository extends CrudRepository<OrderUserDataEntity, Long> {

    Optional<OrderUserDataEntity> findByOrderId(Long orderId);

    List<OrderUserDataEntity> findAllByOrderIdIn(List<Long> orderId);

    List<OrderUserDataEntity> findByUserIdAndCreatedAtBetween(
            Long userId,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime
    );

    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
