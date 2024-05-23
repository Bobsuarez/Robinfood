package com.robinfood.dtos.v1.response.searchResolutions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DataResolutionResponseDTO {

    private List<ResolutionResponseDTO> content;

    private PageableDTO pageable;
}
