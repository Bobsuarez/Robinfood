package com.robinfood.datamock;

import com.robinfood.dtos.SubscriberChannelDTO;

import java.util.List;

public class SubscriberChannelDTOMock {

    public static SubscriberChannelDTO getDefault() {

        return SubscriberChannelDTO.builder()
                .id(2L)
                .flow(FlowDTOMock.getDefault())
                .subscribers(List.of(SubscriberDTOMock.getDefault()))
                .build();
    }
}
