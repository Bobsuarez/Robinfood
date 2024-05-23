package com.robinfood.app.mocks;

import com.robinfood.core.dtos.configuration.ChannelDTO;
import com.robinfood.core.dtos.configuration.ChannelsDTO;

import java.util.List;

public class ConfigChannelsDTOMock {

    public static ChannelsDTO getDataDefault() {
        return ChannelsDTO.builder()
                .content(List.of(ChannelDTO.builder()
                        .id(1)
                        .name("Pos")
                        .build()))
                .build();
    }
}
