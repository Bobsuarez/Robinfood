package com.robinfood.customersbc.thirdparties.repositories;

import com.robinfood.customersbc.thirdparties.models.ThirdPartyModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ThirdPartyRepository extends R2dbcRepository<ThirdPartyModel, Long> {

    Mono<ThirdPartyModel> findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
        Long customerId, Integer identificationTypeId,
        String firstName, String lastName,
        String email, String identificationNumber
    );

    Flux<ThirdPartyModel> findAllByCustomerId(Long customerId);
}
