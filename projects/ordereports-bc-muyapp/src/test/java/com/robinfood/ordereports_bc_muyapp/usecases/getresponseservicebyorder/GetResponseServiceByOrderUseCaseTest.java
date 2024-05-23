package com.robinfood.ordereports_bc_muyapp.usecases.getresponseservicebyorder;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderServicesEntityMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.ServicesEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseServiceDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderServicesEntity;
import com.robinfood.ordereports_bc_muyapp.models.entities.ServiceEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.service.IServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetResponseServiceByOrderUseCaseTest {

    @Mock
    private IServiceRepository serviceRepository;

    @Mock
    private OrderDetailOrderMapper orderDetailOrderMapper;

    @InjectMocks
    private GetResponseServiceByOrderUseCase getResponseServiceByOrderUseCase;

    @Test
    void test_ValidatedServices_When_IsPresent_Should_OptionalServicesDTO_Return() {

        when(serviceRepository.findById(anyLong())).thenReturn(
                Optional.of(ServicesEntityMock.getDataDefault())
        );

        when(orderDetailOrderMapper.mapOrderServiceToResponseDTO(
                any(OrderServicesEntity.class), any(ServiceEntity.class)))
                .thenReturn(ResponseServiceDTO.builder()
                                    .id(1L)
                                    .build());

        Optional<ResponseServiceDTO> responseOptional =
                getResponseServiceByOrderUseCase.invoke(OrderServicesEntityMock.getDataDefault());

        assertTrue(responseOptional.isPresent());

        ResponseServiceDTO response = responseOptional.get();
        assertThat(response.getId(), is(equalTo(1L)));
    }
}