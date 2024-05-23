package com.robinfood.storeor.dtos.resolutions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ResolutionsIdsDTO {


    @JsonProperty(value = "id")
    private Long id;


    @JsonProperty(value = "resolutionId")
    private Long resolutionId;
}
