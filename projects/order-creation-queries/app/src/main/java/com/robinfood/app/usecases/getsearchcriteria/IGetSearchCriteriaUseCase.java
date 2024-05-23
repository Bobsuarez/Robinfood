package com.robinfood.app.usecases.getsearchcriteria;

import com.robinfood.core.dtos.searchcriteria.SearchCriteriaDTO;

import java.util.List;

public interface IGetSearchCriteriaUseCase {

    List<SearchCriteriaDTO> invoke();
}
