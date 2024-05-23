package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.CommandConfiguration.CommandConfigurationResponseDTO;
import com.robinfood.storeor.entities.CommandConfiguration.CommandConfigurationEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ICommandConfigurationMapper {

    List<CommandConfigurationResponseDTO> commandConfigurationEntityToCommandConfigurationResponseDTO
            (List<CommandConfigurationEntity> commandConfiguration);

}
