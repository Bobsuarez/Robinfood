package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.Entity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * Entity para el uso de CRUD, utilizando como interface JpaRepository
 * @author Hernán A. Ramirez O.
 */
public interface EntitiesRepository extends JpaRepository<Entity, Long> {
    Optional<Entity> findById(Long id);
}
