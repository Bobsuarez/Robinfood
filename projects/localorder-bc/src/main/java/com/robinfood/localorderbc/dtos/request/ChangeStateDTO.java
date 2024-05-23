package com.robinfood.localorderbc.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeStateDTO {

    private String brandId;

    private Long channelId;

    private String notes;

    private String orderId;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private Long storeId;

    @Builder.Default
    private Long userId = 1L;

}
