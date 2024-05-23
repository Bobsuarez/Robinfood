package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.CreditCard;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * Campaign para el uso de CRUD, utilizando como interface JpaRepository
 * @author Edwin Artunduaga
 */
@Repository
public interface CreditCardsRepository extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> findByIdAndUserIdAndDeletedAt(
        Long id,
        Long userId,
        LocalDateTime deletedAt
    );

    List<CreditCard> findByUserIdAndCountryIdAndDeletedAt(
        Long userId,
        Long countryId,
        LocalDateTime deletedAt
    );
}
