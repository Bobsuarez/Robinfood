package com.robinfood.app.usecases.getposresolution;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetPosResolutionUseCase implements IGetPosResolutionUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IPosResolutionRepository iPosResolutionRepository;

    public GetPosResolutionUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IPosResolutionRepository iPosResolutionRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.iPosResolutionRepository = iPosResolutionRepository;
    }

    @Override
    public Result<GetPosResolutionsDTO> invoke(DataPosResolutionRequestDTO dataRequestDTO) {

        TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        return iPosResolutionRepository.getDataPosResolution(dataRequestDTO, token.getAccessToken());

    }
}
