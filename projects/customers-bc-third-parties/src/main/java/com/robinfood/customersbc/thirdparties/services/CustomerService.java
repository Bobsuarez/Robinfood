package com.robinfood.customersbc.thirdparties.services;

import com.robinfood.customersbc.thirdparties.domains.CreateCustomerDomain;
import com.robinfood.customersbc.thirdparties.domains.CustomerDomain;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<CustomerDomain> createCustomer(CreateCustomerDomain createCustomerDomain);
}
