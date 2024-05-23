package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatebrandgroups;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.PrintingTemplateBrandGroupsEntity;
import com.robinfood.configurationslocalserverbc.mappers.IPrintingTemplateBrandGroupsMapper;
import com.robinfood.configurationslocalserverbc.repositories.IPrintingTemplateBrandGroupsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetPrintingTemplateBrandGroupUseCase implements IGetPrintingTemplateBrandGroupUseCase {

    private final IPrintingTemplateBrandGroupsRepository printingTemplateBrandGroupsRepository;
    private final IPrintingTemplateBrandGroupsMapper printingTemplateBrandGroupsMapper;

    public GetPrintingTemplateBrandGroupUseCase(
            IPrintingTemplateBrandGroupsRepository printingTemplateBrandGroupsRepository,
            IPrintingTemplateBrandGroupsMapper printingTemplateBrandGroupsMapper) {
        
        this.printingTemplateBrandGroupsRepository = printingTemplateBrandGroupsRepository;
        this.printingTemplateBrandGroupsMapper = printingTemplateBrandGroupsMapper;
    }

    @Override
    public List<PrintingTemplateBrandGroupsDTO> invoke(List<Long> groupIds) {
        List<PrintingTemplateBrandGroupsEntity>
                optionalPrintingTemplateBrandGroupsEntity = printingTemplateBrandGroupsRepository.findByIds(groupIds);

        return printingTemplateBrandGroupsMapper.printingTemplateBrandGroupsEntityToPrintingTemplateBrandGroupsDTO(
                optionalPrintingTemplateBrandGroupsEntity);
    }
}
