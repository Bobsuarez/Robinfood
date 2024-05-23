package com.robinfood.storeor.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreMultiBrandEntity {

    private String image;

    private String color;
}