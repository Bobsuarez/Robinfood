package com.robinfood.app.usecases.gettransactionuuid;

import static java.util.Objects.isNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.ExistsTransactionUuidOrderUuidDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.exitstransactionuuidorderuid.IExitsTransactionUuidOrderUuidRepository;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class GetTransactionUuidUseCaseTest {

    @Mock
    private IExitsTransactionUuidOrderUuidRepository exitsTransactionUuidOrderUuidRepository;

    @InjectMocks
    private GetTransactionUuidUseCase getTransactionUuidUseCase;

    @Test
    void test_GetTransactionUuidUseCase_All_Orders_Null() {

        ExistsTransactionUuidOrderUuidDTO existsTransactionUuidOrderUuidDTO = ExistsTransactionUuidOrderUuidDTO.builder()
                .exits(false)
                .message("Don't Exits")
                .build();

        TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTOMultipleOrders;

        when(exitsTransactionUuidOrderUuidRepository.invoke(anyString(), eq(null), anyList())).thenReturn(
                existsTransactionUuidOrderUuidDTO);

        getTransactionUuidUseCase.invoke("token", transactionRequestDTO);

        Assertions.assertNotNull(transactionRequestDTO.getUuid());
        Assertions.assertTrue(transactionRequestDTO.getOrders().stream()
                .noneMatch(orderDTO -> isNull(orderDTO.getUuid())));
    }

    @Test
    void test_GetTransactionUuidUseCase_All_Not_Null_Not_Exits() {

        ExistsTransactionUuidOrderUuidDTO existsTransactionUuidOrderUuidDTO = ExistsTransactionUuidOrderUuidDTO.builder()
                .exits(false)
                .message("Don't Exits")
                .build();

        TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTOMultipleOrders;

        transactionRequestDTO.setUuid(UUID.randomUUID());

        when(exitsTransactionUuidOrderUuidRepository.invoke(anyString(), eq(null), anyList())).thenReturn(
                existsTransactionUuidOrderUuidDTO);

        for (OrderDTO order : transactionRequestDTO.getOrders()
        ) {
            order.setUuid(UUID.randomUUID());
        }

        getTransactionUuidUseCase.invoke("token", transactionRequestDTO);

        Assertions.assertNotNull(transactionRequestDTO.getUuid());
        Assertions.assertTrue(transactionRequestDTO.getOrders().stream()
                .noneMatch(orderDTO -> isNull(orderDTO.getUuid())));
    }

    @Test
    void test_GetTransactionUuidUseCase_Transaction_Not_Null_Not_Exits() {

        ExistsTransactionUuidOrderUuidDTO existsTransactionUuidOrderUuidDTO = ExistsTransactionUuidOrderUuidDTO.builder()
                .exits(false)
                .message("Don't Exits")
                .build();

        TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTOMultipleOrders;

        transactionRequestDTO.setUuid(UUID.randomUUID());

        when(exitsTransactionUuidOrderUuidRepository.invoke(anyString(), eq(null), anyList())).thenReturn(
                existsTransactionUuidOrderUuidDTO);

        getTransactionUuidUseCase.invoke("token", transactionRequestDTO);

        Assertions.assertNotNull(transactionRequestDTO.getUuid());
        Assertions.assertTrue(transactionRequestDTO.getOrders().stream()
                .noneMatch(orderDTO -> isNull(orderDTO.getUuid())));
    }

    @Test
    void test_GetTransactionUuidUseCase_Transaction_Null_Order_Not_Null_Exits() {

        ExistsTransactionUuidOrderUuidDTO existsOrderUidDTO = ExistsTransactionUuidOrderUuidDTO.builder()
                .exits(true)
                .message("exits")
                .build();

        TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTOMultipleOrders;

        for (OrderDTO order : transactionRequestDTO.getOrders()
        ) {
            order.setUuid(UUID.randomUUID());
        }

        when(exitsTransactionUuidOrderUuidRepository.invoke(anyString(), eq(null), anyList())).thenReturn(
                existsOrderUidDTO);

        TransactionCreationException result = Assertions.assertThrows(TransactionCreationException.class, () -> {
            getTransactionUuidUseCase.invoke("token", transactionRequestDTO);

        });

        Assertions.assertEquals("exits", result.getMessage());
    }

    @Test
    void test_GetTransactionUuidUseCase_Transaction_Null_Order_Not_ALL_Null_Exits() {

        TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTOMultipleOrders;

        transactionRequestDTO.getOrders().get(0).setUuid((UUID.randomUUID()));

        TransactionCreationException result = Assertions.assertThrows(TransactionCreationException.class, () -> {
            getTransactionUuidUseCase.invoke("token", transactionRequestDTO);

        });

        Assertions.assertEquals("All order uuids must be sent or send none"
                , result.getMessage());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST
                , result.getStatus());
    }

    @Test
    void test_GetTransactionUuidUseCase_Transaction_Null_Order_Not_ALL_Not_Null_Not_Exits() {

        ExistsTransactionUuidOrderUuidDTO exits = ExistsTransactionUuidOrderUuidDTO.builder()
                .exits(false)
                .message("not exits")
                .build();

        ExistsTransactionUuidOrderUuidDTO notExits = ExistsTransactionUuidOrderUuidDTO.builder()
                .exits(true)
                .message("exits")
                .build();

        TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTOMultipleOrders;

        when(exitsTransactionUuidOrderUuidRepository.invoke(anyString(), eq(null), anyList()))
                .thenReturn(notExits)
                .thenReturn(exits);

        getTransactionUuidUseCase.invoke("token", transactionRequestDTO);

        Assertions.assertNotNull(transactionRequestDTO.getUuid());
        Assertions.assertTrue(transactionRequestDTO.getOrders().stream()
                .noneMatch(orderDTO -> isNull(orderDTO.getUuid())));
    }

    @Test
    void test_GetTransactionUuidUseCase_Orders_Not_Null_Exits() {

        ExistsTransactionUuidOrderUuidDTO exits = ExistsTransactionUuidOrderUuidDTO.builder()
                .exits(true)
                .message("exits")
                .build();

        TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTOMultipleOrders;

        for (OrderDTO order : transactionRequestDTO.getOrders()
        ) {
            order.setUuid(UUID.randomUUID());
        }

        when(exitsTransactionUuidOrderUuidRepository.invoke(anyString(), eq(null), anyList())).thenReturn(
                exits);

        TransactionCreationException result = Assertions.assertThrows(TransactionCreationException.class, () -> {
            getTransactionUuidUseCase.invoke("token", transactionRequestDTO);

        });

        Assertions.assertEquals("exits", result.getMessage());
    }

    @Test
    void test_GetTransactionUuidUseCase_Orders_Not_Null_Not_Exits() {

        ExistsTransactionUuidOrderUuidDTO notExits = ExistsTransactionUuidOrderUuidDTO.builder()
                .exits(false)
                .message("not exits")
                .build();

        TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

        TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTOMultipleOrders;

        for (OrderDTO order : transactionRequestDTO.getOrders()
        ) {
            order.setUuid(UUID.randomUUID());
        }

        when(exitsTransactionUuidOrderUuidRepository.invoke(anyString(), eq(null), anyList())).thenReturn(
                notExits);

        getTransactionUuidUseCase.invoke("token", transactionRequestDTO);

        Assertions.assertNotNull(transactionRequestDTO.getUuid());
        Assertions.assertTrue(transactionRequestDTO.getOrders().stream()
                .noneMatch(orderDTO -> isNull(orderDTO.getUuid())));
    }
}
