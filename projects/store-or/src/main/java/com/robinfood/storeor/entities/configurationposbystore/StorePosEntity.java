package com.robinfood.storeor.entities.configurationposbystore;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StorePosEntity {
    private final Long id;
    private final String name;
    private final List<ResolutionEntity> resolutions;
}
