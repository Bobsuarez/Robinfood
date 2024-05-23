package com.robinfood.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeStateResponseDTO {

    private String brandId;

    private String notes;

    private String orderId;

    private String origin;

    private String statusCode;

    private Long userId;
}
