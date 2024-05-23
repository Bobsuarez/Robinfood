package com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsordersposbc;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IActiveResolutionMapper;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsOrderPosRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_UPDATE_IN_ORDERS;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATED_CODE;

/**
 * Implementation of IActivateOrDeactivateResolutionsOrdersPosUseCase
 */
@Slf4j
@Service
public class ActivateOrDeactivateResolutionsOrdersPosUseCase
        implements IActivateOrDeactivateResolutionsOrdersPosUseCase {

    private final IResolutionsOrderPosRepository resolutionsOrderPosRepository;
    private final IActiveResolutionMapper activeResolutionMapper;

    public ActivateOrDeactivateResolutionsOrdersPosUseCase(
            IResolutionsOrderPosRepository resolutionsOrderPosRepository,
            IActiveResolutionMapper activeResolutionMapper) {
        this.resolutionsOrderPosRepository = resolutionsOrderPosRepository;
        this.activeResolutionMapper = activeResolutionMapper;
    }

    @Override
    public void invoke(
            @NotNull ActivateOrDeactivateDTO activateOrDeactivateDTO,
            Long resolutionId,
            String token
    ) throws ResolutionCrudException {

        log.info("Start ActivateOrDeactivateResolutionsOrdersPosUseCase active or deactivate data {} with Id {} ",
                activateOrDeactivateDTO, resolutionId);

        ActivateOrDeactivateEntity activateOrDeactivateEntity = activeResolutionMapper
                .activeResolutionDTOActiveResolutionEntity(activateOrDeactivateDTO);

        APIResponseEntity<Object> apiResponseEntity = resolutionsOrderPosRepository.activateOrDeactivate(
                activateOrDeactivateEntity, resolutionId, token);

        if (!apiResponseEntity.getCode().equals(UPDATED_CODE)) {
            throw new ResolutionCrudException(ERROR_UPDATE_IN_ORDERS
                    .concat(apiResponseEntity.getMessage()));
        }

        log.info("Finish ActivateOrDeactivateResolutionsOrdersPosUseCase update successfully Id {} ", resolutionId);
    }
}
