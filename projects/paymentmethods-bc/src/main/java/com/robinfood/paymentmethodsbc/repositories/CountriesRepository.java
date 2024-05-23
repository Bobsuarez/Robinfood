package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.Country;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * Campaign para el uso de CRUD, utilizando como interface JpaRepository
 * @author Edwin Artunduaga
 */
@Repository
public interface CountriesRepository extends JpaRepository<Country, Long> {
    Optional<Country> findById(Long id);
}
