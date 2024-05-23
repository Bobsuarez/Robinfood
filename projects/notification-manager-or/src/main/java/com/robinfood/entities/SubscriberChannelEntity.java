package com.robinfood.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SubscriberChannelEntity {

    private FlowEntity flow;
    private String uuid;
    private List<SubscriberEntity> subscribers;
}
