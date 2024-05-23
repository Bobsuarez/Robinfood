package com.robinfood.core.dtos.orderbyuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseOriginDTO {

    private Long companyId;

    private Long platformId;

    private ResponseStoreDTO store;

}
