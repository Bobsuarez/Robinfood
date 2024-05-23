package com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IActiveResolutionMapper;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsConfigurationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_UPDATE_CONFIGURATIONS_BC;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATED_CODE;

/**
 * Implementation of IActivateOrDeactivateResolutionsConfigurationsUseCase
 */
@Slf4j
@Service
public class ActivateOrDeactivateResolutionsConfigurationsUseCase
        implements IActivateOrDeactivateResolutionsConfigurationsUseCase {

    private final IResolutionsConfigurationsRepository resolutionsRepository;
    private final IActiveResolutionMapper activeResolutionMapper;

    public ActivateOrDeactivateResolutionsConfigurationsUseCase(
            IResolutionsConfigurationsRepository resolutionsRepository,
            IActiveResolutionMapper activeResolutionMapper) {

        this.resolutionsRepository = resolutionsRepository;
        this.activeResolutionMapper = activeResolutionMapper;
    }

    @Override
    public void invoke(
            @NotNull ActivateOrDeactivateDTO activateOrDeactivateDTO,
            Long resolutionId,
            String token)
            throws ResolutionCrudException {

        log.info("Start ActivateOrDeactivateResolutionsConfigurationsUseCase active or deactivate data {} with Id {} ",
                activateOrDeactivateDTO, resolutionId);

        ActivateOrDeactivateEntity activateOrDeactivateEntity = activeResolutionMapper
                .activeResolutionDTOActiveResolutionEntity(activateOrDeactivateDTO);

        APIResponseEntity<Object> apiResponseEntity = resolutionsRepository.activateOrDeactivate(
                activateOrDeactivateEntity, resolutionId, token);

        if (!apiResponseEntity.getCode().equals(UPDATED_CODE)) {
            throw new ResolutionCrudException(ERROR_UPDATE_CONFIGURATIONS_BC
                    .concat(apiResponseEntity.getMessage()));
        }

        log.info("Finish ActivateOrDeactivateResolutionsConfigurationsUseCase update successfully Id {} ",
                resolutionId);
    }
}
