package com.robinfood.app.usecases.getstoresbycountryid;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mappers.StoreMappers;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.store.IStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetStoresByCountryIdUseCase implements IGetStoresByCountryIdUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IStoreRepository storeRepository;

    public GetStoresByCountryIdUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IStoreRepository storeRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.storeRepository = storeRepository;
    }

    @Override
    public Result<List<StoreWithIdAndNameDTO>> invoke(Long countryId) {

        final Optional<Long> getCountryId = Optional.of(countryId);
        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();
        final Result<List<StoreDTO>> getResultStores = storeRepository.getStores(token.getAccessToken());

        if (getResultStores instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) getResultStores).getHttpStatus(),
                    ((Result.Error) getResultStores).getException().getMessage()
            );
        }

        List<StoreDTO> getStores = ((Result.Success<List<StoreDTO>>) getResultStores).getData();

        getStores = getStores.stream().filter(storeDTO -> storeDTO.getCompany().getCountry()
                .getId().equals(getCountryId.get()))
                .collect(Collectors.toList());

        final List<StoreWithIdAndNameDTO> getStoreWithIdAndNameDTO = StoreMappers
                .storesDtoToStoreWithIdAndNameDto(getStores);

        return new Result.Success(getStoreWithIdAndNameDTO);
    }
}
