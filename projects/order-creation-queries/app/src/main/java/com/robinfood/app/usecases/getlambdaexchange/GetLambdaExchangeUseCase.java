package com.robinfood.app.usecases.getlambdaexchange;

import com.google.gson.JsonObject;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.lambdaexchange.ILambdaExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetLambdaExchangeUseCase implements IGetLambdaExchangeUseCase{

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final ILambdaExchangeRepository lambdaExchangeRepository;

    public GetLambdaExchangeUseCase (
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            ILambdaExchangeRepository lambdaExchangeRepository
    ) {

        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.lambdaExchangeRepository = lambdaExchangeRepository;
    }

    @Override
    public Result<JsonObject> invoke(
            String currentDate,
            String previousDate
    ) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        return  lambdaExchangeRepository.getExchanges(
                currentDate,
                previousDate,
                token.getAccessToken());
    }
}
