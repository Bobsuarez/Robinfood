package com.robinfood.app.usecases.getsearchcriteria;

import com.robinfood.core.enums.SearchCriteriaEnum;
import com.robinfood.core.dtos.searchcriteria.SearchCriteriaDTO;
import com.robinfood.core.mappers.searchcriteria.SearchCriteriaMappers;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetSearchCriteriaUseCase implements IGetSearchCriteriaUseCase {

    @Override
    public List<SearchCriteriaDTO> invoke() {

        return Arrays.stream(SearchCriteriaEnum.values()).distinct()
                .map(SearchCriteriaMappers::searchCriteriaEnumToSearchCriteriaDto)
                .collect(Collectors.toList());
    }
}
