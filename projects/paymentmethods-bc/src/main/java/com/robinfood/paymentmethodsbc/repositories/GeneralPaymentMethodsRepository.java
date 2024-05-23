package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralPaymentMethodsRepository
    extends JpaRepository<GeneralPaymentMethod, Long> {
    Optional<GeneralPaymentMethod> findByIdAndStatus(Long id, Boolean status);
}
