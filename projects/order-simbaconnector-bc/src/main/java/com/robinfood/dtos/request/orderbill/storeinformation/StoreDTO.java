package com.robinfood.dtos.request.orderbill.storeinformation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreDTO {

    private String code;

    private Long id;

    private Long posId;

    private Long posTypeId;

    private String postalCode;

}
