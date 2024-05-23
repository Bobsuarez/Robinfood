package com.robinfood.repository.configurationsbc;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.core.models.domain.configuration.Channel;
import com.robinfood.network.api.ConfigurationsBCAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfigurationsDataSourceTest {

    private final Channel channel = new Channel(
            10L,"cash"
    );

    final ApiResponseEntity<Channel> apiResponseChannel = ApiResponseEntity.<Channel>builder()
            .code(200)
            .data(
                    Channel.builder()
                            .id(10L)
                            .name("cash")
                            .build()
            )
            .locale("CO")
            .message("Channel es valid")
            .build();

    final ApiResponseEntity<Boolean> apiErrorResponseChannel = ApiResponseEntity.<Boolean>builder()
            .code(400)
            .error("Error")
            .locale("CO")
            .message("Error validating menu")
            .build();

    @Mock
    private ConfigurationsBCAPI configurationsBCAPI;

    @Mock
    private Call<ApiResponseEntity<Channel>> mockGetChannel;

    @InjectMocks
    private ConfigurationsDataSource configurationsDataSource;

    @Test
    void  test_getChannelById_Should_GetChannel_When_GetTheDetailOfAChannel() throws Exception {

        when(configurationsBCAPI.getChannel(anyString(), anyLong()))
                .thenReturn(mockGetChannel);

        when(mockGetChannel.execute()).thenReturn(Response.success(apiResponseChannel));

        final Channel channelResult = configurationsDataSource.getChannelById(anyString(), anyLong());

        assertEquals(channel, channelResult);

    }

    @Test
    void  test_getChannelById_Should_GetChannel_When_GetTheDetailOfAChannel_error() throws Exception {

        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseChannel);

        when(configurationsBCAPI.getChannel(anyString(), anyLong()))
                .thenReturn(mockGetChannel);

        when(mockGetChannel.execute()).thenReturn(Response.error(400, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            final Channel channelResult = configurationsDataSource.getChannelById(anyString(), anyLong());
            assertEquals(channel, channelResult);
        } catch (Exception exception) {
            assertFalse(exception.getLocalizedMessage().contains(apiErrorResponseChannel.getMessage()));
        }

    }
}
