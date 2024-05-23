package com.robinfood.app.usecases.getbrands;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.configuration.BrandsDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.brands.IBrandsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetBrandsUseCase implements IGetBrandsUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IBrandsRepository brandsRepository;

    public GetBrandsUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IBrandsRepository brandsRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.brandsRepository = brandsRepository;
    }

    @Override
    public Result<List<BrandDTO>> invoke() {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        Result<BrandsDTO> response = this.brandsRepository.getAll(token.getAccessToken());

        return new Result.Success(((Result.Success<BrandsDTO>) response).getData().getContent());
    }
}
