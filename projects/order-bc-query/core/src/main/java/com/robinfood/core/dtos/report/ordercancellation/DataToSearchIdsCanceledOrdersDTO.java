package com.robinfood.core.dtos.report.ordercancellation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class DataToSearchIdsCanceledOrdersDTO {

    private List<Long> brandsIdsDTO;

    private Long companyIdDTO;

    private Integer currentPageDTO;

    private Long idCustomFilterDTO;

    private LocalDate localDateStartDTO;

    private LocalDate localDateEndDTO;

    private List<Long> originIdsDTO;
    
    private List<Long> paymentMethodIdsDTO;

    private Integer perPageDTO;

    private String statusCodeDTO;

    private List<Long> storesIdsDTO;

    private String timeZoneDTO;

    private String valueCustomFilterDTO;

}
