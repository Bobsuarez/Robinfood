package com.robinfood.app.usecases.getsumserviceprice;

import com.robinfood.app.datamocks.dto.input.ServiceDTODataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetSumServicePriceUseCaseTest {
    private final ServiceDTODataMock serviceDTOMock = new ServiceDTODataMock();

    @InjectMocks
    private GetSumServicePriceUseCase getSumServicePriceUseCase;

    @Test
    void test_SumServicePrice_When_Pass_Correct_Data() {
        final Double result = getSumServicePriceUseCase.invoke(serviceDTOMock.getDataDefaultListForCalculate());
        Assertions.assertEquals(18000, result);
    }
}
