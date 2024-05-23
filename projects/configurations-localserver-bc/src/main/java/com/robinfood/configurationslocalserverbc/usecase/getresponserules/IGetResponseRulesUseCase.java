package com.robinfood.configurationslocalserverbc.usecase.getresponserules;

import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TransformRulesResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetResponseRulesUseCase {

    /**
     * Get response de transform rules
     *
     * @param printingTemplateId id of printing template
     * @return Transform rules Response
     */
    List<TransformRulesResponseDTO> invoke(Long printingTemplateId);
}
