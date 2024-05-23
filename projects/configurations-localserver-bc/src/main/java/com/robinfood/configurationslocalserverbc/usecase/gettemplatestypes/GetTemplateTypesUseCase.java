package com.robinfood.configurationslocalserverbc.usecase.gettemplatestypes;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTypesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTypesEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplateTypesMapper;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplateTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetTemplateTypesUseCase implements IGetTemplateTypesUseCase {

    private final IPrintingTemplateTypesRepository printingTemplateTypesRepository;
    private final IPrintingTemplateTypesMapper printingTemplateTypesMapper;

    public GetTemplateTypesUseCase(IPrintingTemplateTypesRepository printingTemplateTypesRepository,
                                   IPrintingTemplateTypesMapper printingTemplateTypesMapper) {
        this.printingTemplateTypesRepository = printingTemplateTypesRepository;
        this.printingTemplateTypesMapper = printingTemplateTypesMapper;
    }


    @Override
    public PrintingTemplateTypesDTO invoke(Long printingTemplateTypeId) {

        Optional<PrintingTemplateTypesEntity> optionalPrintingTemplateTypesEntity = printingTemplateTypesRepository
                .findById(printingTemplateTypeId);

        if (optionalPrintingTemplateTypesEntity.isEmpty()) {
            log.error(
                    "Not found printing template types with id: {}", printingTemplateTypeId);

            throw new NotFoundException("Not found printing template types with id: " + printingTemplateTypeId);
        }

        return printingTemplateTypesMapper.printingTemplateTypeEntityToPrintingTemplateTypeDTO(
                optionalPrintingTemplateTypesEntity.get());
    }
}
