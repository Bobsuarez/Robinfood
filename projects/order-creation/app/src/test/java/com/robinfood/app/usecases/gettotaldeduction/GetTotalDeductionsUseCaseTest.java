package com.robinfood.app.usecases.gettotaldeduction;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.getallactivetypedeductions.GetTotalDeductionsUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.repository.deductions.IDeductionsRemoteDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTotalDeductionsUseCaseTest {
    @InjectMocks
    private GetTotalDeductionsUseCase getTotalDeductionsUseCase;

    @Mock
    private IDeductionsRemoteDataSource deductionsRemoteDataSource;

    private String token = "token";

    private TransactionRequestDTO request = new TransactionRequestDTOMock().transactionRequestDTOWithDeductions;

    private TransactionRequestDTO requestTotal = new TransactionRequestDTOMock().transactionRequestDTOWithDeductionsTotalOrder;
    private Map<Long,String> data = new HashMap<>();

    @Test
    void get_total_deductions_Happy_Path() {

        data.put(1L,"Prueba");

        when(deductionsRemoteDataSource.getAllActiveTypeDeductions(token)).thenReturn(data);

        BigDecimal total = getTotalDeductionsUseCase.invoke(request,token);

        BigDecimal expect = BigDecimal.valueOf(2000);

        Assertions.assertEquals(total, expect);

    }
    @Test
    void get_total_deductions_when_equals_order_total() {

        data.put(1L,"Prueba");

        when(deductionsRemoteDataSource.getAllActiveTypeDeductions(token)).thenReturn(data);

        BigDecimal total = getTotalDeductionsUseCase.invoke(requestTotal,token);

        BigDecimal expect = BigDecimal.valueOf(7900.0000).setScale(4);

        Assertions.assertEquals(expect,total );

    }
}
