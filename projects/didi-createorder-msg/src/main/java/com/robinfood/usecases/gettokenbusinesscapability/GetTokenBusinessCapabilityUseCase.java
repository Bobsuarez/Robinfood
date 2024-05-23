package com.robinfood.usecases.gettokenbusinesscapability;

import com.robinfood.entities.TokenEntity;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.repository.token.ITokenToBusinessCapabilityRepository;
import com.robinfood.repository.token.TokenToBusinessCapabilityRepository;
import com.robinfood.util.LogsUtil;
import org.apache.logging.log4j.util.Chars;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BEARER_AUTHORIZATION;

public class GetTokenBusinessCapabilityUseCase implements IGetTokenBusinessCapabilityUseCase {

    private final ITokenToBusinessCapabilityRepository tokenToBusinessCapabilityRepository;

    public GetTokenBusinessCapabilityUseCase() {
        this.tokenToBusinessCapabilityRepository = new TokenToBusinessCapabilityRepository();
    }

    public GetTokenBusinessCapabilityUseCase(ITokenToBusinessCapabilityRepository tokenToBusinessCapabilityRepository) {
        this.tokenToBusinessCapabilityRepository = tokenToBusinessCapabilityRepository;
    }

    @Override
    public String invoke() {

        final TokenEntity tokenEntity = tokenToBusinessCapabilityRepository.get();

        LogsUtil.info(AppLogsTraceEnum.GETTING_THE_SERVICE_TOKEN.getMessage());

        return DEFAULT_BEARER_AUTHORIZATION + Chars.SPACE +tokenEntity.getAccessToken();
    }
}
