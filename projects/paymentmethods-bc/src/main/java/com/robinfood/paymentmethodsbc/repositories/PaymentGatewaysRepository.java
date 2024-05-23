package com.robinfood.paymentmethodsbc.repositories;

import java.util.Optional;

import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * PaymentGateway para el uso de CRUD, utilizando como interface JpaRepository
 * @author Hernán A. Ramírez Osorio
 */
public interface PaymentGatewaysRepository extends JpaRepository<PaymentGateway, Long> {
    Optional<PaymentGateway> findById(Long id);
}
