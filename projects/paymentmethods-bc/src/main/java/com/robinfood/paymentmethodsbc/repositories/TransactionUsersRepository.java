package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.TransactionUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * Transaction para el uso de CRUD, utilizando como interface JpaRepository
 * @author Wilson Guerrero <wguerrero@robinfood.com>
 */
public interface TransactionUsersRepository
    extends JpaRepository<TransactionUser, Long> {}
