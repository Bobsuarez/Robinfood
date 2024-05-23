package com.robinfood.orderorlocalserver.repositories.printingtemplate;

import com.robinfood.orderorlocalserver.entities.APIResponseEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateEntity;
import com.robinfood.orderorlocalserver.network.ConfigurationsLocalServerBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class PrintingTemplateRepository implements IPrintingTemplateRepository{

    private ConfigurationsLocalServerBcAPI configurationsLocalServerBcAPI;

    public PrintingTemplateRepository(
            ConfigurationsLocalServerBcAPI configurationsLocalServerBcAPI
    ){
        this.configurationsLocalServerBcAPI = configurationsLocalServerBcAPI;
    }

    @Override
    public PrintingTemplateEntity getTemplatesByStore(String token, Long storeId) {

        log.info("Starting repository to get the templates from configurations-localserver-bc. " +
                "This is token {} and storeId {}", token, storeId);

        APIResponseEntity<PrintingTemplateEntity> printingTemplateEntityAPIResponseEntity =
                configurationsLocalServerBcAPI.getTemplatesByStore(token, storeId);

        log.info("This is response from api configurations-localserver-bc: {}",
                printingTemplateEntityAPIResponseEntity);

        return printingTemplateEntityAPIResponseEntity.getData();
    }
}
