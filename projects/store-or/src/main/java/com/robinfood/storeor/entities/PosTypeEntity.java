package com.robinfood.storeor.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PosTypeEntity {

    private final Long id;
    private final String name;
}
