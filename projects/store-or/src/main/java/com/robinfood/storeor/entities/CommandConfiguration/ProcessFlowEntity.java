package com.robinfood.storeor.entities.CommandConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessFlowEntity {

    protected Long id;

    private String name;

    private Boolean proxyFlow;
}
