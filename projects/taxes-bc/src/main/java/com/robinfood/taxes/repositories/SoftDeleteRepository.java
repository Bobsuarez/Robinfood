package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.AbstractBaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SoftDeleteRepository<T extends AbstractBaseEntity, I extends Long>
    extends JpaRepository<T, I> {

    @Override
    @Transactional
    @Query("select e from #{#entityName} e where e.deletedAt = null")
    List<T> findAll();

    @Override
    @Transactional
    @Query("select e from #{#entityName} e where e.deletedAt = null")
    Page<T> findAll(Pageable pageable);

    @Override
    @Transactional
    @Query("select e from #{#entityName} e where e.id = ?1 and e.deletedAt = null")
    Optional<T> findById(Long id);

    @Override
    @Transactional
    default boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Query("update #{#entityName} e set e.deletedAt = ?2 where e.id = ?1")
    @Transactional
    @Modifying
    void deleteByIdAndDeletedAt(Long id, LocalDateTime deleteAt);

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
