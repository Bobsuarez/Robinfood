package com.robinfood.storeor.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResolutionsListResponseDTO {

    private Boolean current;
    private String endDate;
    private Long id;
    private String initialDate;
    private Integer invoiceNumberEnd;
    private Integer invoiceNumberInitial;
    private String invoiceNumberResolution;
    private Boolean linkedToPos;
    private String name;
    private String prefix;
    private Long resolutionId;
    private String typeDocument;
}
