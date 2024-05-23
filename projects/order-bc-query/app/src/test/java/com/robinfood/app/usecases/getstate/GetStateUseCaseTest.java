package com.robinfood.app.usecases.getstate;

import com.robinfood.app.mappers.OrderStateMappers;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.status.IStatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetStateUseCaseTest {

    @Mock
    private IStatusRepository mockStatusRepository;

    @Mock
    private OrderStateMappers orderStateMappers;

    @InjectMocks
    private GetStateOrderWithCodeUseCase getStateUseCase;

    private List<StatusEntity> listStatus = new ArrayList<>();

    private final StatusEntity statusEntity = new StatusEntity(
            LocalDateTime.now(),
            "st-001",
            1L,
            "Pedido",
            BigDecimal.valueOf(1),
            LocalDateTime.now(),
            1L
    );

    private final StatusEntity subStatusEntity = new StatusEntity(
            LocalDateTime.now(),
            "st-0011",
            1L,
            "Validando",
            BigDecimal.valueOf(1.2),
            LocalDateTime.now(),
            1L
    );


    private final StatusEntity subStatusEntity_result = new StatusEntity(
            LocalDateTime.now(),
            "st-001",
            4L,
            "Pedido",
            BigDecimal.valueOf(1),
            LocalDateTime.now(),
            1L
    );

    private final OrderEntity orderEntity = new OrderEntity();
    private final OrderStateDTO orderStateDTO = new OrderStateDTO();
    private final OrderStateDTO subStateOrder = new OrderStateDTO();

    @Test
    void test_Get_State_by_code() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        when(mockStatusRepository.findByCode("a"))
                .thenReturn(Optional.of(statusEntity));
        when(orderStateMappers.buildOrderState(statusEntity)).thenReturn(orderStateDTO);

        final OrderStateDTO resultOrderStateDTO = getStateUseCase.invoke("a");

        assertEquals(orderStateDTO, resultOrderStateDTO);
    }
    @Test
    void test_Get_State_by_code_with_subState() {

        orderEntity.setStatusId(4L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        subStateOrder.setId(4L);
        subStateOrder.setName("Validando");
        orderStateDTO.setSubState(subStateOrder);

        when(mockStatusRepository.findByCode("a"))
                .thenReturn(Optional.of(subStatusEntity));

        when(mockStatusRepository.findById(1L))
                .thenReturn(Optional.of(subStatusEntity_result));

        when(orderStateMappers.buildOrderState(subStatusEntity,subStatusEntity_result)).thenReturn(orderStateDTO);

        final OrderStateDTO resultOrderStateDTO = getStateUseCase.invoke("a");

        assertEquals(orderStateDTO, resultOrderStateDTO);
    }

    @Test
    void test_Get_State_Not_Found_Status_Code() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        orderStateDTO.setSubState(subStateOrder);

        when(mockStatusRepository.findByCode("a"))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(GenericOrderBcException.class, () -> {
            getStateUseCase.invoke("a");
        });

        String expectedMessage = "status is no created";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_Get_State_by_id_with_subState_Not_found_Code() {

        orderEntity.setStatusId(4L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");

        subStateOrder.setCode("st-0011");
        subStateOrder.setId(4L);
        subStateOrder.setName("Validando");
        orderStateDTO.setSubState(subStateOrder);

        when(mockStatusRepository.findByCode("a"))
                .thenReturn(Optional.of(subStatusEntity));
        when(mockStatusRepository.findById(1L))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(GenericOrderBcException.class, () -> {
            getStateUseCase.invoke("a");
        });

        String expectedMessage = "status is no created";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
