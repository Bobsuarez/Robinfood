package com.robinfood.paymentmethodsbc.utils;

import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.COMMA;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.DEFAULT_LIST_STATUS_TO_NOTIFICATION;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.LIST_STATUS_TO_NOTIFICATION;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_PENDING;

import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.enums.ValidateStatusEnum;
import com.robinfood.paymentmethodsbc.model.Transaction;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class FilterStatusNotificationUtils {

    private FilterStatusNotificationUtils() {
    }

    public static boolean isNotify(
        SettingsDTO settingsDTO,
        Transaction transaction,
        Long statusTransaction,
        Long statusRedeban

    ){
        if (ValidateStatusEnum.isGetStatusResult(statusTransaction,statusRedeban)){
            return filterListStatusNotNotification(settingsDTO,transaction);
        }
        return false;
    }

    public static boolean filterListStatusNotNotification(
        SettingsDTO settingsDTO,
        Transaction transaction
    ) {

        Long transactionStatusId = transaction.getTransactionStatus().getId();

        List<Long> gatewayConfigValues = Arrays.stream(Optional.ofNullable(
                    Optional.ofNullable(settingsDTO)
                        .orElse(SettingsDTO.builder()
                            .gatewayConfig(
                                Map.of(LIST_STATUS_TO_NOTIFICATION, DEFAULT_LIST_STATUS_TO_NOTIFICATION))
                            .build()).getGatewayConfig())
                .filter(
                    gatewayConfig -> gatewayConfig.containsKey(LIST_STATUS_TO_NOTIFICATION))
                .orElse(Map.of(LIST_STATUS_TO_NOTIFICATION, DEFAULT_LIST_STATUS_TO_NOTIFICATION))
                .get(LIST_STATUS_TO_NOTIFICATION)
                .split(COMMA))
            .map(Long::parseLong)
            .collect(Collectors.toList());

        log.info("process filter whit list status :{} and idStatus to be validated: {}",
            gatewayConfigValues, transactionStatusId);

        return gatewayConfigValues.contains(transactionStatusId) &&
            !gatewayConfigValues.contains(STATUS_PENDING);
    }

}
