package com.robinfood.localserver.commons.entities.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ChangeStatusLocalRequestEntity {
    private Long orderId;
    private Long statusId;
}
