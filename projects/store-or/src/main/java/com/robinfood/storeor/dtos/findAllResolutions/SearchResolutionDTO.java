package com.robinfood.storeor.dtos.findAllResolutions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class SearchResolutionDTO {

    private String orderByEndDateResolution;
    @NotNull
    private Integer page;
    private Long resolutionId;
    @NotNull
    private Integer size;
    private Boolean status;
    private String valueCustomFilter;
    private Boolean withPos;

}
