package com.robinfood.storeor.dtos.configurationposbystore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class StorePosDTO {
    private Long id;
    private String name;
    private List<ResolutionDTO> resolutions;
}
