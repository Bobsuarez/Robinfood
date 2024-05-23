package com.robinfood.storeor.usecase.createresolutions.createresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.entities.response.ResponseResolutionsWithPosEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IResolutionMapper;
import com.robinfood.storeor.mappers.IResponseResolutionsWithPosMapper;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsConfigurationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.CREATED_CODE;
import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_CREATED_CONFIGURATIONS_BC;
import static com.robinfood.storeor.configs.constants.APIConstants.LOG_REQUEST_CREATE_RESOLUTIONS;

/**
 * Implementation of ICreateResolutionsConfigurationsUseCase
 */
@Slf4j
@Service
public class CreateResolutionsConfigurationsUseCase implements ICreateResolutionsConfigurationsUseCase {

    private final IResolutionsConfigurationsRepository resolutionsRepository;
    private final IResponseResolutionsWithPosMapper responseResolutionsWithPosMapper;
    private final IResolutionMapper resolutionMapper;

    public CreateResolutionsConfigurationsUseCase(
            IResolutionsConfigurationsRepository resolutionsRepository,
            IResponseResolutionsWithPosMapper responseResolutionsWithPosMapper,
            IResolutionMapper resolutionMapper) {
        this.resolutionsRepository = resolutionsRepository;
        this.responseResolutionsWithPosMapper = responseResolutionsWithPosMapper;
        this.resolutionMapper = resolutionMapper;
    }

    @Override
    public List<ResponseResolutionsWithPosDTO> invoke(
            @NotNull StoreResolutionsDTO storeResolutions,
            String token)
            throws ResolutionCrudException {

        log.info(LOG_REQUEST_CREATE_RESOLUTIONS, storeResolutions);

        List<ResolutionEntity> resolutionEntityList = resolutionMapper.resolutionDTOListToResolutionEntityList(
                storeResolutions.getResolutions());

        APIResponseEntity<List<ResponseResolutionsWithPosEntity>> apiResponseEntity = resolutionsRepository
                .createStoreResolutions(resolutionEntityList, token);

        if (!apiResponseEntity.getCode().equals(CREATED_CODE)) {
            throw new ResolutionCrudException(ERROR_CREATED_CONFIGURATIONS_BC
                    .concat(apiResponseEntity.getMessage()));
        }

        return responseResolutionsWithPosMapper.responseResolutionsWithPosEntityToResponseResolutionsWithPosDTO(
                apiResponseEntity.getData()
        );
    }
}
