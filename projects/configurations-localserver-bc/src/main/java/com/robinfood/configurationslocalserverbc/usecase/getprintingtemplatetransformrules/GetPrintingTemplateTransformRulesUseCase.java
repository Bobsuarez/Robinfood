package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatetransformrules;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTransformRulesDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateTransformRulesEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplateTransformRulesMapper;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplateTransformRulesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetPrintingTemplateTransformRulesUseCase implements IGetPrintingTemplateTransformRulesUseCase {

    private final IPrintingTemplateTransformRulesRepository printingTemplateTransformRulesRepository;
    private final IPrintingTemplateTransformRulesMapper printingTemplateTransformRulesMapper;

    public GetPrintingTemplateTransformRulesUseCase(
            IPrintingTemplateTransformRulesRepository printingTemplateTransformRulesRepository,
            IPrintingTemplateTransformRulesMapper printingTemplateTransformRulesMapper) {
        this.printingTemplateTransformRulesRepository = printingTemplateTransformRulesRepository;
        this.printingTemplateTransformRulesMapper = printingTemplateTransformRulesMapper;
    }

    @Override
    public List<PrintingTemplateTransformRulesDTO> invoke(Long printingTemplateId) {

        List<PrintingTemplateTransformRulesEntity> printingTemplateTransformRulesEntities =
                printingTemplateTransformRulesRepository.findByPrintingTemplateId(printingTemplateId);

        if (printingTemplateTransformRulesEntities.isEmpty()) {
            log.error(
                    "Not found rules printing template with id: {}", printingTemplateId);

            throw new NotFoundException("Not found transform rules printing template with id: " + printingTemplateId);
        }

        return printingTemplateTransformRulesMapper
                .lisPrintingTemplateTransformRulesEntityToPrintingTemplateTransformRulesDTO(
                        printingTemplateTransformRulesEntities
                );
    }
}
