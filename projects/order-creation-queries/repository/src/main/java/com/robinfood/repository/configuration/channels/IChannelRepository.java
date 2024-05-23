package com.robinfood.repository.configuration.channels;

import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.enums.Result;

public interface IChannelRepository {

    /**
     * Get channels configuration
     *
     * @param token Token auth service
     * @return Result channels
     */
    Result<ChannelsDTO> getChannels(String token);
}
