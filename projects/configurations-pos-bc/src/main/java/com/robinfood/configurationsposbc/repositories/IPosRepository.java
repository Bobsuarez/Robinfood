package com.robinfood.configurationsposbc.repositories;

import com.robinfood.configurationsposbc.entities.PosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPosRepository extends JpaRepository<PosEntity, Long> {

    Optional<PosEntity> findByIdAndStatus(Long posId, Boolean status);
}
