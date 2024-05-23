package com.robinfood.paymentmethodsbc.repositories;

import java.util.Optional;

import com.robinfood.paymentmethodsbc.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * Platform para el uso de CRUD, utilizando como interface JpaRepository
 * @author Hernán A. Ramirez O.
 */
public interface PlatformsRepository extends JpaRepository<Platform, Long> {
    Optional<Platform> findById(Long id);
}
