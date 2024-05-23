package com.robinfood.storeor.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PosEntity {

    private final String code;
    private final Long id;
    private final String name;
    private final PosTypeEntity posTypes;
    private final Boolean status;

}
