package com.robinfood.usecases.savethirdparty;

import com.robinfood.datamock.ThirdPartyDTOMock;
import com.robinfood.entities.OrderThirdPartiesEntity;
import com.robinfood.repository.thirdparty.IOrderThirdPartiesRepository;
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

class SaveThirdPartyUseCaseTest {

    @Mock
    private IOrderThirdPartiesRepository orderThirdPartiesRepository;

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
    void test_SaveThirdPartyUseCase_Accepted() {

        when(orderThirdPartiesRepository.save(any(OrderThirdPartiesEntity.class)))
                .thenReturn(BigInteger.ONE);

        SaveThirdPartyUseCase saveThirdPartyUseCase
                = new SaveThirdPartyUseCase(orderThirdPartiesRepository);

        saveThirdPartyUseCase.invoke(123456L, ThirdPartyDTOMock.getDefault());

        verify(orderThirdPartiesRepository, times(1))
                .save(any(OrderThirdPartiesEntity.class));
    }

    @Test
    void test_Invoke_SaveThirdPartyUseCase_Should_When_Instance() {

        SaveThirdPartyUseCase saveThirdPartyUseCase
                = new SaveThirdPartyUseCase();

        Assert.notNull(saveThirdPartyUseCase);
    }
}