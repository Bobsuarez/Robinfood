package com.robinfood.dtos.v1.response.searchResolutions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PageableDTO {
    private int pageNumber;
    private int pageSize;
    private int total;

}