package com.robinfood.localorderbc.entities.changestate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeStateEntity {

    private String brandId;

    private Long channelId;

    private String notes;

    private String orderId;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long storeId;

    private Long statusId;

    private Long userId;

}
