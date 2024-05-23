package com.robinfood.app.usecases.updateordertemplate;

import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateOrderUuidTemplateUseCaseTest {

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @InjectMocks
    private UpdateOrderUuidTemplateUseCase updateOrderUuidTemplateUseCase;

    @Test
    void test_Update_Order_With_Uuid_Template_Happy_Path() throws ResourceNotFoundException {

        // Arrange
        final String uuid = "00896238-b1a6-40c4-9ce8-892";
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUuid(uuid);

        when(mockOrdersRepository.findByUuid(uuid)).thenReturn(Optional.of(orderEntity));

        // Act
        updateOrderUuidTemplateUseCase.invoke(uuid);

        // Assert
        verify(mockOrdersRepository, times(1))
                .findByUuid(uuid);
    }

    @Test
    void test_Update_Order_With_Uuid_Template_Throw_Resource_Not_Found_Exception() throws ResourceNotFoundException {

        // Arrange
        final String uuid = UUID.randomUUID().toString();
        final String messageException = "Order not found with the Uuid: " + uuid;
        when(mockOrdersRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        // Act
        ResourceNotFoundException response = assertThrows(ResourceNotFoundException.class, () -> {
            updateOrderUuidTemplateUseCase.invoke(uuid);
        });

        //Assert
        assertEquals(messageException, response.getMessage());
    }
}
