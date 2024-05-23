package com.robinfood.storeor.usecase.pos.createposconfiguration;

import com.robinfood.storeor.dtos.configurationpos.StoreCreatePosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.StoreCreatePosEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IPosMapper;
import com.robinfood.storeor.repositories.posconfigurationsbcrepository.IPosConfigurationsBcRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of ICreatePosConfigurationsUseCase
 */
@Slf4j
@Service
public class CreatePosConfigurationsUseCase implements ICreatePosConfigurationsUseCase{

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IPosMapper posMapper;

    private final IPosConfigurationsBcRepository posConfigurationsBcRepository;

    public CreatePosConfigurationsUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IPosMapper posMapper,
            IPosConfigurationsBcRepository posConfigurationsBcRepository
    ){
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.posMapper = posMapper;
        this.posConfigurationsBcRepository = posConfigurationsBcRepository;
    }

    @Override
    public StoreCreatePosDTO invoke(
            @NotNull StoreCreatePosDTO storeCreatePosDTO
    ) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        APIResponseEntity<StoreCreatePosEntity> responseStoreCreatePos = this.posConfigurationsBcRepository
                .createPosConfiguration(
                token.getAccessToken(),
                posMapper.storeCreatePosDTOToStoreCreatePosEntity(storeCreatePosDTO)
        );

        return posMapper.storeCreatePosEntityToStoreCreatePosDTO(responseStoreCreatePos.getData());
    }
}
