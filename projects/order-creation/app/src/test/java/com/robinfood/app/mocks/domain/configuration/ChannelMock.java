package com.robinfood.app.mocks.domain.configuration;


import com.robinfood.core.dtos.transactionrequestdto.ChannelDTO;
import com.robinfood.core.models.domain.configuration.Channel;

public class ChannelMock {

    private static final Long CHANNEL_ID = 1L;
    private static final String CHANNEL_NAME = "Cash";

    public static ChannelDTO build(){

        return ChannelDTO.builder()
                .id(CHANNEL_ID)
                .name(CHANNEL_NAME)
                .build();
    }

}
