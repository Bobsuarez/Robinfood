package com.robinfood.configurationslocalserverbc.usecase.gettemplatebygroup;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateGroupsEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplateGroupMapper;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplateGroupsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetPrintingTemplateGroupsUseCase implements IGetPrintingTemplateGroupsUseCase {

    private final IPrintingTemplateGroupsRepository printingTemplateGroupsRepository;
    private final IPrintingTemplateGroupMapper printingTemplateGroupMapper;

    public GetPrintingTemplateGroupsUseCase(IPrintingTemplateGroupsRepository printingTemplateGroupsRepository
            , IPrintingTemplateGroupMapper printingTemplateGroupMapper) {
        this.printingTemplateGroupsRepository = printingTemplateGroupsRepository;
        this.printingTemplateGroupMapper = printingTemplateGroupMapper;
    }

    @Override
    public List<PrintingTemplateGroupsDTO> invoke(Long groupId) {
        List<PrintingTemplateGroupsEntity> printingTemplateGroupsEntities =
                printingTemplateGroupsRepository.findByGroupId(groupId);

        if (printingTemplateGroupsEntities.isEmpty()) {
            log.error(
                    "Not found group with id: {}", groupId);

            throw new NotFoundException("Not found printing template group with id: ");
        }

        return printingTemplateGroupMapper.listPrintingTemplatesGroupEntityToListPrintingTemplatesGroupDTO(
                printingTemplateGroupsEntities);
    }
}
