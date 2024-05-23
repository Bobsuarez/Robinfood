package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class PropertyDTO {

    private final Integer currentPage;

    private final Integer limit;

    private final Integer totalPages;

    private final Integer totalRows;
}
