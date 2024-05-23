package com.robinfood.repository.paymentmethod;


import com.robinfood.core.entities.PaymentMethodEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository that handles payment method data
 */
public interface IPaymentMethodRepository extends CrudRepository<PaymentMethodEntity, Long> {

}
