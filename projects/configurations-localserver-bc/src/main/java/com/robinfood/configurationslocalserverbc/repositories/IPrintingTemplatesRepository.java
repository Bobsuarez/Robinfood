package com.robinfood.configurationslocalserverbc.repositories;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrintingTemplatesRepository extends JpaRepository<PrintingTemplatesEntity, Long> {

    @Query("select p from PrintingTemplatesEntity p where p.id IN(:ids) and p.active = true")
    List<PrintingTemplatesEntity> findByIds(@Param("ids") List<Long> ids);
}
