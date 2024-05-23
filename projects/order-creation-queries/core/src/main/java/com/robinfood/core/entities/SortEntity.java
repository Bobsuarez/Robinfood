package com.robinfood.core.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SortEntity {

    private final boolean empty;

    private final boolean sorted;

    private final boolean unsorted;
}
