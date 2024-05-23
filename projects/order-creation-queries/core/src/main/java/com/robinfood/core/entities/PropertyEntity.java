package com.robinfood.core.entities;

import lombok.Data;

@Data
public class PropertyEntity {

    private final Integer currentPage;

    private final Integer limit;

    private final Integer totalPages;

    private final Integer totalRows;
}
