package com.robinfood.app.usecases.calculatesummationfinalproductaddition;


import com.robinfood.app.datamocks.dto.input.FinalProductDTODataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculateFinalProductAdditionUseCaseTest {

    @InjectMocks
    private CalculateFinalProductAdditionUseCase calculateFinalProductAdditionUseCase;

    private final FinalProductDTODataMock inputFinalProductDTODataMock = new FinalProductDTODataMock();

    @Test
    void test_CalculateSummationAdditions_When_Save_Success() {

        Double result = calculateFinalProductAdditionUseCase.invoke(
                inputFinalProductDTODataMock.getDefaultWithFreePortions()
        );

        Assertions.assertEquals(400.0, result);
    }

    @Test
    void test_CalculateSummationAdditions_Multiple_Portions_When_Save_Success() {

        Double result = calculateFinalProductAdditionUseCase.invoke(
                inputFinalProductDTODataMock.getDefaultWithFreePortions()
        );

        Assertions.assertEquals(400.0, result);
    }

}
