package com.robinfood.storeor.usecase.updateresolutions.updateresolutionsordersposbc;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IResolutionMapper;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsOrderPosRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_UPDATE_IN_ORDERS;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATED_CODE;

/**
 * Implementation of IUpdateResolutionsOrdersPosUseCase
 */
@Slf4j
@Service
public class UpdateResolutionsOrdersPosUseCase implements IUpdateResolutionsOrdersPosUseCase {

    private final IResolutionsOrderPosRepository resolutionsOrderPosRepository;
    private final IResolutionMapper resolutionMapper;

    public UpdateResolutionsOrdersPosUseCase(
            IResolutionsOrderPosRepository resolutionsOrderPosRepository,
            IResolutionMapper resolutionMapper) {
        this.resolutionsOrderPosRepository = resolutionsOrderPosRepository;
        this.resolutionMapper = resolutionMapper;
    }

    @Override
    public void invoke(
            @NotNull ResolutionDTO resolutionDTO,
            Long resolutionId,
            String token
    ) throws ResolutionCrudException {

        log.info("Update resolutions request in orders {}, to update", resolutionDTO);

        ResolutionEntity resolutionEntity = resolutionMapper.resolutionDTOToResolutionEntity(resolutionDTO);

        APIResponseEntity<Object> apiResponseEntity = resolutionsOrderPosRepository.updateStoreResolutions(
                resolutionEntity, resolutionId, token);

        if (!apiResponseEntity.getCode().equals(UPDATED_CODE)) {
            throw new ResolutionCrudException(ERROR_UPDATE_IN_ORDERS
                    .concat(apiResponseEntity.getMessage()));
        }
    }
}
