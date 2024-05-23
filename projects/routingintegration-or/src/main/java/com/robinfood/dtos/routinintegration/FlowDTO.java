package com.robinfood.dtos.routinintegration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FlowDTO {

    public String code;

    public Long flowId;

    public String name;
}
