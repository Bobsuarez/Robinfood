package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.CountryPaymentGatewaySetting;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * CountryPaymentGatewaySetting para el uso de CRUD, utilizando como interface JpaRepository
 * @author Hernán A. Ramírez Osorio
 */
@Repository
public interface CountryPaymentGatewaySettingsRepository extends JpaRepository<CountryPaymentGatewaySetting, Long> {

    List<CountryPaymentGatewaySetting> findByCountryId(Long countryId);

    Optional<List<CountryPaymentGatewaySetting>> findByCountryIdAndPaymentGatewayId(
        Long countryId,
        Long paymentGatewayId
    );
}
