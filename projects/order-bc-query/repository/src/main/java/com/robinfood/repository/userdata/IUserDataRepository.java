package com.robinfood.repository.userdata;

import com.robinfood.core.entities.OrderUserDataEntity;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository that handles user data information
 */
public interface IUserDataRepository extends CrudRepository<OrderUserDataEntity, Long> {

    Optional<OrderUserDataEntity> findByOrderId(Long orderId);

    List<OrderUserDataEntity> findAllByUserIdIn(List<Long> userIds);

    List<OrderUserDataEntity> findAllByOrderIdIn(List<Long> orderId);

    List<OrderUserDataEntity> findByUserIdAndCreatedAtBetween(
            Long userId,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime
    );

    @Query("SELECT userDataEntity FROM OrderUserDataEntity userDataEntity WHERE "
            + " (userDataEntity.storeId = :storeId) "
            + " AND (userDataEntity.firstName like :firstName||'%' "
            + " OR userDataEntity.lastName like :lastName||'%') "
            + " AND (userDataEntity.createdAt >= :localDateTimeStart AND userDataEntity.createdAt <= :localDateTimeEnd)")
    List<OrderUserDataEntity> findAllByFirstNameContainingOrLastNameContainingAndCreatedAtBetween(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("localDateTimeStart") LocalDateTime localDateTimeStart,
            @Param("localDateTimeEnd") LocalDateTime localDateTimeEnd,
            @Param("storeId") Long storeId
    );

    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
