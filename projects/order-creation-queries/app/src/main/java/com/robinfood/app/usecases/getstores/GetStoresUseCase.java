package com.robinfood.app.usecases.getstores;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mappers.StoreMappers;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.store.IStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetStoresUseCase implements IGetStoresUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IStoreRepository storeRepository;

    public GetStoresUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IStoreRepository storeRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.storeRepository = storeRepository;
    }

    @Override
    public Result<List<StoreWithIdAndNameDTO>> invoke() {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();
        final Result<List<StoreDTO>> getResultStores = storeRepository.getStores(token.getAccessToken());
        final List<StoreDTO> getStores = ((Result.Success<List<StoreDTO>>) getResultStores).getData();
        final List<StoreWithIdAndNameDTO> getStoreWithIdAndNameDTO = StoreMappers
                .storesDtoToStoreWithIdAndNameDto(getStores);

        return new Result.Success(getStoreWithIdAndNameDTO);
    }
}
