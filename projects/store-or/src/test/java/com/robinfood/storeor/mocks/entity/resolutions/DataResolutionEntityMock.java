package com.robinfood.storeor.mocks.entity.resolutions;

import com.robinfood.storeor.entities.configurationposbystore.DataResolutionEntity;

import java.util.Arrays;

public class DataResolutionEntityMock {

    public DataResolutionEntity defaultData(){
        return DataResolutionEntity.builder()
                .content(Arrays.asList(ResolutionsListEntityMock.defaultData()))
                .pageable(PageableEntityMock.defaultData()).build();
    }
}
