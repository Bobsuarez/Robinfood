package com.robinfood.localprinterbc.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants
        .MESSAGE_HOST_INTERRUPTED_OR_EXECUTION_FAILED;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.MESSAGE_HOST_TIMEOUT;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.MESSAGE_HOST_UNKNOWN_EXCEPTION;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_HOST_CONNECTION_REFUSED;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_PING_FAILED;

@Slf4j
public class OrderPrinterUtil {

    public static void testConnectionPOS(String host, Integer port) throws IOException {

        int timeoutMillis = 3000;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Boolean> connectionTask = () -> {
            try (Socket socket = new Socket(host, port)) {
                return Boolean.TRUE;
            } catch (java.net.NoRouteToHostException e) {
                log.error(MESSAGE_PING_FAILED.getMessage() + "{} {}", MESSAGE_HOST_INTERRUPTED_OR_EXECUTION_FAILED
                        , host, e);
                return Boolean.FALSE;
            } catch (java.io.IOException e) {
                log.error(MESSAGE_PING_FAILED.getMessage() + "{} {}", MESSAGE_HOST_TIMEOUT, host, e);
                return Boolean.FALSE;
            } catch (Exception e) {
                log.info(MESSAGE_PING_FAILED.getMessage() + "{} {}", MESSAGE_HOST_UNKNOWN_EXCEPTION, host, e);
                return Boolean.FALSE;
            }
        };

        Future<Boolean> future = executor.submit(connectionTask);

        try {
            Boolean isConnected = future.get(timeoutMillis, TimeUnit.MILLISECONDS);
            if (!isConnected) {
                throw new IOException("Connection refused");
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(MESSAGE_HOST_CONNECTION_REFUSED.getMessage() + "{} {}",
                    MESSAGE_HOST_INTERRUPTED_OR_EXECUTION_FAILED, host);
            throw new IOException("Connection refused " + e);
        } catch (TimeoutException e) {
            log.error(MESSAGE_HOST_CONNECTION_REFUSED.getMessage() + "{} {}", MESSAGE_HOST_TIMEOUT, host);
            throw new IOException("Connection refused " + e);
        } catch (Exception e) {
            log.error(MESSAGE_HOST_CONNECTION_REFUSED.getMessage() + "{} {}", MESSAGE_HOST_UNKNOWN_EXCEPTION, host);
            throw new IOException("Connection refused " + e);
        }

        executor.shutdown();
    }
}
