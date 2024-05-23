package com.robinfood.core.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoryDTO<T> {

    private final List<T> history;

    private final PropertyDTO properties;
}
