package com.robinfood.ordereports_bc_muyapp.repository.paymentmethod;


import com.robinfood.ordereports_bc_muyapp.models.entities.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that handles payment method data
 */
@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Short> {
}
