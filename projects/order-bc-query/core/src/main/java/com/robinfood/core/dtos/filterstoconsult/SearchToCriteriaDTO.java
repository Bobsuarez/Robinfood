package com.robinfood.core.dtos.filterstoconsult;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SearchToCriteriaDTO {
    private String key;
    private Object value;
}

