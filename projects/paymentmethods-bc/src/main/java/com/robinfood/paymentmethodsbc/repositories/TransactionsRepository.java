package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad Transaction para el uso de CRUD, utilizando
 * como interface JpaRepository
 *
 * @author Hernán A. Ramirez O.
 */
public interface TransactionsRepository extends JpaRepository<Transaction, Long>,
    JpaSpecificationExecutor<Transaction> {
    Optional<Transaction> findById(Long id);

    Optional<Transaction> findByUuid(String uuid);
}
