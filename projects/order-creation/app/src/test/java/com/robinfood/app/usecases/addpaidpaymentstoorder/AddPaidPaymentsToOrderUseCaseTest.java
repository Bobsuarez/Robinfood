package com.robinfood.app.usecases.addpaidpaymentstoorder;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddPaidPaymentsToOrderUseCaseTest {

    @InjectMocks
    private AddPaidPaymentsToOrderUseCase addPaidPaymentsToOrderUseCase;

    private final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

    @Test
    void test_When_Add_Paid_Payments_Happy_Path() {

        TransactionRequestDTO transactionRequest = transactionRequestDTOMocks.transactionRequestDTOWithPaymentsPaid;

        addPaidPaymentsToOrderUseCase.invoke(transactionRequest);

        assertEquals(BigDecimal.valueOf(7900.0), transactionRequest.getTotal());

    }
}
