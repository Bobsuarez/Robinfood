package com.robinfood.storeor.entities.configurationposbystore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchResolutionEntity {

    private String orderByEndDateResolution;
    private Integer page;
    private Long resolutionId;
    private Integer size;
    private Boolean status;
    private String valueCustomFilter;
    private Boolean withPos;

}
