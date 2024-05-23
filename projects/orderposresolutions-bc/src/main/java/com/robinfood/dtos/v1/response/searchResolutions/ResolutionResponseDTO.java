package com.robinfood.dtos.v1.response.searchResolutions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResolutionResponseDTO {

    private String endDate;
    private Long id;
    private String invoiceNumberResolution;
    private Integer invoiceNumberEnd;
    private Integer invoiceNumberInitial;
    private String name;
    private Boolean linkedToPos;
    private String prefix;
    private String initialDate;
    private Boolean current;
    private Long resolutionId;
    private String typeDocument;

}
