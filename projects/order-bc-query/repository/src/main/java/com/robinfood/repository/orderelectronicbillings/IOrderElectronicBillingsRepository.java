package com.robinfood.repository.orderelectronicbillings;

import com.robinfood.core.entities.OrderElectronicBillingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IOrderElectronicBillingsRepository extends CrudRepository<OrderElectronicBillingEntity, Long> {

    Optional<OrderElectronicBillingEntity>findByOrderId(@Param("orderId") Long orderId);

    @Query(value = " SELECT JSON_MERGE(" +
                   " JSON_EXTRACT(p.response_payload, :basicDataObjectInJsonPayload), " +
                   " JSON_OBJECT('QR', JSON_EXTRACT(p.response_payload, '$.QR'))) AS data" +
                   " FROM orders.order_electronic_billings p " +
                   " WHERE p.order_id = :orderId AND p.status_code = :statusCode",
            nativeQuery = true)
    Optional<String> findByOrderIdAndStatusCodeAccepted(@Param("orderId") Long orderId,
           @Param("basicDataObjectInJsonPayload") String basicDataObjectInJsonPayload,
           @Param("statusCode") Integer statusCode);
}
