package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.FailedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedTransactionRepository
    extends JpaRepository<FailedTransaction, Long> {}
