package com.robinfood.customersbc.thirdparties.repositories;

import com.robinfood.customersbc.thirdparties.models.CustomerModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends R2dbcRepository<CustomerModel, Long> {

    Mono<CustomerModel> findByExternalId(Long externalId);
}
