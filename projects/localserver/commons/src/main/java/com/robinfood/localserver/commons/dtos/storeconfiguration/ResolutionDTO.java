package com.robinfood.localserver.commons.dtos.storeconfiguration;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResolutionDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    private String finalNumber;

    private Long id;

    private String invoiceNumberResolutions;

    private String prefix;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    private String startingNumber;
}
