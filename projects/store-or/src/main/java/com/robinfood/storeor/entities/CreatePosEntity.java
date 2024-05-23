package com.robinfood.storeor.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePosEntity {

    private final String code;
    private final Long id;
    private final String name;
    private final Long posTypeId;
    private ResolutionsIdsEntity resolutionsIds;
    private final Boolean status;

}
