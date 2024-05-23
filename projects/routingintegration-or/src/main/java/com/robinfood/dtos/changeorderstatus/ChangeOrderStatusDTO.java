package com.robinfood.dtos.changeorderstatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeOrderStatusDTO {

    public String brandId;

    public String notes;

    public String orderId;

    public String origin;

    public String statusCode;

    public Long userId;
}
