package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO {

    @JsonProperty("perPage")
    private int perPage;

    @JsonProperty("page")
    private int page;

    @JsonProperty("lastPage")
    private int lastPage;

    @JsonProperty("total")
    private Long total;

}
