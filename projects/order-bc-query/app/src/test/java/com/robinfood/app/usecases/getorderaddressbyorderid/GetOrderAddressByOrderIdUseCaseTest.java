package com.robinfood.app.usecases.getorderaddressbyorderid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.entity.OrderAddressEntityMock;
import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.entities.OrderAddressEntity;
import com.robinfood.repository.orderaddress.IOrderAddressRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrderAddressByOrderIdUseCaseTest {

    @Mock
    private IOrderAddressRepository orderAddressRepository;

    @InjectMocks
    private GetOrderAddressByOrderIdUseCase useCase;

    @Test
    void get_address_by_order_id() {
        // Arrange
        Long orderId = 1L;

        OrderAddressEntity orderAddressEntity = OrderAddressEntityMock.build();

        when(orderAddressRepository.findById(anyLong()))
            .thenReturn(Optional.of(orderAddressEntity));

        // Act
        OrderAddressDTO orderAddressDTO = useCase.invoke(orderId);

        // Assert
        assertEquals(orderAddressEntity.getAddress(), orderAddressDTO.getAddress());
        assertEquals(orderAddressEntity.getOrderId(), orderAddressDTO.getOrderId());
        assertEquals(orderAddressEntity.getCityId(), orderAddressDTO.getCityId());
    }

    @Test
    void get_address_by_order_id_not_found() {
        // Arrange
        Long orderId = 1L;

        when(orderAddressRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        OrderAddressDTO orderAddressDTO = useCase.invoke(orderId);

        // Assert
        assertNull(orderAddressDTO);
    }

}
