package com.robinfood.storeor.usecase.activateordeactivatepos;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivatePosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivatePosEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import com.robinfood.storeor.mappers.IActiveOrDeactivatePosMapper;
import com.robinfood.storeor.repositories.posconfigurationsbcrepository.IPosConfigurationsBcRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.storeor.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_UPDATE_POS_CONFIGURATIONS_BC;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATED_CODE;

/**
 * Implementation of IActivateOrDeactivatePosConfigurationsUseCase
 */
@Slf4j
@Service
public class ActivateOrDeactivatePosConfigurationsUseCase
        implements IActivateOrDeactivatePosConfigurationsUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IPosConfigurationsBcRepository posConfigurationsBcRepository;
    private final IActiveOrDeactivatePosMapper activeOrDeactivatePosMapper;

    public ActivateOrDeactivatePosConfigurationsUseCase(
            IActiveOrDeactivatePosMapper activeOrDeactivatePosMapper,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IPosConfigurationsBcRepository posConfigurationsBcRepository
    ) {

        this.activeOrDeactivatePosMapper = activeOrDeactivatePosMapper;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.posConfigurationsBcRepository = posConfigurationsBcRepository;
    }

    @Override
    public void invoke(
            @NotNull ActivateOrDeactivatePosDTO activateOrDeactivatePosDTO,
            Long posId
    ) throws PosException {

        log.info("Start ActivateOrDeactivatePosConfigurationsUseCase active or deactivate data {} with Id {} ",
                ObjectMapperUtils.objectToJson(activateOrDeactivatePosDTO), posId);

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        ActivateOrDeactivatePosEntity activateOrDeactivateEntity = activeOrDeactivatePosMapper
                .activeOrDeactivatePosDTOActiveOrDeactivateEntity(activateOrDeactivatePosDTO);

        APIResponseEntity<Object> apiResponseEntity = posConfigurationsBcRepository
                .activateOrDeactivatePosConfigurations(activateOrDeactivateEntity, posId, token.getAccessToken());

        if (!apiResponseEntity.getCode().equals(UPDATED_CODE)) {
            throw new PosException(ERROR_UPDATE_POS_CONFIGURATIONS_BC
                    .concat(apiResponseEntity.getMessage()));
        }

        log.info("Finish ActivateOrDeactivatePosConfigurationsUseCase update successfully Id {} ",
                posId);
    }
}
