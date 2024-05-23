package com.robinfood.entities.api.routingintegration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FlowEntity {

    public String code;

    public Long flowId;

    public String name;
}
