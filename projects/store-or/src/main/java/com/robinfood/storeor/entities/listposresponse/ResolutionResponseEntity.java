package com.robinfood.storeor.entities.listposresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResolutionResponseEntity implements Serializable {

    private LocalDateTime endDate;

    private String finalNumber;

    private Long id;

    private String invoiceNumberResolutions;

    private String name;

    private String prefix;

    private LocalDateTime startDate;

    private String startingNumber;
}
