package com.robinfood.customersbc.thirdparties.services.impl;

import com.robinfood.customersbc.thirdparties.domains.ThirdPartyDomain;
import com.robinfood.customersbc.thirdparties.exceptions.EntityNotFoundException;
import com.robinfood.customersbc.thirdparties.repositories.CustomerRepository;
import com.robinfood.customersbc.thirdparties.repositories.ThirdPartyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.robinfood.customersbc.thirdparties.mocks.models.CustomerModelMock.getCustomerModel;
import static com.robinfood.customersbc.thirdparties.mocks.models.ThirdPartyModelMock.getThirdPartyModel;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ThirdPartyServiceImplTest {
    @InjectMocks
    private ThirdPartyServiceImpl thirdPartyService;

    @Mock
    private ThirdPartyRepository thirdPartyRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void test_GetThirdPartiesByExternalId_ShouldBe_Ok_When_DataIsValid() {
        when(customerRepository.findByExternalId(anyLong())).thenReturn(Mono.just(getCustomerModel()));

        when(thirdPartyRepository.findAllByCustomerId(anyLong()))
            .thenReturn(Flux.fromIterable(List.of(getThirdPartyModel())));

        Mono<List<ThirdPartyDomain>> resultMono = thirdPartyService.getThirdPartiesByExternalId(1L);

        StepVerifier.create(resultMono)
            .expectNextMatches((List<ThirdPartyDomain> thirdPartyDomainList) -> !thirdPartyDomainList.isEmpty())
            .verifyComplete();

        verify(customerRepository, times(1)).findByExternalId(anyLong());

        verify(thirdPartyRepository, times(1)).findAllByCustomerId(anyLong());
    }

    @Test
    void test_GetThirdPartiesByExternalId_ShouldBe_Ok_When_CustomerNotFound() {
        when(customerRepository.findByExternalId(anyLong())).thenReturn(Mono.empty());

        Mono<List<ThirdPartyDomain>> resultMono = thirdPartyService.getThirdPartiesByExternalId(1L);

        StepVerifier.create(resultMono)
            .verifyError(EntityNotFoundException.class);

        verify(customerRepository, times(1)).findByExternalId(anyLong());

        verify(thirdPartyRepository, never()).findAllByCustomerId(anyLong());
    }

    @Test
    void test_GetThirdPartiesByExternalId_ShouldBe_Ok_When_ThirdPartyNotFound() {
        when(customerRepository.findByExternalId(anyLong())).thenReturn(Mono.just(getCustomerModel()));

        when(thirdPartyRepository.findAllByCustomerId(anyLong())).thenReturn(Flux.empty());

        Mono<List<ThirdPartyDomain>> resultMono = thirdPartyService.getThirdPartiesByExternalId(1L);

        StepVerifier.create(resultMono)
            .verifyError(EntityNotFoundException.class);

        verify(customerRepository, times(1)).findByExternalId(anyLong());

        verify(thirdPartyRepository, times(1)).findAllByCustomerId(anyLong());
    }
}