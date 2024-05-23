package com.robinfood.core.dtos.ordersstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class DataOrderStoreRequestDTO {

    private LocalDate localDateStart;

    private LocalDate localDateEnd;

    private Long storeId;

    private String timeZone;
}
