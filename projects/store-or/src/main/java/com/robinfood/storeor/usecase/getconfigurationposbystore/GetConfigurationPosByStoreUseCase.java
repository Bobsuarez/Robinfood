package com.robinfood.storeor.usecase.getconfigurationposbystore;

import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.StorePosEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IStorePosMapper;
import com.robinfood.storeor.repositories.configurationposbystorerepository.IStorePosRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetConfigurationPosByStoreUseCase implements IGetConfigurationPosByStoreUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IStorePosRepository storePosRepository;

    private final IStorePosMapper storePosMapper;


    public GetConfigurationPosByStoreUseCase(IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
                                             IStorePosRepository storePosRepository,
                                             IStorePosMapper storePosMapper) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.storePosRepository = storePosRepository;
        this.storePosMapper = storePosMapper;
    }

    @Override
    public List<StorePosDTO> invoke(Long storeId) {

        log.info("Execute GetConfigurationPosByStoreUseCase invoke param storeId: {} ", storeId);

        TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("get token " + token.getAccessToken());

        APIResponseEntity<List<StorePosEntity>> storePosEntities = storePosRepository
                .getConfigurationPosByStore(storeId, token.getAccessToken());

        log.info("get storePosEntities " + storePosEntities);

        return storePosMapper.storePosEntitiesToStorePosDTOs(storePosEntities.getData());
    }
}
