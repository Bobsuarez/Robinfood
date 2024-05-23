package com.robinfood.core.dtos.orderspaid;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderUserDataDTO {

    private final String firstName;

    private final String lastName;

    private final String mobile;
}
