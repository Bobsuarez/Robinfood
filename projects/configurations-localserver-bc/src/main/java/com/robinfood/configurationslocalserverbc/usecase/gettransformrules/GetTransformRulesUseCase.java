package com.robinfood.configurationslocalserverbc.usecase.gettransformrules;

import com.robinfood.configurationslocalserverbc.dtos.template.TransformRulesDTO;
import com.robinfood.configurationslocalserverbc.entities.TransformRulesEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.ITransformRulesMapper;
import com.robinfood.configurationslocalserverbc.repositories.ITransformRulesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetTransformRulesUseCase implements IGetTransformRulesUseCase {

    private final ITransformRulesRepository transformRulesRepository;
    private final ITransformRulesMapper transformRulesMapper;

    public GetTransformRulesUseCase(ITransformRulesRepository transformRulesRepository,
                                    ITransformRulesMapper transformRulesMapper) {
        this.transformRulesRepository = transformRulesRepository;
        this.transformRulesMapper = transformRulesMapper;
    }

    @Override
    public List<TransformRulesDTO> invoke(List<Long> transformRulesIds) {
        List<TransformRulesEntity> transformRulesEntities = transformRulesRepository
                .findByIds(transformRulesIds);

        if (transformRulesEntities.isEmpty()) {
            log.error(
                    "Not found transform rules with ids: {}", transformRulesIds);

            throw new NotFoundException("Not found transform rules template with ids: " + transformRulesIds);
        }

        return transformRulesMapper.listTransformRulesEntityToListTransformRulesDTO(
                transformRulesEntities);
    }
}
