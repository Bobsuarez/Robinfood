package com.robinfood.orderorlocalserver.repositories.printingtemplate;

import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrintingTemplateRepository {

    PrintingTemplateEntity getTemplatesByStore(
            String token,
            Long storeId
    );
}
