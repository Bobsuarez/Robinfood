package com.robinfood.configurationsposbc.repositories;

import com.robinfood.configurationsposbc.entities.PosTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPosTypesRepository extends JpaRepository<PosTypesEntity, Long> {
}
