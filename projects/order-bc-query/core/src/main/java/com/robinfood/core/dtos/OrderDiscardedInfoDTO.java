package com.robinfood.core.dtos;

import lombok.Builder;

import lombok.Value;

@Value
@Builder
public class OrderDiscardedInfoDTO {

    boolean discard;

    String uuid;
}
