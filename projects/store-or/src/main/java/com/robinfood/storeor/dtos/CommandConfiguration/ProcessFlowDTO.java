package com.robinfood.storeor.dtos.CommandConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessFlowDTO {

    protected Long id;

    private String name;

    private Boolean proxyFlow;
}
