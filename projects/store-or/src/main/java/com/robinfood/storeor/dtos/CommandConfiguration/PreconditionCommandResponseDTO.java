package com.robinfood.storeor.dtos.CommandConfiguration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreconditionCommandResponseDTO {

    private Long id;

    private String name;

}
