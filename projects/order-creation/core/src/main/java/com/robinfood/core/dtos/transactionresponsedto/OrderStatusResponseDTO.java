package com.robinfood.core.dtos.transactionresponsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderStatusResponseDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
}
