package com.robinfood.repository.paymentmethod;

import com.robinfood.core.entities.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository that handles payment method data
 */
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Long> {

    List<PaymentMethodEntity> findByStatusId(Integer statusId);

}
