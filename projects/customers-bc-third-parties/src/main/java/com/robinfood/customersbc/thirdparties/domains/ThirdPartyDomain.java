package com.robinfood.customersbc.thirdparties.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = 651065573511492844L;

    private Long id;
    private String firstName;
    private String lastName;
    private Integer identificationTypeId;
    private String identificationNumber;
    private String email;
}
