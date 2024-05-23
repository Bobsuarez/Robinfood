package com.robinfood.repository.payment;


import com.robinfood.core.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository that handles transaction data
 */
public interface IPaymentRepository extends CrudRepository<PaymentEntity, Long> {
    void deleteAllByTransactionId(Long orderIds);
}
