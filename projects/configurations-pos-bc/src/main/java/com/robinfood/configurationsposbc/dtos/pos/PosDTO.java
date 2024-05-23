package com.robinfood.configurationsposbc.dtos.pos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PosDTO {

    private String code;

    private Long id;

    private String name;

    private Long posTypeId;

    private Boolean status;

}
