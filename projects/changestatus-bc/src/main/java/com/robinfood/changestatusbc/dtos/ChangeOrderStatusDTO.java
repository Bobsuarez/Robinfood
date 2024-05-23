package com.robinfood.changestatusbc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeOrderStatusDTO {

    private Long channelId;

    private String brandId;

    private String deliveryIntegrationId;

    private String notes;

    private Long orderId;

    private String orderUid;

    private String orderUuid;

    private String origin;

    @Size(max = 50, min = 3)
    @NotEmpty(message = "Please enter status code")
    private String statusCode;

    private Long statusId;

    private Long transactionId;

    @NotNull(message = "Please enter userId")
    private Long userId;

    private String transactionUuid;
}
