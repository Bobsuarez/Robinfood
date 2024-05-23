package com.robinfood.storeor.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResolutionsIdsEntity {

    private Long id;
    private Long resolutionId;
}
