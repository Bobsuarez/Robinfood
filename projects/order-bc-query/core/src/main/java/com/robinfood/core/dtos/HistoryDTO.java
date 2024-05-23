package com.robinfood.core.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoryDTO<T> {

    private final List<T> items;

    private final PropertyDTO properties;
}
