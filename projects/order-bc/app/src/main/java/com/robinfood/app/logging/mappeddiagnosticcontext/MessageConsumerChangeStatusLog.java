package com.robinfood.app.logging.mappeddiagnosticcontext;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import org.slf4j.MDC;

import java.util.Objects;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

public final class MessageConsumerChangeStatusLog {

    private MessageConsumerChangeStatusLog() {
        throw new IllegalStateException("Utility class");
    }

    public static void invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {

        MDC.clear();

        MDC.put("deliveryId", changeOrderStatusDTO.getDeliveryIntegrationId());
        MDC.put("orderId", validateAttribute(changeOrderStatusDTO.getOrderId()));
        MDC.put("origin", changeOrderStatusDTO.getOrigin());
        MDC.put("statusCode", changeOrderStatusDTO.getStatusCode());
        MDC.put("transactionId", validateAttribute(changeOrderStatusDTO.getTransactionId()));
        MDC.put("uuid", changeOrderStatusDTO.getOrderUuid());
        MDC.put("userId", validateAttribute(changeOrderStatusDTO.getUserId()));
    }

    private static String validateAttribute(Object attribute) {

        if (Objects.isNull(attribute)) {
            return DEFAULT_STRING_VALUE;
        }
        return attribute.toString();
    }
}
