package com.robinfood.app.usecases.getstatusbylistid;

import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.repository.status.IStatusRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetStatusByListIdUseCaseTest {

    @Mock
    private IStatusRepository mockStatusRepository;

    @InjectMocks
    private GetStatusByListIdUseCase getStatusByListIdUseCase;

    private final List<Long> listStatus = Arrays.asList(1L);

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
        Mockito.when(mockStatusRepository.findAllByIdIn(listStatus))
                .thenReturn(Arrays.asList(statusEntity));

        final List<OrderStatusDTO> resultOrderStatusDTO = getStatusByListIdUseCase.invoke(listStatus);

        assertEquals(Arrays.asList(orderStatusDTO), resultOrderStatusDTO);
    }

    @Test
    void test_Get_Status_Empty_By_Id() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            Mockito.when(mockStatusRepository.findAllByIdIn(listStatus))
                    .thenReturn(Arrays.asList());

            getStatusByListIdUseCase.invoke(listStatus);
        });
    }
}
