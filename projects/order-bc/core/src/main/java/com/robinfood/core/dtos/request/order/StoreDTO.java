package com.robinfood.core.dtos.request.order;

import lombok.Data;

@Data
public class StoreDTO {

    private final Long id;

    private final String name;

    private final Long posId;
}
