package com.robinfood.repository.configurationsbc;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.logs.OrderWarningLogEnum;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.models.domain.configuration.Channel;
import com.robinfood.network.api.ConfigurationsBCAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.robinfood.core.enums.TransactionCreationErrors.GET_POS_ID_ERROR;
import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Slf4j
@Repository
public class ConfigurationsDataSource implements IConfigurationsDataSource{

    private final ConfigurationsBCAPI configurationsBCAPI;

    public ConfigurationsDataSource(ConfigurationsBCAPI configurationsBCAPI) {
        this.configurationsBCAPI = configurationsBCAPI;
    }

    @Override
    public Channel getChannelById(String token, Long channelId) {

        log.info("getChannel start with id {} ", channelId);

        Result<ApiResponseEntity<Channel>> response =
                safeAPICall(configurationsBCAPI.getChannel(token, channelId));

        if (response instanceof Result.Error) {

            final Result.Error error = (Result.Error) response;

            log.warn(OrderWarningLogEnum.WARN_ID_CHANNEL_NOT_FOUND.getMessage());

            throw new TransactionCreationException(
                    error.component1().getMessage(),
                    format("Error obtain channel info with channel_id [%s]", channelId),
                    GET_POS_ID_ERROR,
                    PRECONDITION_FAILED
            );
        }

        return ((Result.Success<ApiResponseEntity<Channel>>) response).getData()
                .getData();
    }
}
