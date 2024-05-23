package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.CountryPaymentGateway;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * CountryPaymentGateway para el uso de CRUD, utilizando como interface JpaRepository
 * @author Hernán A. Ramírez Osorio
 */
public interface CountryPaymentGatewaysRepository
    extends JpaRepository<CountryPaymentGateway, Long> {
    List<CountryPaymentGateway> findByCountryIdAndCreditCardTokenizationEnabled(
        Long countryId,
        Boolean creditCardTokenizationEnabled
    );

    Optional<CountryPaymentGateway> findFirstByCountryIdOrderByPriorityAsc(
        Long countryId
    );
}
