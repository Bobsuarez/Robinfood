package com.robinfood.usecases.processhandler;

import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.usecases.createorder.CreateOrderUseCase;
import com.robinfood.usecases.createorder.ICreateOrderUseCase;
import com.robinfood.usecases.gettokenbusinesscapability.GetTokenBusinessCapabilityUseCase;
import com.robinfood.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.util.LogsUtil;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.robinfood.constants.GeneralConstants.VALUE_TIME_OUT;
import static com.robinfood.enums.AppLogsTraceEnum.FINAL_PROCESS_SUBSCRIBER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_EXCEPTION_THREAD_TIMEOUT;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_EXCEPTION_URL_NOT_FOUND_PROPERTY;

public class ProcessHandlerUseCase implements IProcessHandlerUseCase {

    private final ICreateOrderUseCase createOrderUseCase;

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public ProcessHandlerUseCase(
            ICreateOrderUseCase createOrderUseCase,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    public ProcessHandlerUseCase() {
        this.createOrderUseCase = new CreateOrderUseCase();
        this.getTokenBusinessCapabilityUseCase = new GetTokenBusinessCapabilityUseCase();
    }

    private void waitThreadsProcess(CompletableFuture<Void> future) {
        try {
            future.get(VALUE_TIME_OUT, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Thread.currentThread().interrupt();
            LogsUtil.error(ERROR_EXCEPTION_THREAD_TIMEOUT.getMessageWithCodeWithComplement(e));
        }
    }

    @Override
    public void invoke(OrderToCreateDTO orderToCreateDTO, Map<String, String> messageAttribute) {

        final String token = getTokenBusinessCapabilityUseCase.invoke();

        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                createOrderUseCase.invoke(orderToCreateDTO, token, messageAttribute)
        ).exceptionally((Throwable ex) -> {
            LogsUtil.error(ERROR_EXCEPTION_URL_NOT_FOUND_PROPERTY
                    .getMessageWithCodeWithComplement(ex));
            return null;
        });

        waitThreadsProcess(future);

        LogsUtil.info(FINAL_PROCESS_SUBSCRIBER.getMessage());
    }
}
