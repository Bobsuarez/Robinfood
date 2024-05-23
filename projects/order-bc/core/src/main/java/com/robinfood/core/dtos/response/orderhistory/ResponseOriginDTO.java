package com.robinfood.core.dtos.response.orderhistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class ResponseOriginDTO {

    private Long companyId;

    private Long platformId;

    private ResponseStoreDTO store;

}
