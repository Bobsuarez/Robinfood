package com.robinfood.storeor.usecase.updateresolutions.updateresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IResolutionMapper;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsConfigurationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_UPDATE_CONFIGURATIONS_BC;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATED_CODE;

/**
 * Implementation of IUpdateResolutionsConfigurationsUseCase
 */
@Slf4j
@Service
public class UpdateResolutionsConfigurationsUseCase implements IUpdateResolutionsConfigurationsUseCase {

    private final IResolutionsConfigurationsRepository resolutionsRepository;
    private final IResolutionMapper resolutionMapper;

    public UpdateResolutionsConfigurationsUseCase(
            IResolutionsConfigurationsRepository resolutionsRepository,
            IResolutionMapper resolutionMapper) {

        this.resolutionsRepository = resolutionsRepository;
        this.resolutionMapper = resolutionMapper;
    }

    @Override
    public void invoke(
            @NotNull ResolutionDTO resolutionDTO,
            Long resolutionId,
            String token)
            throws ResolutionCrudException {

        log.info("Start UpdateResolutionsConfigurationsUseCase update resolution {} with Id {} ",
                resolutionDTO, resolutionId);

        ResolutionEntity resolutionEntity = resolutionMapper.resolutionDTOToResolutionEntity(resolutionDTO);

        APIResponseEntity<Object> apiResponseEntity = resolutionsRepository.updateStoreResolutions(
                resolutionEntity, resolutionId, token);

        if (!apiResponseEntity.getCode().equals(UPDATED_CODE)) {
            throw new ResolutionCrudException(ERROR_UPDATE_CONFIGURATIONS_BC
                    .concat(apiResponseEntity.getMessage()));
        }

        log.info("Finish UpdateResolutionsConfigurationsUseCase update successfully Id {} ", resolutionId);
    }
}
