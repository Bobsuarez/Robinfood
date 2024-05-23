package com.robinfood.core.dtos.staterequestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateChangeRequestDTO {

    private String brandId;

    private String deliveryIntegrationId;

    private String notes;

    private Long orderId;

    private String orderUuid;

    private String origin;

    @Size(max = 50, min = 3)
    @NotEmpty(message = "Please enter status code")
    private String statusCode;

    private Long statusId;

    @NotNull(message = "Please enter userId")
    private Long userId;
}
