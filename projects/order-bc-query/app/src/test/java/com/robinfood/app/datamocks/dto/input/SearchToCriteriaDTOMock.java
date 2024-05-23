package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;

public class SearchToCriteriaDTOMock {

    public static SearchToCriteriaDTO getDefault() {
        return SearchToCriteriaDTO.builder()
                .key("column")
                .value("value")
                .build();
    }
}
