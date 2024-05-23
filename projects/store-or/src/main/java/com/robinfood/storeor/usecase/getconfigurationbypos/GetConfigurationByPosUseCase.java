package com.robinfood.storeor.usecase.getconfigurationbypos;

import com.robinfood.storeor.dtos.response.PosResponseDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IPosMapper;
import com.robinfood.storeor.repositories.posrepository.IPosRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetConfigurationByPosUseCase implements  IGetConfigurationByPosUseCase{

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IPosRepository posRepository;

    private final IPosMapper iPosMapper;


    public GetConfigurationByPosUseCase(IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
                                        IPosRepository posRepository,
                                        IPosMapper iPosMapper) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.posRepository = posRepository;
        this.iPosMapper = iPosMapper;
    }

    @Override
    public PosResponseDTO invoke(Long storeId, Long userId) {

        log.info("Execute GetConfigurationByPosUseCase invoke param storeId: {} and userId: {}", storeId, userId);

        TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("get token " + token.getAccessToken());

        APIResponseEntity<PosEntity> posEntity = posRepository.getPosConfiguration(token.getAccessToken(),
                userId, storeId);

        log.info("get posEntity " + posEntity);
        return iPosMapper.posEntityToPosResponseDTO(posEntity.getData());
    }
}
