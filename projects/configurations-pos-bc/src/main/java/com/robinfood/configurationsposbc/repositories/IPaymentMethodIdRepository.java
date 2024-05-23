package com.robinfood.configurationsposbc.repositories;

import com.robinfood.configurationsposbc.entities.PaymentMethodIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentMethodIdRepository extends JpaRepository<PaymentMethodIdEntity, Long> {

}
