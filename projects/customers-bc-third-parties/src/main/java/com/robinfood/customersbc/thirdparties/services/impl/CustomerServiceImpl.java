package com.robinfood.customersbc.thirdparties.services.impl;

import com.robinfood.customersbc.thirdparties.annotations.BasicLog;
import com.robinfood.customersbc.thirdparties.domains.CreateCustomerDomain;
import com.robinfood.customersbc.thirdparties.domains.CustomerDomain;
import com.robinfood.customersbc.thirdparties.domains.ThirdPartyDomain;
import com.robinfood.customersbc.thirdparties.enums.StatusEnum;
import com.robinfood.customersbc.thirdparties.models.CustomerModel;
import com.robinfood.customersbc.thirdparties.models.ThirdPartyModel;
import com.robinfood.customersbc.thirdparties.repositories.CustomerRepository;
import com.robinfood.customersbc.thirdparties.repositories.ThirdPartyRepository;
import com.robinfood.customersbc.thirdparties.services.CustomerService;
import com.robinfood.customersbc.thirdparties.mappers.utils.ModelMapperUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Locale;

import static com.robinfood.customersbc.thirdparties.utils.SanitizeUtils.sanitizeString;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ThirdPartyRepository thirdPartyRepository;

    public CustomerServiceImpl(
        CustomerRepository customerRepository, ThirdPartyRepository thirdPartyRepository
    ) {
        this.customerRepository = customerRepository;
        this.thirdPartyRepository = thirdPartyRepository;
    }

    @BasicLog
    @Override
    public Mono<CustomerDomain> createCustomer(CreateCustomerDomain createCustomerDomain) {
        CustomerDomain customerDomain = sanitizeCustomerDomain(createCustomerDomain.getCustomer());
        ThirdPartyDomain thirdPartyDomain = customerDomain.getThirdParty();

        return processCustomer(customerDomain)
            .flatMap((CustomerDomain newCustomerDomain) ->
                processThirdParty(newCustomerDomain.getId(), thirdPartyDomain)
                    .map((ThirdPartyDomain newThirdPartyDomain) -> {
                        newCustomerDomain.setThirdParty(newThirdPartyDomain);
                        return newCustomerDomain;
                    })
            );
    }

    private static CustomerDomain sanitizeCustomerDomain(CustomerDomain customerDomain) {
        return customerDomain.toBuilder()
            .firstName(customerDomain.getFirstName().toLowerCase(Locale.getDefault()).trim())
            .lastName(customerDomain.getLastName().toLowerCase(Locale.getDefault()).trim())
            .email(sanitizeString(customerDomain.getEmail()))
            .thirdParty(sanitizeThirdPartyDomain(customerDomain.getThirdParty()))
            .build();
    }

    private static ThirdPartyDomain sanitizeThirdPartyDomain(ThirdPartyDomain thirdPartyDomain) {
        return thirdPartyDomain.toBuilder()
            .firstName(thirdPartyDomain.getFirstName().toLowerCase(Locale.getDefault()).trim())
            .lastName(sanitizeString(thirdPartyDomain.getLastName()))
            .identificationNumber(thirdPartyDomain.getIdentificationNumber().toLowerCase(Locale.getDefault()).trim())
            .email(thirdPartyDomain.getEmail().toLowerCase(Locale.getDefault()).trim())
            .build();
    }

    private Mono<CustomerDomain> processCustomer(CustomerDomain customerDomain) {
        return findCustomer(customerDomain)
            .switchIfEmpty(Mono.defer(() -> saveCustomer(customerDomain)))
            .map((CustomerModel customerModel) -> ModelMapperUtils.getInstance().map(
                customerModel, CustomerDomain.class
            ));
    }

    private Mono<CustomerModel> findCustomer(CustomerDomain customerDomain) {
        return customerRepository.findByExternalId(customerDomain.getExternalId());
    }

    private Mono<CustomerModel> saveCustomer(CustomerDomain customerDomain) {
        CustomerModel customerModel = ModelMapperUtils.getInstance().map(customerDomain, CustomerModel.class);
        customerModel.setStatus(StatusEnum.ACTIVE.getStatus());
        return customerRepository.save(customerModel);
    }

    private Mono<ThirdPartyDomain> processThirdParty(Long customerId, ThirdPartyDomain thirdPartyDomain) {
        return findThirdParty(customerId, thirdPartyDomain)
            .switchIfEmpty(Mono.defer(() -> saveThirdParty(customerId, thirdPartyDomain)))
            .map((ThirdPartyModel thirdPartyModel) -> ModelMapperUtils.getInstance().map(
                thirdPartyModel, ThirdPartyDomain.class
            ));
    }

    private Mono<ThirdPartyModel> findThirdParty(Long customerId, ThirdPartyDomain thirdPartyDomain) {
        return thirdPartyRepository
            .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                customerId,
                thirdPartyDomain.getIdentificationTypeId(),
                thirdPartyDomain.getFirstName(),
                thirdPartyDomain.getLastName(),
                thirdPartyDomain.getEmail(),
                thirdPartyDomain.getIdentificationNumber()
            );
    }

    private Mono<ThirdPartyModel> saveThirdParty(Long customerId, ThirdPartyDomain thirdPartyDomain) {
        ThirdPartyModel thirdPartyModel = ModelMapperUtils.getInstance().map(thirdPartyDomain, ThirdPartyModel.class);
        thirdPartyModel.setCustomerId(customerId);
        thirdPartyModel.setStatus(StatusEnum.ACTIVE.getStatus());
        return thirdPartyRepository.save(thirdPartyModel);
    }
}
