package com.robinfood.usecases.createsubscribereventhistorylogs;

import com.robinfood.datamock.SubscriberEventHistoryLogsEntityMock;
import com.robinfood.datamock.request.SubscriberEventHistoryLogsRequestDTOMock;
import com.robinfood.dtos.createsubscribereventhistorylogs.response.SubscriberEventHistoryLogsResponseDTO;
import com.robinfood.repository.subscribereventhistorylogs.ISubscriberEventHistoryLogsRepository;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class CreateSubscriberEventHistoryLogsUseCaseTest {

    @Mock
    private ISubscriberEventHistoryLogsRepository subscriberEventHistoryLogsRepository;

    @Mock
    private CreateSubscriberEventHistoryLogsUseCase createSubscriberEventHistoryLogsUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Should_CreateSubscriberEventHistory_When_Success() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(any());

        when(subscriberEventHistoryLogsRepository.save(any())).thenReturn(new BigInteger(String.valueOf(1)));
        when(subscriberEventHistoryLogsRepository.searchById(anyLong())).thenReturn(
                SubscriberEventHistoryLogsEntityMock.build()
        );

        createSubscriberEventHistoryLogsUseCase =
                new CreateSubscriberEventHistoryLogsUseCase(subscriberEventHistoryLogsRepository);

        final SubscriberEventHistoryLogsResponseDTO requestDTO = createSubscriberEventHistoryLogsUseCase.invoke(
                SubscriberEventHistoryLogsRequestDTOMock.build()
        );

        Assert.notNull(requestDTO);
        clearAllCaches();
    }

    @Test
    void test_Should_CreateSubscriberEventHistory_When_Not_Found() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(any());

        when(subscriberEventHistoryLogsRepository.save(any())).thenReturn(new BigInteger(String.valueOf(-1)));
        when(subscriberEventHistoryLogsRepository.searchById(anyLong())).thenReturn(null);

        createSubscriberEventHistoryLogsUseCase =
                new CreateSubscriberEventHistoryLogsUseCase(subscriberEventHistoryLogsRepository);

        final SubscriberEventHistoryLogsResponseDTO requestDTO = createSubscriberEventHistoryLogsUseCase.invoke(
                SubscriberEventHistoryLogsRequestDTOMock.build()
        );

        Assert.notNull(requestDTO);
        clearAllCaches();
    }

    @Test
    void test_Should_CreateSubscriberEventHistory_When_Build_Empty() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(any());

        createSubscriberEventHistoryLogsUseCase = new CreateSubscriberEventHistoryLogsUseCase();
        Assert.notNull(createSubscriberEventHistoryLogsUseCase);
        clearAllCaches();
    }
}
