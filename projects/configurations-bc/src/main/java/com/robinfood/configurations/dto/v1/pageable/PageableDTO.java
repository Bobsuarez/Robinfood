package com.robinfood.configurations.dto.v1.pageable;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableDTO implements Serializable {

    private static final long serialVersionUID = -776789533357192083L;

    @JsonProperty
    private SortDTO sort;

    @JsonProperty
    private long offset;

    @JsonProperty
    private int pageNumber;

    @JsonProperty
    private int pageSize;

    @JsonProperty
    private boolean paged;

    @JsonProperty
    private boolean unpaged;

}
