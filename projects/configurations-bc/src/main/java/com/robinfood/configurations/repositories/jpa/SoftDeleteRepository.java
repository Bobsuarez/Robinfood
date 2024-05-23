package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.AbstractBaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface SoftDeleteRepository<T extends AbstractBaseEntity, I extends Long>
    extends JpaRepository<T, I> {

    @Override
    @Transactional
    @Query("select e from #{#entityName} e where e.deletedAt = null")
    List<T> findAll();

    @Override
    @Transactional
    @Query("select e from #{#entityName} e where e.id = :id and e.deletedAt = null")
    Optional<T> findById(@Param("id") Long id);

    @Override
    @Transactional
    default boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Query("update #{#entityName} e set e.deletedAt = :deleteAt where e.id = :id")
    @Transactional
    @Modifying
    void deleteByIdAndDeletedAt(@Param("id") Long id, @Param("deleteAt") LocalDateTime deleteAt);

    @Override
    @Transactional
    @Modifying
    default void deleteById(Long id) {
        deleteByIdAndDeletedAt(id, LocalDateTime.now());
    }

    @Override
    @Transactional
    @Modifying
    default void delete(T entity) {
        deleteById(entity.getId());
    }

    @Override
    @Transactional
    @Query("select count(e) from #{#entityName} e where e.deletedAt = null")
    long count();

}

