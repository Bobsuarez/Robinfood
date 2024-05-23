package com.robinfood.app.usecases.getsumproducttaxesprice;

import com.robinfood.app.datamocks.dto.input.FinalProductTaxDTODataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetSumProductTaxPriceUseCaseTest {

    private final FinalProductTaxDTODataMock finalProductTaxDTOMock = new FinalProductTaxDTODataMock();

    @InjectMocks
    private GetSumProductTaxPriceUseCase getSumProductTaxPriceUseCase;

    @Test
    void test_SumProductsTaxPrice_When_Pass_Correct_Data() {
        final Double result = getSumProductTaxPriceUseCase.invoke(
            finalProductTaxDTOMock.getDataDefaultListForCalculate());
        Assertions.assertEquals(370.0, result);
    }
}
