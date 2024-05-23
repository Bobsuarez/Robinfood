package com.robinfood.changestatusor.usecases.gettokenbusinesscapability;

import com.robinfood.changestatusor.models.domain.Token;
import com.robinfood.changestatusor.repository.token.ITokenToBusinessCapabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetTokenBusinessCapabilityUseCase implements IGetTokenBusinessCapabilityUseCase{

    private final ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository;

    public GetTokenBusinessCapabilityUseCase(
            ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository
    ) {
        this.getTokenToBusinessCapabilityRepository = getTokenToBusinessCapabilityRepository;
    }

    @Override
    public Token invoke() {

        return this.getTokenToBusinessCapabilityRepository.get();
    }

}
