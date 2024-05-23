package com.robinfood.storeor.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StoreCreatePosEntity {

    private final List<CreatePosEntity> pos;

    private final Long storeId;
}
