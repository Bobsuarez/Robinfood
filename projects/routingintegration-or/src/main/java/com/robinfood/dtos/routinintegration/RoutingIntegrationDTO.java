package com.robinfood.dtos.routinintegration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RoutingIntegrationDTO {

    private Long channelId;

    private String description;

    private FlowDTO flow;

    private Long id;

    private String name;

    private String url;

    private String uuid;
}
