package com.robinfood.configurationslocalserverbc.usecase.getgroupstore;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsStoresDTO;
import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsStoresEntity;
import com.robinfood.configurationslocalserverbc.exceptions.NotFoundException;
import com.robinfood.configurationslocalserverbc.mappers.ITemplateGroupsStoresMapper;
import com.robinfood.configurationslocalserverbc.repositories.ITemplateGroupsStoresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetGroupByStoreUseCase implements IGetGroupByStoreUseCase {

    private final ITemplateGroupsStoresRepository templateGroupsStoresRepository;
    private final ITemplateGroupsStoresMapper templateGroupsStoresMapper;

    public GetGroupByStoreUseCase(ITemplateGroupsStoresRepository templateGroupsStoresRepository,
                                  ITemplateGroupsStoresMapper templateGroupsStoresMapper) {
        this.templateGroupsStoresRepository = templateGroupsStoresRepository;
        this.templateGroupsStoresMapper = templateGroupsStoresMapper;
    }

    @Override
    public TemplateGroupsStoresDTO invoke(Long storeId) {

        Optional<TemplateGroupsStoresEntity> optionalTemplateGroupsStoresEntity = templateGroupsStoresRepository
                .findByStoreIdAndActive(storeId, Boolean.TRUE);

        if (optionalTemplateGroupsStoresEntity.isEmpty()) {
            log.error(
                    "Not found Group by store with id: {}", storeId);

            throw new NotFoundException("Not found Group by store with id: " + storeId);
        }

        return templateGroupsStoresMapper.templateGroupStoreEntityToTemplateGroupStore(
                optionalTemplateGroupsStoresEntity.get());
    }
}
