package com.robinfood.core.entities;

import lombok.Data;

import java.util.List;

@Data
public class HistoryEntity<T> {

    private final List<T> history;

    private final PropertyEntity properties;
}
