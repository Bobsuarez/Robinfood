package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplate;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplatesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplatesEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplatesMapper;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplatesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetPrintingTemplateUseCase implements IGetPrintingTemplateUseCase {

    private final IPrintingTemplatesRepository printingTemplatesRepository;
    private final IPrintingTemplatesMapper printingTemplatesMapper;

    public GetPrintingTemplateUseCase(IPrintingTemplatesRepository printingTemplatesRepository,
                                      IPrintingTemplatesMapper printingTemplatesMapper) {
        this.printingTemplatesRepository = printingTemplatesRepository;
        this.printingTemplatesMapper = printingTemplatesMapper;
    }

    @Override
    public List<PrintingTemplatesDTO> invoke(List<Long> printTemplateIds) {
        List<PrintingTemplatesEntity> printingTemplatesEntities = printingTemplatesRepository
                .findByIds(printTemplateIds);

        if (printingTemplatesEntities.isEmpty()) {
            log.error(
                    "Not found templates with ids: {}", printTemplateIds);

            throw new NotFoundException("Not found printing template with ids: " + printTemplateIds);
        }

        return printingTemplatesMapper.listPrintingTemplatesEntityToListPrintingTemplatesDTO(
                printingTemplatesEntities);
    }
}
