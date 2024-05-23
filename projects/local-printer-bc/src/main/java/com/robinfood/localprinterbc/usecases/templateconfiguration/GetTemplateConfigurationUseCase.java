package com.robinfood.localprinterbc.usecases.templateconfiguration;

import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.entities.PrintingTemplateEntity;
import com.robinfood.localprinterbc.mappers.printingtemplate.IPrintingTemplateMapper;
import com.robinfood.localprinterbc.repositories.templateconfigurationrepository.ITemplateConfigurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class GetTemplateConfigurationUseCase implements IGetTemplateConfigurationUseCase {


    private final IPrintingTemplateMapper printingTemplateMapper;

    private final ITemplateConfigurationRepository templateConfigurationRepository;


    public GetTemplateConfigurationUseCase(
            IPrintingTemplateMapper printingTemplateMapper,
                                   ITemplateConfigurationRepository templateConfigurationRepository) {
        this.printingTemplateMapper = printingTemplateMapper;
        this.templateConfigurationRepository = templateConfigurationRepository;
    }

    @Cacheable("printingTemplateDTO")
    @Override
    public PrintingTemplateDTO invoke(String token,Long storeId) {
        log.info("initial_hour {} ", LocalDate.now());
        log.info("use case: DownloadTemplateUseCase, \n method: invoke, \n param storeId {}", storeId);
        PrintingTemplateEntity printingTemplateEntity =
                this.templateConfigurationRepository.findTemplateByIdStore(token, storeId);

        PrintingTemplateDTO printingTemplateDTO = this.printingTemplateMapper.entityToDto(printingTemplateEntity);
        log.info("final_hour {} ", LocalDate.now());
        return printingTemplateDTO;
    }

}
