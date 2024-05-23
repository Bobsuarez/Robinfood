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
public class TraceDTO implements Serializable {

    private String createdAt;

    private Long id;

    private String message;

    private String name;


}
