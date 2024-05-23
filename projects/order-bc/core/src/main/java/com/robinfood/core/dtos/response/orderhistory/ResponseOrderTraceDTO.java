package com.robinfood.core.dtos.response.orderhistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ResponseOrderTraceDTO {

    private String description;

    private LocalDateTime datetime;

    private Long id;

    private String name;

}
