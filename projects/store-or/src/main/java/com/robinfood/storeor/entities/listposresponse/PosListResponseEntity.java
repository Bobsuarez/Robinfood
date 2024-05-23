package com.robinfood.storeor.entities.listposresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PosListResponseEntity {

    private String code;

    private Long id;

    private String name;

    private Long posTypeId;

    private ResolutionResponseEntity resolution;

    private Boolean status;

    private StorePosResponseEntity store;
}
