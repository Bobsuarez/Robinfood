package com.robinfood.repository.configuration.channels;

import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.ConfigurationBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.robinfood.core.constants.GlobalConstants.SORT_ENTITY;

/**
 * Implementation of IChannelRepository
 */
@Repository
@Slf4j
public class ChannelRepository implements IChannelRepository {

    private final ConfigurationBcAPI configurationBcAPI;

    public ChannelRepository(ConfigurationBcAPI configurationBcAPI) {
        this.configurationBcAPI = configurationBcAPI;
    }

    @Override
    public Result<ChannelsDTO> getChannels(
            String token
    ) {
        log.info("Invoke get configuration bc query");

        final Result<APIResponseEntity<ChannelsDTO>> result = NetworkExtensionsKt.safeAPICall(
                configurationBcAPI.getChannels(
                        SORT_ENTITY,
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<ChannelsDTO>> data =
                ((Result.Success<APIResponseEntity<ChannelsDTO>>) result);

        return new Result.Success(
                data.getData().getData()
        );
    }
}
