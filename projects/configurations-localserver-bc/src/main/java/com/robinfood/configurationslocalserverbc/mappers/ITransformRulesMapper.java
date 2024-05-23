package com.robinfood.configurationslocalserverbc.mappers;

import com.robinfood.configurationslocalserverbc.dtos.template.TransformRulesDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TransformRulesResponseDTO;
import com.robinfood.configurationslocalserverbc.entities.TransformRulesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITransformRulesMapper {

    List<TransformRulesDTO> listTransformRulesEntityToListTransformRulesDTO(
            List<TransformRulesEntity> transformRulesEntities);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    List<TransformRulesResponseDTO> listTransformRulesDTOToListTransformRulesResponseDTO(
            List<TransformRulesDTO> transformRulesDTOS);

}
