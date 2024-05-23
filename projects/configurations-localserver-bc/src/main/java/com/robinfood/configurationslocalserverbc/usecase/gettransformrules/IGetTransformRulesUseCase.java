package com.robinfood.configurationslocalserverbc.usecase.gettransformrules;

import com.robinfood.configurationslocalserverbc.dtos.template.TransformRulesDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetTransformRulesUseCase {

    /**
     * Finds a group by id
     *
     * @param transformRulesIds ids Transforms
     * @return Transform Rules of templates
     */
    List<TransformRulesDTO> invoke(List<Long> transformRulesIds);
}
