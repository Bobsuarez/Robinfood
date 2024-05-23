package com.robinfood.core.dtos.searchcriteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SearchCriteriaDTO {

    private final Long id;

    private final String name;
}
