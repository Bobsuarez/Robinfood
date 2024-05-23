package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OriginDTO implements Serializable {

    private Long companyId;

    private Long id;

    private Long platformId;

    private StoreDTO store;
}
