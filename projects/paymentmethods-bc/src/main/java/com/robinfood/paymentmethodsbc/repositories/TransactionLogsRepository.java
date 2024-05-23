package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * TransactionLog para el uso de CRUD, utilizando como interface JpaRepository
 */
@Repository
public interface TransactionLogsRepository extends JpaRepository<TransactionLog, Long> {
}
