package com.robinfood.repository.configurationsbc;

import com.robinfood.core.models.domain.configuration.Channel;

public interface IConfigurationsDataSource {

    /**
     * Method that obtains the channelsId by the channel
     *
     * @param token to access configurations bc
     * @param channelId    of the channel
     *
     * @return store with posId
     */
    Channel getChannelById(
            String token,
            Long channelId
    );
}
