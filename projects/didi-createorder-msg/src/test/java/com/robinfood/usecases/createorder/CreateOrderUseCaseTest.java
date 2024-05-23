package com.robinfood.usecases.createorder;

import com.robinfood.datamock.OrderToCreateDTOMock;
import com.robinfood.datamock.request.APIGatewayRequestEventMock;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.repository.createtransaction.CreateTransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.when;

class CreateOrderUseCaseTest {


    @Mock
    private CreateTransactionRepository createTransactionRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void test_CreateOrderUseCase_When_DataOk_Should_OK() {

        when(createTransactionRepository.createTransaction(anyMap(), any(), anyString()))
                .thenReturn(OrderToCreateDTOMock.getDataDefault());

        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(createTransactionRepository);

        OrderToCreateDTO transactionDTO = createOrderUseCase
                .invoke(
                        OrderToCreateDTOMock.getDataDefault(),
                        "Token",
                        APIGatewayRequestEventMock.getHeaders()
                );

        Assertions.assertEquals(transactionDTO.getPaid(), Boolean.TRUE);
    }

    @Test
    void test_CreateOrderUseCase_Should_BuildConstructor_When_MethodInvoke() {

        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase();

        Assertions.assertNotNull(createOrderUseCase);
        clearAllCaches();
    }

}