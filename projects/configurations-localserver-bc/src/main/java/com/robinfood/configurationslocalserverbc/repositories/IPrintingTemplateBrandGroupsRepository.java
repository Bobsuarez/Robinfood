package com.robinfood.configurationslocalserverbc.repositories;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateBrandGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrintingTemplateBrandGroupsRepository
        extends JpaRepository<PrintingTemplateBrandGroupsEntity, Long> {

    @Query("select p from PrintingTemplateBrandGroupsEntity p where p.groupId IN(:ids)")
    List<PrintingTemplateBrandGroupsEntity> findByIds(@Param("ids") List<Long> ids);
}
