package com.robinfood.core.dtos.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeOrderStatusDTO implements Serializable {

    private static final long serialVersionUID = -3218870724848871132L;

    private String notes;

    private Long orderId;

    private Long orderUid;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private String transactionUuid;

    private Long userId;

    private UUID uuid;
}
