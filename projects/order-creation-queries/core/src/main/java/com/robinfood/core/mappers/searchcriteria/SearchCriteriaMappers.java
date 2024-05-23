package com.robinfood.core.mappers.searchcriteria;

import com.robinfood.core.dtos.searchcriteria.SearchCriteriaDTO;
import com.robinfood.core.enums.SearchCriteriaEnum;

public final class SearchCriteriaMappers {

    private SearchCriteriaMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static SearchCriteriaDTO searchCriteriaEnumToSearchCriteriaDto(SearchCriteriaEnum searchCriteriaEnum) {
        return SearchCriteriaDTO.builder()
                .id(searchCriteriaEnum.getId())
                .name(searchCriteriaEnum.getCriteriaName())
                .build();
    }
}
