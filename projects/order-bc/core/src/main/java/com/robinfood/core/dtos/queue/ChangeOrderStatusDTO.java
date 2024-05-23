package com.robinfood.core.dtos.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class ChangeOrderStatusDTO  implements Serializable {

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
