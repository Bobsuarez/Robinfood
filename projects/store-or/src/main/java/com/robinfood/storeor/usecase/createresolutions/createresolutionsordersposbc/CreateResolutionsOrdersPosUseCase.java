package com.robinfood.storeor.usecase.createresolutions.createresolutionsordersposbc;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IStoreResolutionsMapper;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsOrderPosRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.CREATED_CODE;
import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_CREATE_IN_ORDERS;

/**
 * Implementation of ICreateResolutionsOrdersPosUseCase
 */
@Slf4j
@Service
public class CreateResolutionsOrdersPosUseCase implements ICreateResolutionsOrdersPosUseCase {

    private final IResolutionsOrderPosRepository resolutionsOrderPosRepository;
    private final IStoreResolutionsMapper storeResolutionsMapper;

    public CreateResolutionsOrdersPosUseCase(
            IResolutionsOrderPosRepository resolutionsOrderPosRepository,
            IStoreResolutionsMapper storeResolutionsMapper) {
        this.resolutionsOrderPosRepository = resolutionsOrderPosRepository;
        this.storeResolutionsMapper = storeResolutionsMapper;
    }

    @Override
    public void invoke(
            List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOs,
            @NotNull StoreResolutionsDTO storeResolutions,
            String token
    ) throws ResolutionCrudException {

        log.info("Create Pos resolutions request {}, to create", storeResolutions);

        buildIdsResolutions(responseResolutionsWithPosDTOs, storeResolutions);

        StoreResolutionsEntity storeResolutionsEntity = storeResolutionsMapper
                .storeResolutionDTOToStoreResolutionEntity(storeResolutions);

        APIResponseEntity<Object> apiResponseEntity = resolutionsOrderPosRepository
                .createStoreResolutions(token, storeResolutionsEntity);

        if (!apiResponseEntity.getCode().equals(CREATED_CODE)) {
            throw new ResolutionCrudException(ERROR_CREATE_IN_ORDERS
                    .concat(apiResponseEntity.getMessage()));
        }

    }

    private static void buildIdsResolutions(
            List<ResponseResolutionsWithPosDTO> responseResolutionsList,
            StoreResolutionsDTO storeResolutions
    ) {
        List<ResolutionDTO> resolutions = storeResolutions.getResolutions();

        responseResolutionsList.forEach(responseResolution ->
                resolutions.forEach(resolution -> resolution.setId(responseResolution.getId()))
        );
    }
}
