package com.robinfood.app.usecases.getstatusbyorderids;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.repository.status.IStatusRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetStatusByOrderIdsUseCaseTest {

    @Mock
    private IStatusRepository statusRepository;

    @InjectMocks
    private GetStatusByOrderIds getStatusByOrderIds;

    private final List<Long> statusIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L));

    private final List<StatusEntity> statusEntities = new ArrayList<>(Arrays.asList(
        new StatusEntity(
            LocalDateTime.now(),
                "st-001",
            1L,
            "On its way",
                BigDecimal.valueOf(1),
            LocalDateTime.now(),

                1L
        ),
        new StatusEntity(
            LocalDateTime.now(),
                "st-001",
            2L,
            "Canceled",
                BigDecimal.valueOf(1),
            LocalDateTime.now(),
                1L
        ),
        new StatusEntity(
            LocalDateTime.now(),
                "st-001",
            3L,
            "Ready to deliver",
                BigDecimal.valueOf(1),
            LocalDateTime.now(),
                1L
        )
    ));

    private final List<OrderStatusDTO> orderStatusDTOS = new ArrayList<>(Arrays.asList(
        new OrderStatusDTO(
            1L,
            "On its way"
        ),
        new OrderStatusDTO(
            2L,
            "Canceled"
        ),
        new OrderStatusDTO(
            3L,
            "Ready to deliver"
        )
    ));

    @Test
    void test_GetStatus_Returns_Correctly() {
        when(statusRepository.findAllByIdIn(statusIds)).thenReturn(statusEntities);

        final List<OrderStatusDTO> result = getStatusByOrderIds.invoke(statusIds);

        assertEquals(orderStatusDTOS, result);
    }
}
