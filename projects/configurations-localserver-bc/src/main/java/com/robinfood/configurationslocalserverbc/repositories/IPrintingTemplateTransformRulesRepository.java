package com.robinfood.configurationslocalserverbc.repositories;

import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTransformRulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrintingTemplateTransformRulesRepository
        extends JpaRepository<PrintingTemplateTransformRulesEntity, Long> {

    List<PrintingTemplateTransformRulesEntity> findByPrintingTemplateId(Long printingTemplateId);
}
