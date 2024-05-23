package com.robinfood.localorderbc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OrderDTO {

    private Long brandId;

    private String brandName;

    private String data;

    private Long id;

    private Long originId;

    private String originName;

    private Boolean printed;

    private Long statusId;

    private String statusName;

    private String uid;
}
