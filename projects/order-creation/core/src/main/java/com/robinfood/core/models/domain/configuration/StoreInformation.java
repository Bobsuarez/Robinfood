package com.robinfood.core.models.domain.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreInformation {

    private String address;

    private City city;

    private Country country;

    private String email;

    private Long id;

    private FlowTax flowTax;

    private String identification;

    private String internalName;

    private String location;

    private String name;

    private String phone;

    private String postalCode;

    private State state;

    private String timezone;

}
