package com.robinfood.localprinterbc.repositories.templateconfigurationrepository;

import com.robinfood.localprinterbc.entities.PrintingTemplateEntity;

public interface ITemplateConfigurationRepository {
    PrintingTemplateEntity findTemplateByIdStore(String token, Long storeId);
}
