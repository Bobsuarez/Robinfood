package com.robinfood.core.dtos.orderspaid;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class DataOrdersPaidRequestDTO {

    private List<Long> brandIds;

    private Long companyId;

    private Integer currentPage;

    private Long idCustomFilter;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDateStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDateEnd;

    private List<Long> originIds;

    private List<Long> paymentMethodIds;

    private Integer perPage;

    private String statusCode;

    private List<Long> storeIds;

    private String timezone;

    private String valueCustomFilter;
}
