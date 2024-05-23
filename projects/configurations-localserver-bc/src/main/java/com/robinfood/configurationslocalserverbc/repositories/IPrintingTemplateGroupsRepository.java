package com.robinfood.configurationslocalserverbc.repositories;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrintingTemplateGroupsRepository
        extends JpaRepository<PrintingTemplateGroupsEntity, Long> {

    List<PrintingTemplateGroupsEntity> findByGroupId(Long groupId);
}
