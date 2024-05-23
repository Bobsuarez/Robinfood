package com.robinfood.customersbc.thirdparties.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3446161753694779895L;

    @Valid
    private CustomerDTO customer;
}
