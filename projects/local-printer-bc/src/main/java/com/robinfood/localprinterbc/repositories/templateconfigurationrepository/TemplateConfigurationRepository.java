package com.robinfood.localprinterbc.repositories.templateconfigurationrepository;

import com.robinfood.localprinterbc.entities.APIResponseEntity;
import com.robinfood.localprinterbc.entities.PrintingTemplateEntity;
import com.robinfood.localprinterbc.feings.ConfigurationOrderOrLocalServerAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class TemplateConfigurationRepository implements ITemplateConfigurationRepository {

    private final ConfigurationOrderOrLocalServerAPI configurationOrderOrLocalServerAPI;

    public TemplateConfigurationRepository(ConfigurationOrderOrLocalServerAPI configurationOrderOrLocalServerAPI) {
        this.configurationOrderOrLocalServerAPI = configurationOrderOrLocalServerAPI;
    }

    @Override
    public PrintingTemplateEntity findTemplateByIdStore(String token, Long storeId) {
        APIResponseEntity<PrintingTemplateEntity> printingTemplateEntityAPIResponseEntity =
                this.configurationOrderOrLocalServerAPI.getTemplatesByStore(token, storeId);
        return printingTemplateEntityAPIResponseEntity.getData();
    }
}
