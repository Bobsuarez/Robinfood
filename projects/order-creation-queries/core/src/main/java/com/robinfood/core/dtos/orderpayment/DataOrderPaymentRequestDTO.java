package com.robinfood.core.dtos.orderpayment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class DataOrderPaymentRequestDTO {

    private LocalDate localDateStart;

    private LocalDate localDateEnd;

    private Long posId;

    private String timeZone;

}
