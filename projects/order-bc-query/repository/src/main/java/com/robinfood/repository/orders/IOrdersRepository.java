package com.robinfood.repository.orders;


import com.robinfood.core.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository that handles orders data
 */
public interface IOrdersRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

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

    Page<OrderEntity> findAllByStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long storeId,
            List<Long> deliveryTypeIds,
            Boolean paid,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime,
            Pageable pageable
    );

    @Query("SELECT orderEntity FROM OrderEntity orderEntity WHERE "
            + " (orderEntity.storeId = :storeId) "
            + " AND (orderEntity.paid = :paid)"
            + " AND (orderEntity.deliveryTypeId IN :deliveryTypeIds)"
            + " AND (orderEntity.brandName like '%'||:searchText||'%' "
            + " OR orderEntity.orderInvoiceNumber like '%'||:searchText||'%' "
            + " OR orderEntity.id IN :orderIds)"
            + " AND (orderEntity.createdAt >= :localDateTimeStart AND orderEntity.createdAt <= :localDateTimeEnd)")
    Page<OrderEntity> findAllByFilterLikeBrandNameAndOrderInvoiceNumberCreatedAtBetweenOrderByCreatedAtDesc(
            @Param("deliveryTypeIds") List<Long> deliveryTypeIds,
            @Param("localDateTimeStart") LocalDateTime localDateTimeStart,
            @Param("localDateTimeEnd") LocalDateTime localDateTimeEnd,
            @Param("orderIds") List<Long> orderIds,
            @Param("paid") Boolean paid,
            @Param("pageable") Pageable pageable,
            @Param("searchText") String searchText,
            @Param("storeId") Long storeId
    );

    @Query("SELECT COALESCE(SUM(total), 0) " +
            "FROM OrderEntity orderEntity " +
            "WHERE orderEntity.localDate = :dateToSearch " +
            "  AND orderEntity.companyId = :idCompany " +
            "  AND orderEntity.paid = :paid " +
            "  AND (orderEntity.localTime BETWEEN :initialTime AND :finalTime) " +
            "  AND (orderEntity.statusId NOT IN :statusIds)")
    BigDecimal sumTotalByLocalDateAndCompanyIdAndPaidAndLocalTimeBetweenAndStatusIdNotIn(
            @Param("dateToSearch") LocalDate dateToSearch,
            @Param("finalTime") LocalTime finalTime,
            @Param("idCompany") Long idCompany,
            @Param("initialTime") LocalTime initialTime,
            @Param("paid") Boolean paid,
            @Param("statusIds") List<Long> statusIds
    );

    @Query("SELECT orderEntity FROM OrderEntity orderEntity WHERE "
            + " (orderEntity.storeId = :storeId) "
            + " AND (orderEntity.originId IN :originIds)"
            + " AND (orderEntity.paid = :paid)"
            + " AND (orderEntity.deliveryTypeId IN :deliveryTypeIds)"
            + " AND (orderEntity.brandName like '%'||:searchText||'%' "
            + " OR orderEntity.orderInvoiceNumber like '%'||:searchText||'%' "
            + " OR orderEntity.id IN :orderIds)"
            + " AND (orderEntity.createdAt >= :localDateTimeStart AND orderEntity.createdAt <= :localDateTimeEnd)")
    Page<OrderEntity> findAllByFilterLikeBrandNameAndOrderInvoiceNumberAndOriginIdsCreatedAtBetweenOrderByCreatedAtDesc(
            @Param("deliveryTypeIds") List<Long> deliveryTypeIds,
            @Param("localDateTimeStart") LocalDateTime localDateTimeStart,
            @Param("localDateTimeEnd") LocalDateTime localDateTimeEnd,
            @Param("orderIds") List<Long> orderIds,
            @Param("originIds") List<Long> originIds,
            @Param("paid") Boolean paid,
            @Param("pageable") Pageable pageable,
            @Param("searchText") String searchText,
            @Param("storeId") Long storeId
    );

    Page<OrderEntity> findAllByOriginIdInAndStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
            List<Long> originId,
            Long storeId,
            List<Long> deliveryTypeIds,
            Boolean paid,
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime,
            Pageable pageable
    );

    void deleteAllByIdIsIn(List<Long> orderIds);

    List<OrderEntity> findOrderEntitiesByTransactionId(Long transactionId);

    Optional<List<OrderEntity>> findByCreatedAtBetweenAndPaidAndPosIdOrderByCreatedAtDesc(
            LocalDateTime initialDateTime,
            LocalDateTime finalDateTime,
            Boolean paid,
            Long posId
    );

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

    boolean existsByUuidInAndPaid(List<String> uuids, Boolean paid);

    Optional<OrderEntity> findByUuid(String uid);

    Optional<List<OrderEntity>> findByCreatedAtBetweenAndPaidAndPosId(
            LocalDateTime initialDate,
            LocalDateTime finalDate,
            Boolean paid,
            Long posId
    );

    Optional<List<OrderEntity>> findByCreatedAtBetweenAndPaidAndPosIdAndStatusIdNot(
            LocalDateTime initialDate,
            LocalDateTime finalDate,
            Boolean paid,
            Long posId,
            Long statusId
    );

    Optional<List<OrderEntity>> findByCreatedAtBetweenAndPaidAndStoreIdOrderByCreatedAtAsc(
            LocalDateTime initialDate,
            LocalDateTime finalDate,
            Boolean paid,
            Long storeId
    );
}
