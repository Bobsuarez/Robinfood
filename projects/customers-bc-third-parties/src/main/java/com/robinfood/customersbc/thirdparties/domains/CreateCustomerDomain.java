package com.robinfood.customersbc.thirdparties.domains;

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
public class CreateCustomerDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = -8992712260504924466L;

    private CustomerDomain customer;
}
