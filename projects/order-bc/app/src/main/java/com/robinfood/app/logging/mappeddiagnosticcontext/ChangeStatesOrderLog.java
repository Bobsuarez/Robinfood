package com.robinfood.app.logging.mappeddiagnosticcontext;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import org.slf4j.MDC;

import java.util.Objects;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

public final class ChangeStatesOrderLog {

    private ChangeStatesOrderLog() {
        throw new IllegalStateException("Utility class");
    }

    public static void invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {

        MDC.clear();

        MDC.put("orderId", validateAttribute(changeOrderStatusDTO.getOrderId()));
        MDC.put("deliveryId", changeOrderStatusDTO.getDeliveryIntegrationId());
        MDC.put("statusCode", changeOrderStatusDTO.getStatusCode());
        MDC.put("transactionId", validateAttribute(changeOrderStatusDTO.getTransactionId()));
        MDC.put("origin", changeOrderStatusDTO.getOrigin());
        MDC.put("userId", validateAttribute(changeOrderStatusDTO.getUserId()));
        MDC.put("uuid", changeOrderStatusDTO.getOrderUuid());
    }

    private static String validateAttribute(Object attribute) {

        if (Objects.isNull(attribute)) {
            return DEFAULT_STRING_VALUE;
        }
        return attribute.toString();
    }
}
