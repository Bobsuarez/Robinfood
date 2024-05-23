package com.robinfood.customersbc.thirdparties.services.impl;

import com.robinfood.customersbc.thirdparties.annotations.BasicLog;
import com.robinfood.customersbc.thirdparties.domains.ThirdPartyDomain;
import com.robinfood.customersbc.thirdparties.exceptions.EntityNotFoundException;
import com.robinfood.customersbc.thirdparties.models.CustomerModel;
import com.robinfood.customersbc.thirdparties.models.ThirdPartyModel;
import com.robinfood.customersbc.thirdparties.repositories.CustomerRepository;
import com.robinfood.customersbc.thirdparties.repositories.ThirdPartyRepository;
import com.robinfood.customersbc.thirdparties.services.ThirdPartyService;
import com.robinfood.customersbc.thirdparties.mappers.utils.ModelMapperUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.NOT_FOUND_TYPE;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    private final ThirdPartyRepository thirdPartyRepository;
    private final CustomerRepository customerRepository;

    public ThirdPartyServiceImpl(
        ThirdPartyRepository thirdPartyRepository, CustomerRepository customerRepository
    ) {
        this.thirdPartyRepository = thirdPartyRepository;
        this.customerRepository = customerRepository;
    }

    @BasicLog
    @Override
    public Mono<List<ThirdPartyDomain>> getThirdPartiesByExternalId(Long externalId) {
        return customerRepository.findByExternalId(externalId)
            .switchIfEmpty(Mono.defer(() -> Mono.error(
                getEntityNotFoundException(String.format(
                    "Entity %s with external id %s not found", CustomerModel.class.getSimpleName(), externalId
                ))
            )))
            .flatMapMany((CustomerModel customerModel) ->
                thirdPartyRepository.findAllByCustomerId(customerModel.getId())
                    .switchIfEmpty(Mono.defer(() -> Mono.error(
                        getEntityNotFoundException(String.format(
                            "Entity %s with customer id %s not found",
                            ThirdPartyModel.class.getSimpleName(), customerModel.getId()
                        ))
                    )))
                    .map((ThirdPartyModel thirdPartyModel) ->
                        ModelMapperUtils.getInstance().map(thirdPartyModel, ThirdPartyDomain.class)
                    )
            ).collectList();
    }

    private static EntityNotFoundException getEntityNotFoundException(String message) {
        return new EntityNotFoundException(NOT_FOUND_TYPE, message);
    }
}
