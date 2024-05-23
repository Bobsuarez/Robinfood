package com.robinfood.localprinterbc.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResolutionDTO {
    private Long id;
    private String invoiceNumberResolutions;
    private String startDate;
    private String endDate;
    private String startingNumber;
    private String finalNumber;
    private String prefix;
}
