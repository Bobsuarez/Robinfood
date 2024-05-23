package com.robinfood.app.usecases.getchannels;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.channels.IChannelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetConfigChannelsUseCase implements IGetConfigChannelsUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IChannelRepository channelRepository;

    public GetConfigChannelsUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IChannelRepository channelRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.channelRepository = channelRepository;
    }

    @Override
    public Result<ChannelsDTO> invoke() {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        return channelRepository.getChannels(token.getAccessToken());
    }
}
