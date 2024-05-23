package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class OrderStoreDTO {

    private final String name;

    private final Long id;

    private final Long posId;
}
