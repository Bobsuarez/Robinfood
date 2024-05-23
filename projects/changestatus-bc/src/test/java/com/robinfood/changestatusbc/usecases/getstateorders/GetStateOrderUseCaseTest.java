package com.robinfood.changestatusbc.usecases.getstateorders;

import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.entities.OrderEntity;
import com.robinfood.changestatusbc.entities.OrderIntegrationEntity;
import com.robinfood.changestatusbc.entities.StatusEntity;
import com.robinfood.changestatusbc.entities.TransactionEntity;
import com.robinfood.changestatusbc.mappers.OrderStateMappers;
import com.robinfood.changestatusbc.repositories.orderintegration.IOrderIntegrationRepository;
import com.robinfood.changestatusbc.repositories.orders.IOrdersRepository;
import com.robinfood.changestatusbc.repositories.status.IStatusRepository;
import com.robinfood.changestatusbc.repositories.transaction.ITransactionCRUDRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStateOrderUseCaseTest {
    @Mock
    private IStatusRepository mockStatusRepository;

    @InjectMocks
    private GetStateOrderUseCase getStateOrderUseCase;

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @Mock
    private IOrderIntegrationRepository mockOrderIntegrationRepository;

    @Mock
    private ITransactionCRUDRepository transactionCRUDRepository;

    @Mock
    private OrderStateMappers orderStateMappers;

    private final StatusEntity statusEntity = new StatusEntity(
            LocalDateTime.now(),
            "st-001",
            1L,
            "Pedido",
            BigDecimal.valueOf(1.2),
            LocalDateTime.now(),
            1L
    );

    private final StatusEntity statusEntities = new StatusEntity(
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
            4L,
            "Validando",
            BigDecimal.valueOf(1.2),
            LocalDateTime.now(),
            1L
    );


    private final StatusEntity subStatusEntity_result = new StatusEntity(
            LocalDateTime.now(),
            "st-001",
            1L,
            "Pedido",
            BigDecimal.valueOf(1),
            LocalDateTime.now(),
            1L
    );

    private final OrderEntity orderEntity = new OrderEntity();

    private final OrderIntegrationEntity orderIntegrationEntity = new OrderIntegrationEntity();

    private final OrderStateDTO orderStateDTO = new OrderStateDTO();

    private final OrderStateDTO subStateOrder = new OrderStateDTO();

    @Test
    void test_Get_State_by_Parent_id() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        orderEntity.setTransactionId(1L);

        when(mockOrdersRepository.findById(1L))
                .thenReturn(Optional.of(orderEntity));
        when(mockStatusRepository.findById(1L))
                .thenReturn(Optional.of(statusEntities));

        when(orderStateMappers.buildOrderState(statusEntities)).thenReturn(orderStateDTO);

        when(transactionCRUDRepository.findById(anyLong())).thenReturn(Optional.of(new TransactionEntity()));

        final OrderStateDTO resultOrderStateDTO = getStateOrderUseCase.invoke(1L);

        assertEquals(orderStateDTO, resultOrderStateDTO);

    }

    @Test
    void test_Get_State_by_id() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        orderEntity.setTransactionId(1L);

        when(mockOrdersRepository.findById(1L))
                .thenReturn(Optional.of(orderEntity));
        when(mockStatusRepository.findById(1L))
                .thenReturn(Optional.of(statusEntity));

        when(orderStateMappers.buildOrderStateWithParent(statusEntity, statusEntity)).thenReturn(orderStateDTO);

        when(transactionCRUDRepository.findById(anyLong())).thenReturn(Optional.of(new TransactionEntity()));

        final OrderStateDTO resultOrderStateDTO = getStateOrderUseCase.invoke(1L);

        assertEquals(orderStateDTO, resultOrderStateDTO);

    }

    @Test
    void test_GetEntity_When_Uid_is_Identifier_Should_Return_StateDTO() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        orderEntity.setTransactionId(1L);

        when(mockOrdersRepository.findByUuid("a"))
                .thenReturn(Optional.of(orderEntity));
        when(mockStatusRepository.findById(1L))
                .thenReturn(Optional.of(statusEntity));

        when(orderStateMappers.buildOrderStateWithParent(statusEntity, statusEntity)).thenReturn(orderStateDTO);

        when(transactionCRUDRepository.findById(anyLong())).thenReturn(Optional.of(new TransactionEntity()));

        final OrderStateDTO resultOrderStateDTO = getStateOrderUseCase.invokeUuid("a");

        assertNotNull(resultOrderStateDTO);

    }

    @Test
    void test_GetEntity_When_IntegrationId_is_Identifier_Should_Return_StateDTO() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        orderIntegrationEntity.setOrderId(1L);
        orderEntity.setTransactionId(1L);

        when(mockOrderIntegrationRepository.findByIntegrationId("a"))
                .thenReturn(Optional.of(orderIntegrationEntity));

        when(mockOrdersRepository.findById(orderIntegrationEntity.getOrderId()))
                .thenReturn(Optional.of(orderEntity));
        when(mockStatusRepository.findById(1L))
                .thenReturn(Optional.of(statusEntity));

        when(transactionCRUDRepository.findById(anyLong())).thenReturn(Optional.of(new TransactionEntity()));

        when(orderStateMappers.buildOrderStateWithParent(statusEntity, statusEntity)).thenReturn(orderStateDTO);

        final OrderStateDTO resultOrderStateDTO = getStateOrderUseCase.invokeDeliveryIntegrationId("a");

        assertEquals(orderStateDTO, resultOrderStateDTO);

    }

    @Test
    void test_Get_State_by_id_with_subState() {

        orderEntity.setStatusId(4L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        subStateOrder.setId(4L);
        subStateOrder.setName("Validando");
        orderStateDTO.setSubState(subStateOrder);
        orderEntity.setTransactionId(1L);

        when(mockOrdersRepository.findById(1L))
                .thenReturn(Optional.of(orderEntity));
        when(mockStatusRepository.findById(4L))
                .thenReturn(Optional.of(subStatusEntity));
        when(mockStatusRepository.findById(1L))
                .thenReturn(Optional.of(subStatusEntity_result));

        when(orderStateMappers.buildOrderStateWithParent(subStatusEntity, subStatusEntity_result)).thenReturn(orderStateDTO);

        when(transactionCRUDRepository.findById(anyLong())).thenReturn(Optional.of(new TransactionEntity()));

        final OrderStateDTO resultOrderStateDTO = getStateOrderUseCase.invoke(1L);

        assertEquals(orderStateDTO, resultOrderStateDTO);
    }

    @Test
    void test_Get_State_Not_Found() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        orderStateDTO.setSubState(subStateOrder);

        when(mockOrdersRepository.findById(1L))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            getStateOrderUseCase.invoke(1L);
        });

        String expectedMessage = "Order with id [1] is not found";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_Get_State_Not_Found_Status() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        orderStateDTO.setSubState(subStateOrder);
        orderEntity.setTransactionId(1L);

        when(mockOrdersRepository.findById(1L))
                .thenReturn(Optional.of(orderEntity));
        when(mockStatusRepository.findById(1L))
                .thenReturn(Optional.empty());

        when(transactionCRUDRepository.findById(anyLong())).thenReturn(Optional.of(new TransactionEntity()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            getStateOrderUseCase.invoke(1L);
        });

        String expectedMessage = "Status is not created";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_Get_State_by_id_with_subState_Not_found() {

        orderEntity.setStatusId(4L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        subStateOrder.setId(4L);
        subStateOrder.setName("Validando");
        orderStateDTO.setSubState(subStateOrder);
        orderEntity.setTransactionId(1L);

        when(mockOrdersRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
        when(mockStatusRepository.findById(4L)).thenReturn(Optional.of(subStatusEntity));
        when(mockStatusRepository.findById(1L)).thenReturn(Optional.empty());
        when(transactionCRUDRepository.findById(anyLong())).thenReturn(Optional.of(new TransactionEntity()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            getStateOrderUseCase.invoke(1L);
        });

        String expectedMessage = "sub state not created";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_GetStateDTOS_Uuid_When_Uuid_is_Identifier_Should_Return_Exception_NotFound() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        orderStateDTO.setSubState(subStateOrder);

        when(mockOrdersRepository.findByUuid("aa"))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            getStateOrderUseCase.invokeUuid("aa");
        });

        String expectedMessage = "Order with uuid [aa] is not found";
        String actualMessage = exception.getReason();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_GetStateDTOS_When_IntegrationId_is_Identifier_Should_Return_Exception_NotFound() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        orderStateDTO.setSubState(subStateOrder);

        when(mockOrderIntegrationRepository.findByIntegrationId("aa"))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            getStateOrderUseCase.invokeDeliveryIntegrationId("aa");
        });

        String expectedMessage = "Integration with id [aa] is not found";
        String actualMessage = exception.getReason();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_GetStateDTOS_When_IntegrationId_is_Identifier_Should_Return_Exception_NotFound_Cause_OrderId() {

        orderEntity.setStatusId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setName("Pedido");
        orderStateDTO.setCode("st-001");
        subStateOrder.setCode("st-0011");
        orderStateDTO.setSubState(subStateOrder);
        orderIntegrationEntity.setOrderId(1L);

        when(mockOrderIntegrationRepository.findByIntegrationId("aa"))
                .thenReturn(Optional.of(orderIntegrationEntity));

        when(mockOrdersRepository.findById(1L))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            getStateOrderUseCase.invokeDeliveryIntegrationId("aa");
        });

        String expectedMessage = "Order with id [1] is not found";
        String actualMessage = exception.getReason();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_GetStateDTOS_When_IntegrationId_is_Identifier_Should_Return_Exception_NotFound_Cause_TransactionId() {

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            getStateOrderUseCase.getOrderStateDTO(orderEntity);
        });

        String expectedMessage = "Transaction with id [null] is not found";
        String actualMessage = exception.getReason();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}