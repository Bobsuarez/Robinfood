package com.robinfood.configurationslocalserverbc.usecase.getgroup;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.ITemplateGroupsMapper;
import com.robinfood.configurationslocalserverbc.repositories.ITemplateGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetGroupUseCase implements IGetGroupUseCase {

    private final ITemplateGroupRepository templateGroupRepository;
    private final ITemplateGroupsMapper templateGroupsMapper;

    public GetGroupUseCase(ITemplateGroupRepository templateGroupRepository,
                           ITemplateGroupsMapper templateGroupsMapper) {
        this.templateGroupRepository = templateGroupRepository;
        this.templateGroupsMapper = templateGroupsMapper;
    }

    @Override
    public TemplateGroupsDTO invoke(Long groupId) {

        Optional<TemplateGroupsEntity> optionalTemplateGroupsEntity = templateGroupRepository.findById(groupId);

        if (optionalTemplateGroupsEntity.isEmpty()) {
            log.error(
                    "Not found group with id: {}", groupId);

            throw new NotFoundException("Not found template store with id: " + groupId);
        }

        return templateGroupsMapper.templateGroupsEntityToTemplateGroupDTO(optionalTemplateGroupsEntity.get());
    }
}
