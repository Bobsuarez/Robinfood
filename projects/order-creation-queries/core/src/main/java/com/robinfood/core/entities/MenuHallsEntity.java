package com.robinfood.core.entities;

import lombok.Data;

import java.util.List;

@Data
public class MenuHallsEntity {

    private final Long id;

    private final String name;

    private final Long position;

    private final List<MenuProductEntity> products;
}
