package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatusTraceDTO implements Serializable {

    private String createdAt;

    private String description;

    private Long id;

    private String name;
}
