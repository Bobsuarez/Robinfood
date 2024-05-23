package com.robinfood.orderorlocalserver.mappers.printingtemplate;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.TransformRulesDTO;
import com.robinfood.orderorlocalserver.entities.printingtemplate.TransformRulesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITransformRulesMapper {

    List<TransformRulesDTO> entitiesToDtos(List<TransformRulesEntity> entities);
}
