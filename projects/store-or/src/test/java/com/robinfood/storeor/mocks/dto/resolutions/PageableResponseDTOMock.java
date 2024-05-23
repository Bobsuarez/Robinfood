package com.robinfood.storeor.mocks.dto.resolutions;

import com.robinfood.storeor.dtos.response.PageableResponseDTO;

public class PageableResponseDTOMock {

    public PageableResponseDTO defaultData(){
        return PageableResponseDTO.builder()
                .pageNumber(1)
                .pageSize(10)
                .total(1).build();
    }

}
