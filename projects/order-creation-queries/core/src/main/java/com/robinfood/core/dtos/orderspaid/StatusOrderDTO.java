package com.robinfood.core.dtos.orderspaid;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusOrderDTO {

    private final String code;

    private final Long id;
}
