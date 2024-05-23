package com.robinfood.app.usecases.getallstate;

import com.robinfood.core.entities.StatusEntity;
import com.robinfood.repository.status.IStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllStateUseCaseTest {

    @Mock
    private IStatusRepository mockStatusRepository;


    @InjectMocks
    private GetAllStateUseCase getAllStateUseCase;

    private final List<StatusEntity> listStatus = new ArrayList<>();

    private final StatusEntity statusEntity = new StatusEntity(
            LocalDateTime.now(),
            "st-001",
            1L,
            "Pedido",
            BigDecimal.valueOf(1),
            LocalDateTime.now(),
            1L
    );

    @Test
    void test_Get_All_State() {

        listStatus.add(statusEntity);

        when(mockStatusRepository.findAll()).thenReturn(listStatus);

        assertEquals(listStatus, getAllStateUseCase.invoke());
    }
}
