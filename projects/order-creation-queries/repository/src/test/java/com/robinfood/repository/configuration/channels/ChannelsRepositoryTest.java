package com.robinfood.repository.configuration.channels;

import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.ConfigurationBcAPI;
import com.robinfood.repository.mocks.ConfigChannelsDTOMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChannelsRepositoryTest {

    @Mock
    private ConfigurationBcAPI configurationBcAPI;

    @Mock
    private Call<APIResponseEntity<ChannelsDTO>> responseEntityCall;

    @InjectMocks
    private ChannelRepository channelRepository;

    private final String token = "token";

    @Test
    void test_GetConfigChannels_Should_OK_When_DataIsCorrect() throws Exception {

        when(configurationBcAPI.getChannels(
                any(),
                anyString()
        )).thenReturn(responseEntityCall);
        when(responseEntityCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        ConfigChannelsDTOMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        channelRepository.getChannels(
                token
        );

        verify(configurationBcAPI)
                .getChannels(
                        any(),
                        anyString()
                );
    }

    @Test
    void test_GetConfigChannels_Should_InternalServerError_When_WrongAnswerOfConfigurationBC() throws Exception {

        when(configurationBcAPI.getChannels(
                any(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        List.of(),
                        "CO",
                        "",
                        "Order filter not be returned"
                ))
                ))
        );

        channelRepository.getChannels(
                token
        );

        verify(configurationBcAPI)
                .getChannels(
                        any(),
                        anyString()
                );
    }

}
