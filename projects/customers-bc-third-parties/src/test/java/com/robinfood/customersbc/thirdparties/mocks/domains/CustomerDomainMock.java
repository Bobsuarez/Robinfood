package com.robinfood.customersbc.thirdparties.mocks.domains;

import com.robinfood.customersbc.thirdparties.domains.CustomerDomain;

import static com.robinfood.customersbc.thirdparties.mocks.domains.ThirdPartyDomainMock.getThirdPartyDomain;

public final class CustomerDomainMock {

    private CustomerDomainMock() {}

    public static CustomerDomain getCustomerDomain() {
        return CustomerDomain.builder()
            .id(1L)
            .externalId(22L)
            .firstName("Juan")
            .lastName("Perez")
            .email("juan@muy.com")
            .phoneCode("57")
            .phoneNumber("3008765544")
            .thirdParty(getThirdPartyDomain())
            .build();
    }

    public static CustomerDomain getCustomerDomainWithEmailNull() {
        return CustomerDomain.builder()
            .id(1L)
            .externalId(22L)
            .firstName("Juan")
            .lastName("Perez")
            .phoneCode("57")
            .phoneNumber("3008765544")
            .thirdParty(getThirdPartyDomain())
            .build();
    }
}
