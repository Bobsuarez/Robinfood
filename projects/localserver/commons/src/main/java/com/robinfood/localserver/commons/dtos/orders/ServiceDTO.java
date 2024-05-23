package com.robinfood.localserver.commons.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ServiceDTO {

    private Long id;

    private String name;

    private Long quantity;

    private Double unitPrice;
}
