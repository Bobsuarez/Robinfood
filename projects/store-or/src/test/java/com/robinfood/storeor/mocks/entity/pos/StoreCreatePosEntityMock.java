package com.robinfood.storeor.mocks.entity.pos;

import com.robinfood.storeor.dtos.configurationpos.PosDTO;
import com.robinfood.storeor.dtos.configurationpos.StoreCreatePosDTO;
import com.robinfood.storeor.entities.*;

import java.util.ArrayList;
import java.util.List;

public class StoreCreatePosEntityMock {

    public static StoreCreatePosEntity getDataDefault(boolean status){

        return StoreCreatePosEntity.builder()
                .pos(List.of(CreatePosEntity.builder()
                        .code("AWS12345")
                        .name("TEST")
                        .posTypeId(1L)
                        .status(status)
                        .resolutionsIds(ResolutionsIdsEntity.builder().build())
                        .build()))
                .storeId(1L)
                .build();
    }

    public static StoreCreatePosEntity getStoreCreatePosWithoutResolution(boolean status){

        return StoreCreatePosEntity.builder()
                .pos(new ArrayList<>())
                .storeId(1L)
                .build();
    }

    public static StoreCreatePosEntity getStoreCreatePosWithResolution(boolean status){

        return StoreCreatePosEntity.builder()
                .pos(List.of(CreatePosEntity.builder()
                        .code("AWS12345")
                        .id(1L)
                        .name("TEST")
                        .posTypeId(1L)
                        .resolutionsIds(ResolutionsIdsEntity.builder().id(1L).resolutionId(1L).build())
                        .status(status)
                        .build()))
                .storeId(1L)
                .build();
    }
}
