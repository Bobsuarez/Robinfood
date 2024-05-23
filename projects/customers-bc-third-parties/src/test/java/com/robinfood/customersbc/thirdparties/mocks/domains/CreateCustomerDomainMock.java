package com.robinfood.customersbc.thirdparties.mocks.domains;

import com.robinfood.customersbc.thirdparties.domains.CreateCustomerDomain;

import static com.robinfood.customersbc.thirdparties.mocks.domains.CustomerDomainMock.getCustomerDomain;
import static com.robinfood.customersbc.thirdparties.mocks.domains.CustomerDomainMock.getCustomerDomainWithEmailNull;

public final class CreateCustomerDomainMock {

    private CreateCustomerDomainMock() {}

    public static CreateCustomerDomain getCreateCustomerDomain() {
        return CreateCustomerDomain.builder()
            .customer(getCustomerDomain())
            .build();
    }

    public static CreateCustomerDomain getCreateCustomerDomainWithEmailNull() {
        return CreateCustomerDomain.builder()
            .customer(getCustomerDomainWithEmailNull())
            .build();
    }
}
