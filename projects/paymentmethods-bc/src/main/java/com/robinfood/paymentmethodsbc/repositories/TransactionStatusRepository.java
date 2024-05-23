package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * TransactionStatus, utilizando como interface JpaRepository
 * @author Edwin Artunduaga
 */
public interface TransactionStatusRepository
    extends JpaRepository<TransactionStatus, Long> {
    Optional<TransactionStatus> findById(Long id);
}
