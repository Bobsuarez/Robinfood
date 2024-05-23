package com.robinfood.app.usecases.getresponseservicebyorder;

import com.robinfood.core.dtos.response.orderhistory.ResponseServiceDTO;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.core.entities.ServiceEntity;
import com.robinfood.repository.service.IServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Test of GetResponseServiceByOrderUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetResponseServiceByOrderUseCaseTest {

    @Mock
    private IServiceRepository serviceRepository;

    @InjectMocks
    private GetResponseServiceByOrderUseCase useCase;

    @Test
    void testGetResponseServiceByOrderWithResultShouldBeOk() {

        when(serviceRepository.findById(123L)).thenReturn(
            Optional.of(getServiceEntity())
        );

        Optional<ResponseServiceDTO> responseOptional = useCase.invoke(getOrderServiceEntity());
        assertTrue(responseOptional.isPresent());

        ResponseServiceDTO response = responseOptional.get();
        assertThat(response.getId(), is(equalTo(123L)));
        assertThat(response.getName(), is(equalTo("service")));
        assertThat(response.getSubtotal(), is(equalTo(1000.0)));
        assertThat(response.getTax(), is(equalTo(0.0)));
        assertThat(response.getTotal(), is(equalTo(1000.0)));
    }

    @Test
    void testGetResponseServiceByOrderWithoutResultShouldBeError() {
        when(serviceRepository.findById(123L)).thenReturn(
            Optional.empty()
        );

        Optional<ResponseServiceDTO> responseOptional = useCase.invoke(getOrderServiceEntity());
        assertFalse(responseOptional.isPresent());
    }

    private OrderServicesEntity getOrderServiceEntity() {
        return new OrderServicesEntity(
            LocalDateTime.now(),
            0.0,
            1L,
            12L,
            1000.0,
            123L,
            0.0,
            0.0,
            1000.0
        );
    }

    private ServiceEntity getServiceEntity() {
        return new ServiceEntity(
            1L,
            LocalDateTime.now(),
            123L,
            0.0,
            "service",
            1000.0,
            0.0,
            1L,
            1L,
            LocalDateTime.now()
        );
    }

}
