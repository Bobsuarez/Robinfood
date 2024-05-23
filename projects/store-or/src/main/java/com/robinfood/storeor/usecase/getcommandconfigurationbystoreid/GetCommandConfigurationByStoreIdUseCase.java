package com.robinfood.storeor.usecase.getcommandconfigurationbystoreid;

import com.robinfood.storeor.dtos.CommandConfiguration.CommandConfigurationResponseDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.CommandConfiguration.CommandConfigurationEntity;
import com.robinfood.storeor.mappers.ICommandConfigurationMapper;
import com.robinfood.storeor.repositories.billingrepository.IBillingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class GetCommandConfigurationByStoreIdUseCase implements IGetCommandConfigurationByStoreIdUseCase{

    private IBillingRepository billingRepository;
    private ICommandConfigurationMapper commandConfigurationMapper;

    public GetCommandConfigurationByStoreIdUseCase(IBillingRepository billingRepository,
                                                   ICommandConfigurationMapper commandConfigurationMapper) {
        this.billingRepository = billingRepository;
        this.commandConfigurationMapper = commandConfigurationMapper;
    }

    public List<CommandConfigurationResponseDTO> invoke(Long storeId, String token) {
        log.info("Get commandConfiguration  by storeID use case execute invoke  configuration by store: {}", storeId);

        APIResponseEntity<List<CommandConfigurationEntity>> commandConfigurationEntityAPIResponseEntity =
                billingRepository.getCommandConfiguration(storeId,token);

        return commandConfigurationMapper.commandConfigurationEntityToCommandConfigurationResponseDTO(
                commandConfigurationEntityAPIResponseEntity.getData());
    }
}
