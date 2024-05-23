package com.robinfood.core.dtos.orderstatushistory;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderUserDataDTO {

    private String firstName;

    private String lastName;
}
