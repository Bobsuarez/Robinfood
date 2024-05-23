package com.robinfood.app.mappers;

import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;
import org.springframework.stereotype.Component;

@Component
public class SearchToCriteriaMapper {

    public static SearchToCriteriaDTO buildDataSearch(
            String setKey,
            Object setValue
    ) {
        return SearchToCriteriaDTO.builder()
                .key(setKey)
                .value(setValue)
                .build();
    }
}
