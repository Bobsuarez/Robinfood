package com.robinfood.configurationslocalserverbc.mappers;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsStoresDTO;
import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsStoresEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITemplateGroupsStoresMapper {

    TemplateGroupsStoresDTO templateGroupStoreEntityToTemplateGroupStore(
            TemplateGroupsStoresEntity templateGroupsStoresEntity);

}
