package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.Terminal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * Terminals para el uso de CRUD, utilizando como interface JpaRepository
 * @author Hernán A. Ramirez O.
 */
public interface TerminalsRepository extends JpaRepository<Terminal, Long> {
    Optional<Terminal> findByUuidAndStatusAndDeletedAt(String uuid, boolean status, LocalDateTime deletedAt);
}

