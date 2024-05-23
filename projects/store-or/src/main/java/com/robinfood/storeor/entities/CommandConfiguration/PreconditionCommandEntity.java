package com.robinfood.storeor.entities.CommandConfiguration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreconditionCommandEntity {

    private Long id;

    private String name;

}
