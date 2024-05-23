package com.robinfood.storeor.usecase.getstorebyidusecase;

import com.robinfood.storeor.dtos.UserStoreConfigurationsResponseDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.RestResponsePage;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import com.robinfood.storeor.entities.UserStoreEntity;
import com.robinfood.storeor.mappers.IStoreMapper;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.StoreEntity;
import com.robinfood.storeor.repositories.configurationrepository.IConfigurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Implementation of IGetStoreByIdUseCase
 */
@Service
@Slf4j
public class GetStoreByIdUseCase implements IGetStoreByIdUseCase {

    private final IConfigurationRepository configurationRepository;
    private final IStoreMapper storeMapper;

    public GetStoreByIdUseCase(
            IConfigurationRepository configurationRepository,
            IStoreMapper storeMapper
    ) {
        this.configurationRepository = configurationRepository;
        this.storeMapper = storeMapper;
    }

    public StoreResponseDTO invoke(Long storeId, String token) {

        log.info("Get store by id use case execute invoke get configuration by store: {}", storeId);

        APIResponseEntity<StoreEntity> storeEntityAPIResponseEntity = configurationRepository.getStore(storeId,token);

        return storeMapper.storeEntityToStoreResponseDto(storeEntityAPIResponseEntity.getData());
    }

    @Override
    public Page<StoreResponseDTO> invoke(String name, Long companyCountryId, Integer page,
        Integer size, Sort sort, String accessToken) {

        log.info("Get store list use case execute invoke get configuration store list.");

        APIResponseEntity<RestResponsePage<StoreEntity>> storeEntityAPIResponseEntity =
            configurationRepository.getStore(name, companyCountryId, page, size, sort, accessToken);

        return storeEntityAPIResponseEntity.getData()
            .map(storeMapper::storeEntityToStoreResponseDto);
    }

    @Override
    public UserStoreConfigurationsResponseDTO findStoreByUserId(Long userId, String token) {
        log.info("Param userId " + userId);
        APIResponseEntity<UserStoreEntity> userStoreEntityAPIResponseEntity =  configurationRepository
                .findStoreByUserId(userId, token);
        log.info("Api response" + userStoreEntityAPIResponseEntity);
        StoreResponseDTO storeResponseDTO = storeMapper
                .storeEntityToStoreResponseDto(userStoreEntityAPIResponseEntity.getData().getStore());
        return UserStoreConfigurationsResponseDTO.builder().store(storeResponseDTO).userId(userId).build();
    }

}
