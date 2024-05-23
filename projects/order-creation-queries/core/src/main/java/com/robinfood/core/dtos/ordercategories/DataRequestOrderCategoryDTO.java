package com.robinfood.core.dtos.ordercategories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class DataRequestOrderCategoryDTO {

    private LocalDate localDateStart;

    private LocalDate localDateEnd;

    private Long posId;

    private String timeZone;
}
