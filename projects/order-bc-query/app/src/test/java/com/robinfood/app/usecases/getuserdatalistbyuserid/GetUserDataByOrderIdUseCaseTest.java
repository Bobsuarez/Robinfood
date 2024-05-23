package com.robinfood.app.usecases.getuserdatalistbyuserid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.entity.OrderUserDataEntityMock;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.userdata.IUserDataRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserDataByOrderIdUseCaseTest {

    @Mock
    private IUserDataRepository userDataRepository;

    @InjectMocks
    private GetUserDataByOrderIdUseCase useCase;

    @Test
    void get_user_data_by_order_id() {

        // Arrange
        Long orderId = 1L;

        OrderUserDataEntity orderUserDataEntity = OrderUserDataEntityMock.build();

        when(userDataRepository.findByOrderId(anyLong()))
            .thenReturn(Optional.of(orderUserDataEntity));

        // Act
        UserDataDTO userDataDTO = useCase.invoke(orderId);

        // Assert
        assertEquals(orderUserDataEntity.getUserId(), userDataDTO.getId());
        assertEquals(orderUserDataEntity.getOrderId(), userDataDTO.getOrderId());
        assertEquals(orderUserDataEntity.getEmail(), userDataDTO.getEmail());
    }

    @Test
    void get_user_data_by_order_id_not_found() {

        // Arrange
        Long orderId = 1L;

        when(userDataRepository.findByOrderId(anyLong())).thenReturn(Optional.empty());

        // Assert
        assertThrows(
            GenericOrderBcException.class,
            () -> useCase.invoke(orderId)
        );
    }

}
