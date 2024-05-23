package com.robinfood.usecases.replicateevent;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.EventDTO;
import com.robinfood.dtos.SubscriberDTO;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.ResourceNotFoundException;
import com.robinfood.factories.ReplicateEventFactory;
import com.robinfood.strategies.replicateevent.HttpReplicateEventStrategy;
import com.robinfood.strategies.replicateevent.IReplicateEventStrategy;
import com.robinfood.usecases.saveeventhistory.ISaveEventHistoryUseCase;
import com.robinfood.usecases.saveeventhistory.SaveEventHistoryUseCase;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.robinfood.constants.Constants.EVENT_DISPATCHED;
import static com.robinfood.constants.Constants.VALUE_TIME_OUT;
import static com.robinfood.enums.AppLogsTraceEnum.FINAL_PROCESS_SUBSCRIBER;
import static com.robinfood.enums.AppLogsTraceEnum.INITIAL_PROCESS_SUBSCRIBER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_EXCEPTION_THREAD_EXECUTION_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_EXCEPTION_THREAD_INTERRUPTED_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_EXCEPTION_THREAD_TIMEOUT;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_EXCEPTION_URL_NOT_FOUND_PROPERTY;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_URL_NOT_FOUND;

public class ReplicateEventUseCase implements IReplicateEventUseCase {

    private final ISaveEventHistoryUseCase saveEventHistoryUseCase;
    private final IReplicateEventStrategy replicateEventStrategy;

    public ReplicateEventUseCase(
            ISaveEventHistoryUseCase saveEventHistoryUseCase,
            IReplicateEventStrategy replicateEventUseCase
    ) {
        this.saveEventHistoryUseCase = saveEventHistoryUseCase;
        this.replicateEventStrategy = replicateEventUseCase;
    }

    public ReplicateEventUseCase() {
        this.saveEventHistoryUseCase = new SaveEventHistoryUseCase();
        this.replicateEventStrategy = new HttpReplicateEventStrategy();
    }

    @Override
    public void invoke(
            ChangeStatusDTO changeStatusDTO,
            EventDTO eventDTO,
            List<SubscriberDTO> subscriberDTOS,
            String token,
            String uuid
    ) {

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (SubscriberDTO subscriber : subscriberDTOS) {

            LogsUtil.info(INITIAL_PROCESS_SUBSCRIBER.getMessage(), ObjectMapperSingleton.objectToJson(subscriber));

            CompletableFuture<Void> future;
            try {

                boolean isHttp = ReplicateEventFactory.isExistHttp(subscriber.getType().getName());

                if (!isHttp) {
                    throw new ResourceNotFoundException(ERROR_URL_NOT_FOUND.getMessage());
                }

                future = CompletableFuture.runAsync(() ->
                        replicateEventStrategy.execute(changeStatusDTO, eventDTO.getEventId(), subscriber, token)
                ).exceptionally((Throwable ex) -> {
                    LogsUtil.error(ERROR_EXCEPTION_URL_NOT_FOUND_PROPERTY
                            .getMessageWithCodeWithComplement(ex));
                    return null;
                });

            } catch (ApplicationException applicationException) {
                LogsUtil.error(ERROR_EXCEPTION_URL_NOT_FOUND_PROPERTY
                        .getMessageWithCodeWithComplement(applicationException));
                continue;
            }

            future = future.thenRunAsync(() ->
                    saveEventHistoryUseCase.invoke(EVENT_DISPATCHED, eventDTO.getId(), subscriber.getId(), token, uuid)
            ).exceptionally((Throwable ex) -> {
                LogsUtil.error(ERROR_EXCEPTION_URL_NOT_FOUND_PROPERTY
                        .getMessageWithCodeWithComplement(ex));
                return null;
            });

            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        waitThreadsProcess(allOf);

        LogsUtil.info(FINAL_PROCESS_SUBSCRIBER.getMessage());
    }

    public static void waitThreadsProcess(Future<?> future) {
        try {
            future.get(VALUE_TIME_OUT, TimeUnit.SECONDS);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            LogsUtil.error(ERROR_EXCEPTION_THREAD_INTERRUPTED_EXCEPTION.getMessage(), interruptedException);
        } catch (ExecutionException executionException) {
            LogsUtil.error(ERROR_EXCEPTION_THREAD_EXECUTION_EXCEPTION.getMessage(), executionException);
        } catch (TimeoutException timeoutException) {
            LogsUtil.error(ERROR_EXCEPTION_THREAD_TIMEOUT.getMessage(), timeoutException);
        }
    }

}
