package com.robinfood.storeor.usecase.getlistpos;

import com.robinfood.storeor.dtos.apiresponsebuilder.RestResponsePage;
import com.robinfood.storeor.dtos.listposresponse.PosListResponseDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.listposresponse.PosListResponseEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IPosResponseMapper;
import com.robinfood.storeor.repositories.configurationrepository.IConfigurationRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetListPos implements IGetListPos {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IConfigurationRepository configurationRepository;
    private final IPosResponseMapper posResponseMapper;

    public GetListPos(IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
                      IConfigurationRepository configurationRepository, IPosResponseMapper posResponseMapper) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.configurationRepository = configurationRepository;
        this.posResponseMapper = posResponseMapper;
    }

    @Override
    public Page<PosListResponseDTO> invoke(
            Integer page, String posName, Integer size,
            Long status, Long storeId, Sort sort
    ) {

        TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        APIResponseEntity<RestResponsePage<PosListResponseEntity>> restResponsePageAPIResponseEntity =
                configurationRepository.getListPos(page, posName, size, status, storeId, sort, token.getAccessToken());

        return restResponsePageAPIResponseEntity.getData()
                .map(posResponseMapper::posResponseEntityToPosResponseDTO);
    }
}
