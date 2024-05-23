package com.robinfood.configurationslocalserverbc.mappers;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.entities.TemplateGroupsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITemplateGroupsMapper {

    TemplateGroupsDTO templateGroupsEntityToTemplateGroupDTO(TemplateGroupsEntity templateGroupsEntity);

}
