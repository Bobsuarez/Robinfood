package com.robinfood.storeor.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PosResponseDTO {

    private Long id;
    private String code;
    private String name;
    private Boolean status;
    private PosTypeDTO posTypes;

}
