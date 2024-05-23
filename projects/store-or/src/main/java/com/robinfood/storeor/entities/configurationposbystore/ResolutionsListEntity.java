package com.robinfood.storeor.entities.configurationposbystore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ResolutionsListEntity {

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
