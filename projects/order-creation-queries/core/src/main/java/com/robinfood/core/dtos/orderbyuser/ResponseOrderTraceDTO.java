package com.robinfood.core.dtos.orderbyuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseOrderTraceDTO {

    private String description;

    private String datetime;

    private Long id;

    private String name;

}
