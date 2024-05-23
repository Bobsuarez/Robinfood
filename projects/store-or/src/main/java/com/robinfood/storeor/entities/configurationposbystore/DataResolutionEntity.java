package com.robinfood.storeor.entities.configurationposbystore;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataResolutionEntity {

    private List<ResolutionsListEntity> content;

    private PageableEntity pageable;
}
