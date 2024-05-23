package com.robinfood.repository.createtransaction;

import com.robinfood.datamock.OrderToCreateDTOMock;
import com.robinfood.datamock.request.APIGatewayRequestEventMock;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.network.http.api.OrderCreationAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CreateTransactionRepositoryTest {

    @Mock
    private OrderCreationAPI orderCreationAPI;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_CreateTransactionRepository_Should_DataOK_When_CreateTransaction() {

        when(orderCreationAPI.processOrders(anyMap(), any(OrderToCreateDTO.class), anyString()))
                .thenReturn(OrderToCreateDTOMock.getDataDefault());

        CreateTransactionRepository createTransactionRepository = new CreateTransactionRepository(orderCreationAPI);

        OrderToCreateDTO transactionDTO = createTransactionRepository
                .createTransaction(
                        APIGatewayRequestEventMock.getHeaders(),
                        OrderToCreateDTOMock.getDataDefault(),
                        "Token"
                );

        assertNotNull(transactionDTO);
    }

    @Test
    void test_CreateTransactionRepository_Should_BuildConstructor_When_MethodInvoke() {

        CreateTransactionRepository createTransactionRepository = new CreateTransactionRepository();

        assertNotNull(createTransactionRepository);
    }
}