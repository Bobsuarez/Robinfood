package com.robinfood.paymentmethodsbc.commands.transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SaveTransactionFactory {
    private static final Map<String, SaveTransactionHandler> commandMap = new HashMap<>();
    static {
        commandMap.put("RETRY_FLAG_TRUE", new SaveTransactionOperation());
        commandMap.put("STATUS_RESULT_TRUE", new SaveTransactionOperation());
    }

    private SaveTransactionFactory() {}

    public static Optional<SaveTransactionHandler> getOperation(String operation) {
        return Optional.ofNullable(commandMap.get(operation));
    }
}
