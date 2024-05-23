package com.robinfood.core.entities;

import lombok.Data;

import java.util.List;

@Data
public class MenuCurrentEntity {

    private final List<MenuHallsEntity> halls;

    private final String name;
}
