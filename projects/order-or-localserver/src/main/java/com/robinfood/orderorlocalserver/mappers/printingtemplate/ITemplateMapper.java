package com.robinfood.orderorlocalserver.mappers.printingtemplate;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.TemplateDTO;
import com.robinfood.orderorlocalserver.entities.printingtemplate.TemplateEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITemplateMapper {

    List<TemplateDTO> entitiesToDtos(List<TemplateEntity> entities);
}
