package com.robinfood.storeor.usecase.updateconfigurationbypos;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import com.robinfood.storeor.repositories.posconfigurationsbcrepository.IPosConfigurationsBcRepository;
import com.robinfood.storeor.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_UPDATE_CONFIGURATIONS_BC;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATED_CODE;
import static com.robinfood.storeor.enums.logs.ApplicationLogEnum.ORDER_FINISH_CONFIGURATIONS_BC_UPDATE;
import static com.robinfood.storeor.enums.logs.ApplicationLogEnum.ORDER_START_CONFIGURATIONS_BC_UPDATE;

/**
 * Implementation of IUpdatePosConfigurationsUseCase
 */
@Slf4j
@Service
public class UpdatePosConfigurationsUseCase implements IUpdatePosConfigurationsUseCase {

    private final IPosConfigurationsBcRepository posConfigurationsBcRepository;

    public UpdatePosConfigurationsUseCase(IPosConfigurationsBcRepository posConfigurationsBcRepository) {
        this.posConfigurationsBcRepository = posConfigurationsBcRepository;
    }

    @Override
    public void invoke(
            @NotNull Long posId,
            @NotNull PosDTO posDTO,
            @NotNull String token
    ) throws PosException {

        log.info(ORDER_START_CONFIGURATIONS_BC_UPDATE.getMessage(),
                posDTO, posId
        );

        APIResponseEntity<PosEntity> apiResponseEntity = posConfigurationsBcRepository
                .updatePosConfigurationBc(posDTO, posId, token);

        if (!apiResponseEntity.getCode().equals(UPDATED_CODE)) {
            throw new PosException(ERROR_UPDATE_CONFIGURATIONS_BC
                    .concat(apiResponseEntity.getMessage()));
        }

        log.info(ORDER_FINISH_CONFIGURATIONS_BC_UPDATE.getMessage(),
                ObjectMapperUtils.objectToJson(apiResponseEntity.getMessage())
        );
    }
}
