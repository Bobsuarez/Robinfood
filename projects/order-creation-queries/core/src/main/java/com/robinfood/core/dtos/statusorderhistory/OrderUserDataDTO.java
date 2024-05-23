package com.robinfood.core.dtos.statusorderhistory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderUserDataDTO {

    private String firstName;

    private String lastName;
}
