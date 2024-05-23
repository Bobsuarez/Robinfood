package com.robinfood.app.usecases.distributedplatformdeleveryfee;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.distributeplatformdeliveryfee.DistributedPlatformDeliveryFeeUseCase;
import com.robinfood.app.usecases.getallactivetypedeductions.IGetTotalDeductionsUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class DistributedPlatformDeliveryFeeUseCaseTest {
    @InjectMocks
    private DistributedPlatformDeliveryFeeUseCase distributedPlatformDeliveryFeeUseCase;

    @Mock
    private IGetTotalDeductionsUseCase getTotalDeductionsUseCase;
    private final TransactionRequestDTO transactionRequestDTOMocks = new TransactionRequestDTOMock().transactionRequestDTO;

    @Test
    void test_Distribute_Deductions() {

        when(getTotalDeductionsUseCase.invoke(transactionRequestDTOMocks,"token"))
                .thenReturn(BigDecimal.valueOf(2000));
        distributedPlatformDeliveryFeeUseCase.invoke(transactionRequestDTOMocks, "token");

        assertEquals(transactionRequestDTOMocks.getOrders().get(0).getDeductions().get(0).getValue(),
                BigDecimal.valueOf(2000.0000).setScale(4));


    }
    @Test
    void test_Distribute_Deductions_equals_total_order() {

        when(getTotalDeductionsUseCase.invoke(transactionRequestDTOMocks,"token"))
                .thenReturn(BigDecimal.valueOf(0));
        distributedPlatformDeliveryFeeUseCase.invoke(transactionRequestDTOMocks, "token");

        assertEquals(transactionRequestDTOMocks.getOrders().get(0).getDeductions(),new ArrayList<>());


    }
}
