package com.robinfood.configurationsposbc.dtos.pos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PosTypesDTO {

    private Long id;

    private String name;

}
