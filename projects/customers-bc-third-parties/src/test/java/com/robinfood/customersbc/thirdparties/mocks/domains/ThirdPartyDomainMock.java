package com.robinfood.customersbc.thirdparties.mocks.domains;

import com.robinfood.customersbc.thirdparties.domains.ThirdPartyDomain;

public final class ThirdPartyDomainMock {

    private ThirdPartyDomainMock() {}

    public static ThirdPartyDomain getThirdPartyDomain() {
        return ThirdPartyDomain.builder()
            .identificationTypeId(1)
            .identificationNumber("72354678")
            .firstName("Thomas")
            .lastName("Anderson")
            .email("thomas@matrix.com")
            .build();
    }
}
