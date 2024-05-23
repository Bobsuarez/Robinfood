package com.robinfood.configurationslocalserverbc.repositories;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrintingTemplateTypesRepository extends JpaRepository<PrintingTemplateTypesEntity, Long> {
}
