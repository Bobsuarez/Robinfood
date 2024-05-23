package com.robinfood.repository.orders;


import com.robinfood.core.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository that handles orders data
 */
public interface IOrdersRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUidIn(List<String> orderIds);

    List<OrderEntity> findAllByUuidIn(List<String> orderUuids);

    Page<OrderEntity> findAllByOriginIdAndStoreIdAndDeliveryTypeIdInAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long originId,
            Long storeId,
            List<Long> deliveryTypeIds,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime,
            Pageable pageable
    );

    Page<OrderEntity> findAllByStoreIdAndDeliveryTypeIdInAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long storeId,
            List<Long> deliveryTypeIds,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime,
            Pageable pageable
    );

    List<OrderEntity> findAllByStoreIdAndPaidAndFullSynchronizedAndStatusIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long storeId,
            Boolean paid,
            Integer fullSynchronized,
            Long statusId,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime
    );

    Page<OrderEntity> findAllByStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long storeId,
            List<Long> deliveryTypeIds,
            Boolean paid,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime,
            Pageable pageable
    );

    Page<OrderEntity> findAllByOriginIdAndStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long originId,
            Long storeId,
            List<Long> deliveryTypeIds,
            Boolean paid,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime,
            Pageable pageable
    );

    void deleteAllByIdIsIn(List<Long> orderIds);

    List<OrderEntity> findOrderEntitiesByTransactionId(Long transactionId);

    List<OrderEntity> findAllByTransactionIdOrderByCreatedAtAsc(Long transactionId);

    List<OrderEntity> findAllByStoreIdAndOperationDateAndPaidAndStatusIdNotIn(
        Long storeId,
        LocalDate initialDateTime,
        Boolean paid,
        List<Long> statusId
    );

    Page<OrderEntity> findAllByUserIdAndStatusIdNotInOrderByCreatedAtDesc(
        Long userId,
        List<Long> statusIds,
        Pageable pageable
    );

    Optional<OrderEntity> findByUidAndUserIdAndStatusIdNotIn(
        String uid,
        Long userId,
        List<Long> statusIds
    );

    List<OrderEntity> findAllByUserIdAndOriginIdAndStatusIdNotInAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long userId,
            Long originId,
            List<Long> statusIds,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime
    );

    @Query("SELECT o FROM  OrderEntity o WHERE "
            + " (:storeId is null or o.storeId = :storeId) "
            + " AND (:orderNumber is null or o.orderNumber like '%'||:orderNumber||'%' "
            + " OR :orderInvoiceNumber is null or o.orderInvoiceNumber like '%'||:orderInvoiceNumber||'%') "
            + " AND (o.createdAt >= :localDateTimeStart AND o.createdAt <= :localDateTimeEnd) ")
    Page<OrderEntity> findByStoreIdAndOrderNumberAndOrderInvoiceNumberContainingCreatedAtBetweenOrderByCreatedAtDesc(
            LocalDateTime localDateTimeEnd,
            LocalDateTime localDateTimeStart,
            Pageable pageable,
            String orderNumber,
            String orderInvoiceNumber,
            Long storeId
    );

    boolean existsByUuidInAndPaid(List<String> uuids, Boolean paid);

    Optional<OrderEntity> findByUuid(String uid);

}
