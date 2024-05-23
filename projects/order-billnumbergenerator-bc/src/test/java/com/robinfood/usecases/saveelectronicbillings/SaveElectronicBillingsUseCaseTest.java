package com.robinfood.usecases.saveelectronicbillings;

import com.robinfood.datamock.OrderElectronicBillingsEntityMock;
import com.robinfood.entities.OrderElectronicBillingsEntity;
import com.robinfood.repository.electronicbillings.IElectronicBillingsRepository;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaveElectronicBillingsUseCaseTest {

    @Mock
    private IElectronicBillingsRepository electronicBillingsRepository;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString(), any());
    }

    @AfterEach
    public void setupAfter() {
        clearAllCaches();
    }

    @Test
    void test_SaveElectronicBillingsUseCase_Accepted() {

        when(electronicBillingsRepository.save(any(OrderElectronicBillingsEntity.class)))
                .thenReturn(BigInteger.ONE);

        SaveElectronicBillingsUseCase saveElectronicBillingsUseCase = new SaveElectronicBillingsUseCase(electronicBillingsRepository);

        saveElectronicBillingsUseCase.invoke(OrderElectronicBillingsEntityMock.getDefault());

        verify(electronicBillingsRepository, times(1))
                .save(any(OrderElectronicBillingsEntity.class));
    }

    @Test
    void test_Invoke_SaveElectronicBillingsUseCase_Should_When_Instance() {

        SaveElectronicBillingsUseCase saveElectronicBillingsUseCase
                = new SaveElectronicBillingsUseCase();

        Assert.notNull(saveElectronicBillingsUseCase);
    }
}