package com.robinfood.storeor.mocks.entity.resolutions;

import com.robinfood.storeor.entities.configurationposbystore.PageableEntity;

public class PageableEntityMock {

    public static PageableEntity defaultData(){
        return PageableEntity.builder()
                .pageNumber(1)
                .pageSize(10)
                .total(1).build();
    }
}
