package com.robinfood.storeor.entities.configurationposbystore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageableEntity {

    private int pageNumber;
    private int pageSize;
    private int total;

}
