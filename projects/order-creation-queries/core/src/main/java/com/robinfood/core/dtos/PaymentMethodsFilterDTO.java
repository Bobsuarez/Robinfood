package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentMethodsFilterDTO {

    private Long id;
    private String name;

}
