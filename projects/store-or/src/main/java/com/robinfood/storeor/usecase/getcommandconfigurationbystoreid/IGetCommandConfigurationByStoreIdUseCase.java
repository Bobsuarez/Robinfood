package com.robinfood.storeor.usecase.getcommandconfigurationbystoreid;

import com.robinfood.storeor.dtos.CommandConfiguration.CommandConfigurationResponseDTO;
import java.util.List;

public interface IGetCommandConfigurationByStoreIdUseCase {

    /**
     * use this use case to get the configuration of the commands to execute in the store
     *
     * @param storeId Identifier of the store
     * @param token   token of services
     * @return command configuration
     */
    List<CommandConfigurationResponseDTO> invoke(Long storeId, String token);
}
