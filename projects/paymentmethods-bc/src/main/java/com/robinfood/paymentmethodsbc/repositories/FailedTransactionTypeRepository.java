package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.FailedTransactionType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedTransactionTypeRepository
    extends JpaRepository<FailedTransactionType, Long> {
    Optional<FailedTransactionType> findById(Long id);
}
