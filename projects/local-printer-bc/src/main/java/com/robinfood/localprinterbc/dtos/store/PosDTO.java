package com.robinfood.localprinterbc.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PosDTO {
    private Long id;
    private String name;
    private List<ResolutionDTO> resolutions;
}
