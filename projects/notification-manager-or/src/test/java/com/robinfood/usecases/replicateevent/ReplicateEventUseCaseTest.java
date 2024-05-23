package com.robinfood.usecases.replicateevent;

import com.robinfood.datamock.ChangeStatusDTOMock;
import com.robinfood.datamock.EventDTOMock;
import com.robinfood.datamock.SubscriberDTOMock;
import com.robinfood.datamock.TypeDTOMock;
import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.SubscriberDTO;
import com.robinfood.dtos.TypeDTO;
import com.robinfood.exceptions.ResourceNotFoundException;
import com.robinfood.strategies.replicateevent.IReplicateEventStrategy;
import com.robinfood.usecases.saveeventhistory.ISaveEventHistoryUseCase;
import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.robinfood.constants.Constants.VALUE_TIME_OUT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReplicateEventUseCaseTest {

    @Mock
    private ISaveEventHistoryUseCase saveEventHistoryUseCase;

    @Mock
    private IReplicateEventStrategy replicateEventStrategy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void test_ReplicateUseCase_Should_Successfully_When_InvokeTheUseCase() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        doNothing().when(saveEventHistoryUseCase).invoke(anyLong(), anyLong(), anyLong(), anyString(), anyString());

        doNothing().when(replicateEventStrategy).execute(any(ChangeStatusDTO.class),
                anyString(), any(SubscriberDTO.class), anyString());

        ReplicateEventUseCase replicateEventUseCase = new ReplicateEventUseCase(saveEventHistoryUseCase, replicateEventStrategy);

        replicateEventUseCase.invoke(
                ChangeStatusDTOMock.getDefault(),
                EventDTOMock.getDefault(),
                List.of(SubscriberDTOMock.getDefault()),
                "token",
                "a4271e7d-416b-454c-98fa-604fdbb447ca"
        );

        Mockito.verify(saveEventHistoryUseCase, Mockito.times(1)).invoke(
                anyLong(), anyLong(), anyLong(), anyString(), anyString()
        );

        clearAllCaches();
    }

    @Test
    void test_ReplicateUseCase_Should_ApplicationException_When_NotExistHttp() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        ReplicateEventUseCase replicateEventUseCase = new ReplicateEventUseCase(saveEventHistoryUseCase, replicateEventStrategy);

        SubscriberDTO subscriberDTO = SubscriberDTOMock.getDefault();
        TypeDTO typeDTO = TypeDTOMock.getDefault();
        typeDTO.setName("otherHttp");
        subscriberDTO.setType(typeDTO);

        replicateEventUseCase.invoke(
                ChangeStatusDTOMock.getDefault(),
                EventDTOMock.getDefault(),
                List.of(subscriberDTO),
                "token",
                "a4271e7d-416b-454c-98fa-604fdbb447ca"
        );

        Assertions.assertNotNull(replicateEventUseCase);

        clearAllCaches();
    }

    @Test
    void test_ReplicateUseCase_Should_ApplicationException_When_replicateEventStrategyMethod() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        doThrow(new ResourceNotFoundException("Error en el método"))
                .when(replicateEventStrategy)
                .execute(any(ChangeStatusDTO.class),
                        anyString(),
                        any(SubscriberDTO.class),
                        anyString()
                );

        ReplicateEventUseCase replicateEventUseCase = new ReplicateEventUseCase(saveEventHistoryUseCase, replicateEventStrategy);

        replicateEventUseCase.invoke(
                ChangeStatusDTOMock.getDefault(),
                EventDTOMock.getDefault(),
                List.of(SubscriberDTOMock.getDefault()),
                "token",
                "a4271e7d-416b-454c-98fa-604fdbb447ca"
        );

        Assertions.assertNotNull(replicateEventUseCase);

        clearAllCaches();
    }

    @Test
    void test_ReplicateUseCase_Should_BuildConstructor_When_MethodInvoke() {

        ReplicateEventUseCase replicateEventUseCase = new ReplicateEventUseCase();

        Assertions.assertNotNull(replicateEventUseCase);
    }

    @Test
    void test_ReplicateUseCase_Should_InterruptedException_When_WaitThreadsProcess()
            throws InterruptedException, ExecutionException, TimeoutException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        final CompletableFuture<Void> mockedFuture = Mockito.mock(CompletableFuture.class);
        when(mockedFuture.get(VALUE_TIME_OUT, TimeUnit.SECONDS)).thenThrow(InterruptedException.class);

        ReplicateEventUseCase.waitThreadsProcess(mockedFuture);

        clearAllCaches();
    }
    @Test
    void test_ReplicateUseCase_Should_ExecutionException_When_WaitThreadsProcess()
            throws InterruptedException, ExecutionException, TimeoutException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        final CompletableFuture<Void> mockedFuture = Mockito.mock(CompletableFuture.class);
        when(mockedFuture.get(VALUE_TIME_OUT, TimeUnit.SECONDS)).thenThrow(ExecutionException.class);

        ReplicateEventUseCase.waitThreadsProcess(mockedFuture);

        clearAllCaches();
    }

    @Test
    void test_ReplicateUseCase_Should_TimeoutException_When_WaitThreadsProcess()
            throws InterruptedException, ExecutionException, TimeoutException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        final CompletableFuture<Void> mockedFuture = Mockito.mock(CompletableFuture.class);
        when(mockedFuture.get(VALUE_TIME_OUT, TimeUnit.SECONDS)).thenThrow(TimeoutException.class);

        ReplicateEventUseCase.waitThreadsProcess(mockedFuture);

        clearAllCaches();
    }

}
