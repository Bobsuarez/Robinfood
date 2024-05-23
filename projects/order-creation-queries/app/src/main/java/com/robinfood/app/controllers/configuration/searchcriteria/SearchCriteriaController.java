package com.robinfood.app.controllers.configuration.searchcriteria;

import com.robinfood.app.usecases.getsearchcriteria.IGetSearchCriteriaUseCase;
import com.robinfood.core.dtos.searchcriteria.SearchCriteriaDTO;
import com.robinfood.core.dtos.ApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.SEARCH_CRITERIA;
import static com.robinfood.core.constants.APIConstants.SEARCH_CRITERIA_V1;

@RequestMapping(SEARCH_CRITERIA_V1)
@RestController
@Slf4j
public class SearchCriteriaController implements ISearchCriteriaController {

    private final IGetSearchCriteriaUseCase getSearchCriteriaUseCase;

    public SearchCriteriaController(IGetSearchCriteriaUseCase getSearchCriteriaUseCase) {
        this.getSearchCriteriaUseCase = getSearchCriteriaUseCase;
    }

    @Override
    @GetMapping(SEARCH_CRITERIA)
    public ResponseEntity<ApiResponseDTO<List<SearchCriteriaDTO>>> invoke() {

        log.info("Receiving request get Search criteria");

        ApiResponseDTO<List<SearchCriteriaDTO>> apiResponseDTO;
        HttpStatus httpStatus = HttpStatus.OK;

        List<SearchCriteriaDTO> searchCriteriaDTOList = getSearchCriteriaUseCase.invoke();

        apiResponseDTO = new ApiResponseDTO<>(
                searchCriteriaDTOList,
                httpStatus
        );

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
