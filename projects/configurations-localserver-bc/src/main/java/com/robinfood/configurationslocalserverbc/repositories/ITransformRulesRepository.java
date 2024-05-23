package com.robinfood.configurationslocalserverbc.repositories;

import com.robinfood.configurationslocalserverbc.entities.TransformRulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransformRulesRepository extends JpaRepository<TransformRulesEntity, Long> {

    @Query("select p from TransformRulesEntity p where p.id IN(:ids)")
    List<TransformRulesEntity> findByIds(@Param("ids") List<Long> ids);
}
