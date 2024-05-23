package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * PaymentMethod para el uso de CRUD, utilizando como interface JpaRepository
 * @author Edwin Artunduaga
 */
public interface PaymentMethodsRepository
    extends JpaRepository<PaymentMethod, Long> {}
