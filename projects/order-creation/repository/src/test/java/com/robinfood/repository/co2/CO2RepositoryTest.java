package com.robinfood.repository.co2;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.entities.CO2CalculateRequestEntity;
import com.robinfood.core.entities.CO2CalculateResponseEntity;
import com.robinfood.repository.mocks.TransactionRequestDTOMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CO2RepositoryTest {

    @Mock
    private ICO2RemoteDataSource co2RemoteDataSource;

    @InjectMocks
    private CO2Repository co2Repository;

    private final String token = "token";

    @Test
    void test_calculateCO2Compensation_Success() {
        OrderDTO orderDTO = new TransactionRequestDTOMocks().transactionRequestDTO.getOrders().get(0);

        when(co2RemoteDataSource.calculateCO2Compensation(
                anyString(), any(CO2CalculateRequestEntity.class))
        )
                .thenReturn(CO2CalculateResponseEntity.builder().build());
        
        assertAll(() -> co2Repository.calculateCO2Compensation(token, orderDTO));
    }

}
