package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.ChannelDTO;
import com.robinfood.core.dtos.transactionrequestdto.OriginDTO;
import com.robinfood.core.models.domain.configuration.Channel;

public final class ConfigurationsMappers {

    private ConfigurationsMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ChannelDTO toChannelChannelDTO (Channel channel) {

        return new ChannelDTO(
                channel.getId(),
                channel.getName()
        );
    }

    public static OriginDTO toChannelDTOtoOriginDTO (ChannelDTO channelDTO) {

        return  new OriginDTO(
          channelDTO.getId(),
          channelDTO.getName()
        );
    }
}
