package com.robinfood.core.dtos.report.ordercancellation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderUserDataCancellationDTO {

    private String firstName;

    private String lastName;

    private String mobile;
}
