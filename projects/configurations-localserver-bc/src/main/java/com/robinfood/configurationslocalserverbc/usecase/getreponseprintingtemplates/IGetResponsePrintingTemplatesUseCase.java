package com.robinfood.configurationslocalserverbc.usecase.getreponseprintingtemplates;

import com.robinfood.configurationslocalserverbc.dtos.templateResponse.PrintingTemplateResponseDTO;
import org.springframework.stereotype.Component;

@Component
public interface IGetResponsePrintingTemplatesUseCase {

    /**
     * Get Printing Templates Response
     *
     * @param storeId storeId
     * @return Printing Template Response
     */
    PrintingTemplateResponseDTO invoke(Long storeId);
}
