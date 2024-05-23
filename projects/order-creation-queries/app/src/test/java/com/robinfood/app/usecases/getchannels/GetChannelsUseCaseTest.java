package com.robinfood.app.usecases.getchannels;

import com.robinfood.app.mocks.ConfigChannelsDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.channels.IChannelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetChannelsUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IChannelRepository channelRepository;

    @InjectMocks
    private GetConfigChannelsUseCase getConfigChannelsUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(channelRepository.getChannels(anyString()))
                .thenReturn(new Result.Success<>(ConfigChannelsDTOMock.getDataDefault()));
        getConfigChannelsUseCase.invoke();

        verify(channelRepository)
                .getChannels(
                        anyString()
                );
    }
}
