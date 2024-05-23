package com.robinfood.orderorlocalserver.usecases.getprintingtemplate;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateEntity;
import com.robinfood.orderorlocalserver.entities.token.TokenModelEntity;
import com.robinfood.orderorlocalserver.mappers.printingtemplate.IPrintingTemplateMapper;
import com.robinfood.orderorlocalserver.repositories.printingtemplate.IPrintingTemplateRepository;
import com.robinfood.orderorlocalserver.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetPrintingTemplateUseCase implements IGetPrintingTemplateUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IPrintingTemplateRepository printingTemplateRepository;

    private IPrintingTemplateMapper iPrintingTemplateMapper;

    public GetPrintingTemplateUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IPrintingTemplateRepository printingTemplateRepository,
            IPrintingTemplateMapper iPrintingTemplateMapper
    ){
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.printingTemplateRepository = printingTemplateRepository;
        this.iPrintingTemplateMapper = iPrintingTemplateMapper;
    }

    @Override
    public PrintingTemplateDTO invoke(Long storeId) {

        log.info("Starting use case to get the printing templates");

        TokenModelEntity tokenEntity = getTokenBusinessCapabilityUseCase.invoke();

        PrintingTemplateEntity printingTemplateEntity  =
                printingTemplateRepository.getTemplatesByStore(tokenEntity.getAccessToken(), storeId);

        return iPrintingTemplateMapper.entityToDto(printingTemplateEntity);
    }
}
