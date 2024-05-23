package com.robinfood.entities.api.routingintegration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResponseRoutingIntegrationEntity {

    private Long channelId;

    private String description;

    private FlowEntity flow;

    private Long id;

    private String name;

    private String url;

    private String uuid;
}
