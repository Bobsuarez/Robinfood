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
public class CustomerDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = -5883699596939300926L;

    private Long id;
    private Long externalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneCode;
    private String phoneNumber;
    private ThirdPartyDomain thirdParty;
}
