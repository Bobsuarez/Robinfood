package com.robinfood.localserver.app.configurations;

import com.robinfood.localserver.localprocessorderor.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localserver.localprocessorderor.usecase.getprintingtemplateconfigurationusecase.IGetPrintingTemplateConfigurationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PrintingTemplateConfiguration {

    @NotNull
    private final IGetPrintingTemplateConfigurationUseCase getPrintingTemplateConfigurationUseCase;

    @Bean
    public PrintingTemplateDTO consumeLocalPrinterBcToGetPrintingTemplates(){

        try{
            log.info("Init configuration method to consume local printer bc and get printing templates");

            PrintingTemplateDTO printingTemplateDTO = getPrintingTemplateConfigurationUseCase.invoke();

            log.info("Finished configuration method to consume local printer bc and get printing templates");

            return printingTemplateDTO;

        } catch (Exception e){
            log.error("Error trying to consume local printer bc and get printing templates. Error is {}",e.getMessage());
            return null;
        }
    }
}
