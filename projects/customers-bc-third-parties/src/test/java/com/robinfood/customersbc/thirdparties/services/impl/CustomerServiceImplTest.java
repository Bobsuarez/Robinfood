package com.robinfood.customersbc.thirdparties.services.impl;

import com.robinfood.customersbc.thirdparties.domains.CustomerDomain;
import com.robinfood.customersbc.thirdparties.models.CustomerModel;
import com.robinfood.customersbc.thirdparties.models.ThirdPartyModel;
import com.robinfood.customersbc.thirdparties.repositories.CustomerRepository;
import com.robinfood.customersbc.thirdparties.repositories.ThirdPartyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static com.robinfood.customersbc.thirdparties.mocks.domains.CreateCustomerDomainMock.getCreateCustomerDomain;
import static com.robinfood.customersbc.thirdparties.mocks.domains.CreateCustomerDomainMock.getCreateCustomerDomainWithEmailNull;
import static com.robinfood.customersbc.thirdparties.mocks.domains.CustomerDomainMock.getCustomerDomain;
import static com.robinfood.customersbc.thirdparties.mocks.models.CustomerModelMock.getCustomerModel;
import static com.robinfood.customersbc.thirdparties.mocks.models.ThirdPartyModelMock.getThirdPartyModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ThirdPartyRepository thirdPartyRepository;

    @Test
    void test_CreateCustomer_ShouldBe_Ok_When_CustomerAndThirdPartyNotExists() {
        when(customerRepository.findByExternalId(anyLong())).thenReturn(Mono.empty());

        when(customerRepository.save(any(CustomerModel.class))).thenReturn(Mono.just(getCustomerModel()));

        when(
            thirdPartyRepository
                .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                    anyLong(), anyInt(), anyString(), anyString(), anyString(), anyString()
                )
        ).thenReturn(Mono.empty());

        when(thirdPartyRepository.save(any(ThirdPartyModel.class))).thenReturn(Mono.just(getThirdPartyModel()));

        CustomerDomain customerDomain = getCustomerDomain();

        Mono<CustomerDomain> resultMono = customerService.createCustomer(getCreateCustomerDomain());

        StepVerifier.create(resultMono)
            .expectNextMatches(validatedCustomer ->
                Objects.equals(validatedCustomer.getExternalId(), customerDomain.getExternalId()))
            .verifyComplete();

        verify(customerRepository, times(1)).findByExternalId(anyLong());

        verify(customerRepository, times(1)).save(any(CustomerModel.class));

        verify(thirdPartyRepository, times(1))
            .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                anyLong(), anyInt(), anyString(), anyString(), anyString(), anyString()
            );

        verify(thirdPartyRepository, times(1)).save(any(ThirdPartyModel.class));
    }

    @Test
    void test_CreateCustomer_ShouldBe_Ok_When_CustomerExistsAndThirdPartyNotExists() {
        when(customerRepository.findByExternalId(anyLong())).thenReturn(Mono.just(getCustomerModel()));

        when(
            thirdPartyRepository
                .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                    anyLong(), anyInt(), anyString(), anyString(), anyString(), anyString()
                )
        ).thenReturn(Mono.empty());

        when(thirdPartyRepository.save(any(ThirdPartyModel.class))).thenReturn(Mono.just(getThirdPartyModel()));

        CustomerDomain customerDomain = getCustomerDomain();

        Mono<CustomerDomain> resultMono = customerService.createCustomer(getCreateCustomerDomain());

        StepVerifier.create(resultMono)
            .expectNextMatches(validatedCustomer ->
                Objects.equals(validatedCustomer.getExternalId(), customerDomain.getExternalId()))
            .verifyComplete();

        verify(customerRepository, times(1)).findByExternalId(anyLong());

        verify(thirdPartyRepository, times(1))
            .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                anyLong(), anyInt(), anyString(), anyString(), anyString(), anyString()
            );

        verify(thirdPartyRepository, times(1)).save(any(ThirdPartyModel.class));
    }

    @Test
    void test_CreateCustomer_ShouldBe_Ok_When_CustomerAndThirdPartyExists() {
        when(customerRepository.findByExternalId(anyLong())).thenReturn(Mono.just(getCustomerModel()));

        when(
            thirdPartyRepository
                .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                    anyLong(), anyInt(), anyString(), anyString(), anyString(), anyString()
                )
        ).thenReturn(Mono.just(getThirdPartyModel()));

        CustomerDomain customerDomain = getCustomerDomain();

        Mono<CustomerDomain> resultMono = customerService.createCustomer(getCreateCustomerDomain());

        StepVerifier.create(resultMono)
            .expectNextMatches(validatedCustomer ->
                Objects.equals(validatedCustomer.getExternalId(), customerDomain.getExternalId()))
            .verifyComplete();

        verify(customerRepository, times(1)).findByExternalId(anyLong());

        verify(thirdPartyRepository, times(1))
            .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                anyLong(), anyInt(), anyString(), anyString(), anyString(), anyString()
            );
    }

    @Test
    void test_CreateCustomer_ShouldBe_Ok_When_CustomerEmailIsNull() {
        when(customerRepository.findByExternalId(anyLong())).thenReturn(Mono.just(getCustomerModel()));

        when(
            thirdPartyRepository
                .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                    anyLong(), anyInt(), anyString(), anyString(), anyString(), anyString()
                )
        ).thenReturn(Mono.just(getThirdPartyModel()));

        CustomerDomain customerDomain = getCustomerDomain();

        Mono<CustomerDomain> resultMono = customerService.createCustomer(getCreateCustomerDomainWithEmailNull());

        StepVerifier.create(resultMono)
            .expectNextMatches(validatedCustomer ->
                Objects.equals(validatedCustomer.getExternalId(), customerDomain.getExternalId()))
            .verifyComplete();

        verify(customerRepository, times(1)).findByExternalId(anyLong());

        verify(thirdPartyRepository, times(1))
            .findByCustomerIdAndIdentificationTypeIdAndFirstNameAndLastNameAndEmailAndIdentificationNumber(
                anyLong(), anyInt(), anyString(), anyString(), anyString(), anyString()
            );
    }
}