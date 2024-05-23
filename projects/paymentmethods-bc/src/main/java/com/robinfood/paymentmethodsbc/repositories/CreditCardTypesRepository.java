package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.CreditCardType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * credit_card_type para el uso de CRUD, utilizando como interface JpaRepository
 * @author Edwin Artunduaga
 */
@Repository
public interface CreditCardTypesRepository
    extends JpaRepository<CreditCardType, Long> {

    Optional<CreditCardType> findByCode(String code);
}
