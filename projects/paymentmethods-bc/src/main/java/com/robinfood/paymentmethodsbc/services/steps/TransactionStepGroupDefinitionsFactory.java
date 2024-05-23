package com.robinfood.paymentmethodsbc.services.steps;

import com.robinfood.paymentmethodsbc.dto.steps.StepGroupDTO;
import com.robinfood.paymentmethodsbc.enums.PlatformType;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.GENERATE_TRANSACTION_DATAPHONE_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.GENERATE_TRANSACTION_FOODCOINS_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.GENERATE_TRANSACTION_NO_VALIDATION_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.GENERATE_TRANSACTION_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.INITIAL_TRANSACTION_DATAPHONE_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.INITIAL_TRANSACTION_FOODCOINS_STEPS;
import static com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions.INITIAL_TRANSACTION_STEPS;

public final class TransactionStepGroupDefinitionsFactory {
    private static final Map<PlatformType, List<StepGroupDTO>> INITIAL_TRANSACTIONS_DEFINITIONS = new HashMap<>();
    private static final Map<PlatformType, List<StepGroupDTO>> GENERATE_TRANSACTIONS_DEFINITIONS = new HashMap<>();

    static {
        //Definitions for initial transaction
        INITIAL_TRANSACTIONS_DEFINITIONS.put(PlatformType.GATEWAY, INITIAL_TRANSACTION_STEPS);
        INITIAL_TRANSACTIONS_DEFINITIONS.put(PlatformType.DATAPHONE, INITIAL_TRANSACTION_DATAPHONE_STEPS);
        INITIAL_TRANSACTIONS_DEFINITIONS.put(PlatformType.FOODCOINS, INITIAL_TRANSACTION_FOODCOINS_STEPS);
        INITIAL_TRANSACTIONS_DEFINITIONS.put(PlatformType.CASH, GENERATE_TRANSACTION_NO_VALIDATION_STEPS);
        INITIAL_TRANSACTIONS_DEFINITIONS.put(PlatformType.INTEGRATIONS, GENERATE_TRANSACTION_NO_VALIDATION_STEPS);

        //Definitions for generate transaction
        GENERATE_TRANSACTIONS_DEFINITIONS.put(PlatformType.GATEWAY, GENERATE_TRANSACTION_STEPS);
        GENERATE_TRANSACTIONS_DEFINITIONS.put(PlatformType.DATAPHONE, GENERATE_TRANSACTION_DATAPHONE_STEPS);
        GENERATE_TRANSACTIONS_DEFINITIONS.put(PlatformType.FOODCOINS, GENERATE_TRANSACTION_FOODCOINS_STEPS);
    }

    private TransactionStepGroupDefinitionsFactory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Get the StepGroupDTO to initiate transaction according to platform
     * @param platformTypeId {@linkplain PlatformType}
     * @return {@linkplain List<StepGroupDTO>}
     */
    public static List<StepGroupDTO> getInitialTransactionDefinitions(
        final PlatformType platformTypeId
    ) throws BaseException {
        final List<StepGroupDTO> stepGroupDefinitions =
            INITIAL_TRANSACTIONS_DEFINITIONS.get(platformTypeId);

        if (Objects.nonNull(stepGroupDefinitions)) {
            return stepGroupDefinitions;
        }
        throw new BaseException(
            ResponseCode.SERVICE_ERROR,
            String.format("No such StepGroupDefinitions to %s", platformTypeId)
        );
    }

    /**
     * Get the StepGroupDTO to generate transaction according to platform
     * @param platformTypeId {@linkplain PlatformType}
     * @return {@linkplain List<StepGroupDTO>}
     */
    public static List<StepGroupDTO> getGenerateTransactionDefinitions(
        final PlatformType platformTypeId
    ) throws BaseException {
        final List<StepGroupDTO> stepGroupDefinitions =
            GENERATE_TRANSACTIONS_DEFINITIONS.get(platformTypeId);

        if (Objects.nonNull(stepGroupDefinitions)) {
            return stepGroupDefinitions;
        }
        throw new BaseException(
            ResponseCode.SERVICE_ERROR,
            String.format("No such StepGroupDefinitions to %s",platformTypeId)
        );
    }
}
