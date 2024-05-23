package com.robinfood.app.usecases.exitstransactionuuidandorderuids;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.response.order.ResponseExistsTransactionUuidOrderUidDTO;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExitsTransactionUuidAndOrderUuidsUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;

    @Mock
    private ITransactionCRUDRepository transactionCRUDRepository;

    @InjectMocks
    ExitsTransactionUuidAndOrderUuidsUseCase exitsTransactionUuidAndOrderUuidsUseCase;

    private final String transactionUuid = "43e5d0b6-9f93-4d81-9c8d-010f5ddb072d";

    private final List<String> orderUids = List.of(
            "unfable28xon",
            "ulxdajw1ha6c"
    );

    @Test
    void test_Exits_TransactionUuid_And_OrderUId_Return_False() {

        when(ordersRepository.existsByUuidInAndPaid(anyList(), anyBoolean()))
                .thenReturn(false);

        when(transactionCRUDRepository.existsTransactionEntityByUniqueIdentifier(anyString()))
                .thenReturn(false);

        final ResponseExistsTransactionUuidOrderUidDTO result = exitsTransactionUuidAndOrderUuidsUseCase.invoke(
                transactionUuid, orderUids);

        Assertions.assertEquals(
                String.format("None of these TransactionUuid: %1$s and OrderUuids: %2$s exist and aren't paid",
                        transactionUuid,
                        orderUids), result.getMessage());

        Assertions.assertFalse(result.isExits());
    }

    @Test
    void test_Exits_TransactionUuid_And_OrderUId_Return_TransactionUuid_Exist() {

        when(transactionCRUDRepository.existsTransactionEntityByUniqueIdentifier(anyString()))
                .thenReturn(true);

        final ResponseExistsTransactionUuidOrderUidDTO result = exitsTransactionUuidAndOrderUuidsUseCase.invoke(
                transactionUuid, orderUids);

        Assertions.assertEquals(
                String.format("Uuid %1$s exits", transactionUuid), result.getMessage());

        Assertions.assertTrue(result.isExits());
    }

    @Test
    void test_Exits_TransactionUuid_And_OrderUId_Return_OrderUid_Exist() {

        when(transactionCRUDRepository.existsTransactionEntityByUniqueIdentifier(anyString()))
                .thenReturn(false);

        when(ordersRepository.existsByUuidInAndPaid(anyList(), anyBoolean()))
                .thenReturn(true);

        final ResponseExistsTransactionUuidOrderUidDTO result = exitsTransactionUuidAndOrderUuidsUseCase.invoke(
                transactionUuid, orderUids);

        Assertions.assertEquals(
                String.format("Any of these %1$s uuids exits and it's paid", orderUids), result.getMessage());

        Assertions.assertTrue(result.isExits());
    }

    @Test
    void test_Exits_TransactionUuid_And_OrderUId_When_TransactionUuid_Is_Empty_Return_OrderUid_Exist() {

        when(ordersRepository.existsByUuidInAndPaid(anyList(), anyBoolean()))
                .thenReturn(true);

        final ResponseExistsTransactionUuidOrderUidDTO result = exitsTransactionUuidAndOrderUuidsUseCase.invoke(
                "", orderUids);

        Assertions.assertEquals(
                String.format("Any of these %1$s uuids exits and it's paid", orderUids), result.getMessage());

        Assertions.assertTrue(result.isExits());
    }

    @Test
    void test_Exits_TransactionUuid_And_OrderUId_When_OrderUid_Is_Empty_Return_TransactionUuid_Exist() {

        when(transactionCRUDRepository.existsTransactionEntityByUniqueIdentifier(anyString()))
                .thenReturn(true);

        final ResponseExistsTransactionUuidOrderUidDTO result = exitsTransactionUuidAndOrderUuidsUseCase.invoke(
                transactionUuid, Collections.emptyList());

        Assertions.assertEquals(
                String.format("Uuid %1$s exits", transactionUuid), result.getMessage());

        Assertions.assertTrue(result.isExits());
    }

    @Test
    void test_Exits_TransactionUuid_And_OrderUId_When_OrderUid_Is_Empty_Return_False() {

        when(transactionCRUDRepository.existsTransactionEntityByUniqueIdentifier(anyString()))
                .thenReturn(false);

        final ResponseExistsTransactionUuidOrderUidDTO result = exitsTransactionUuidAndOrderUuidsUseCase.invoke(
                transactionUuid, Collections.emptyList());

        Assertions.assertEquals(
                String.format("None of these TransactionUuid: %1$s and OrderUuids: %2$s exist and aren't paid",
                        transactionUuid,
                        Collections.emptyList()), result.getMessage());

        Assertions.assertFalse(result.isExits());
    }

    @Test
    void test_Exits_TransactionUuid_And_OrderUId_When_TransactionUuid_Is_Empty_Return_False() {

        when(ordersRepository.existsByUuidInAndPaid(anyList(), anyBoolean()))
                .thenReturn(false);

        final ResponseExistsTransactionUuidOrderUidDTO result = exitsTransactionUuidAndOrderUuidsUseCase.invoke(
                "", orderUids);

        Assertions.assertEquals(
                String.format("None of these TransactionUuid: %1$s and OrderUuids: %2$s exist and aren't paid", "",
                        orderUids), result.getMessage());

        Assertions.assertFalse(result.isExits());
    }
}
