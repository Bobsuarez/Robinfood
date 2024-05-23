package com.robinfood.app.usecases.getstatusbyid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.repository.status.IStatusRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetStatusByIdUseCaseTest {

    @Mock
    private IStatusRepository mockStatusRepository;

    @InjectMocks
    private GetStatusByIdUseCase getStatusByIdUseCase;

    private final StatusEntity statusEntity = new StatusEntity(
        LocalDateTime.now(),
            "st-001",
        1L,
        "Pedido",
            BigDecimal.valueOf(1),
        LocalDateTime.now(),
            1L
    );

    private final OrderStatusDTO orderStatusDTO = new OrderStatusDTO(
        1L,
        "Pedido"
    );

    @Test
    void test_Get_Status_By_Id() {
        Mockito.when(mockStatusRepository.findById(1L))
            .thenReturn(Optional.of(statusEntity));

        final OrderStatusDTO resultOrderStatusDTO = getStatusByIdUseCase.invoke(1L);

        assertEquals(orderStatusDTO, resultOrderStatusDTO);
    }
}
