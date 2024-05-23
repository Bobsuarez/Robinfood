package com.robinfood.usecases.sendmessagetosimba;

import com.robinfood.datamock.TransactionRequestDTOMock;
import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.repository.connectorsimba.IConnectorSimbaRepository;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SendMessageToSimbaUseCaseTest {

    @Mock
    private IConnectorSimbaRepository connectorSimbaRepository;

    private final String DATA_RESPONSE = "DATA DATA_RESPONSE";

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
    void test_SendMessageToSimbaUseCase_Accepted() {

        when(connectorSimbaRepository.invoke(any(TransactionRequestDTO.class), anyString()))
                .thenReturn(DATA_RESPONSE);

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase
                = new SendMessageToSimbaUseCase(connectorSimbaRepository);

        String data = sendMessageToSimbaUseCase
                .invoke(TransactionRequestDTOMock.getDefault(), "Token");

        Assertions.assertEquals(data, DATA_RESPONSE);

        verify(connectorSimbaRepository, times(1))
                .invoke(any(TransactionRequestDTO.class), anyString());
    }

    @Test
    void test_Invoke_SendMessageToSimbaUseCase_Should_When_Instance() {

        SendMessageToSimbaUseCase sendMessageToSimbaUseCase
                = new SendMessageToSimbaUseCase();

        Assert.notNull(sendMessageToSimbaUseCase);
    }
}