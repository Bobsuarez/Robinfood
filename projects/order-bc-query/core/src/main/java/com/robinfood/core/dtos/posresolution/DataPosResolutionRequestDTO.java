package com.robinfood.core.dtos.posresolution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class DataPosResolutionRequestDTO {

    private LocalDate localDateStart;

    private LocalDate localDateEnd;

    private Long posId;

    private Long storeId;

    private String timeZone;

}
