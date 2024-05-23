package com.robinfood.storeor.dtos.listposresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResolutionResponseDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;

    private String finalNumber;

    private Long id;

    private String invoiceNumberResolutions;

    private String name;

    private String prefix;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDate;

    private String startingNumber;
}
