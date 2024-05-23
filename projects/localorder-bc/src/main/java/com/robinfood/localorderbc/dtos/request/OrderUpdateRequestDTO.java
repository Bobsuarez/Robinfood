package com.robinfood.localorderbc.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderUpdateRequestDTO {
    private Long orderId;
    private Long statusId;
}
