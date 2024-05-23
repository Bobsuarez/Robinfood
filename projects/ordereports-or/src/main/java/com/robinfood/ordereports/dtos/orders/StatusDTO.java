package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatusDTO implements Serializable {

    private String code;

    private String createdAt;

    private String description;

    private Long id;

    private String name;

    private List<StatusTraceDTO> trace;
}
