package com.robinfood.storeor.mocks.dto.resolutions;

import com.robinfood.storeor.dtos.findAllResolutions.SearchResolutionDTO;

public class SearchResolutionDTOMock {

    public SearchResolutionDTO defaultData(){
        return SearchResolutionDTO.builder()
                .orderByEndDateResolution(null)
                .resolutionId(null)
                .page(1)
                .size(1)
                .status(Boolean.TRUE)
                .valueCustomFilter(null).build();
    }
}
