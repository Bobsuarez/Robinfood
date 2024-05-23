package com.robinfood.core.dtos.statusorderhistory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderStatusDTO {

    private final String code;

    private final Long id;
}
