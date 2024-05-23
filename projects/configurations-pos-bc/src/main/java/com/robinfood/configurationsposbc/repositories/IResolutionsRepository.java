package com.robinfood.configurationsposbc.repositories;

import com.robinfood.configurationsposbc.entities.ResolutionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResolutionsRepository extends JpaRepository<ResolutionsEntity, Long> {
}
