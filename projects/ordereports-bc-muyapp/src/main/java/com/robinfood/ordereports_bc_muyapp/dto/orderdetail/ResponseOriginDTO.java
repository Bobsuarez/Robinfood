package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOriginDTO {

    private Short id;

    private Short companyId;

    private Short platformId;

    private ResponseStoreDTO store;

}
